/*
   Sward App Copyright (C) 2020 FoAM Kernow

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package am.fo.swardapp.data

import am.fo.swardapp.R
import android.content.res.Resources
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

class SwardExport(val data: List<FieldWithSurveysAndRecords>, private val fileName: String, val resources: Resources, val thunk: () -> Unit ) {
    fun export() {
        csvWriter().open(fileName) {
            // Header
            writeRow(
                listOf(
                    "\"Field ID\"",
                    "\"Field name\"",
                    "\"Field date sown\"",
                    "\"Field soil type\"",
                    "\"Field notes\"",
                    "\"Survey ID\"",
                    "\"Survey time\"",
                    "\"Record sample\"",
                    "\"Record species\""
                )
            )
            data.forEach { field ->
                for (surveyAndRecords in field.surveysAndRecords) {
                    for (record in surveyAndRecords.records) {
                        writeRow(
                            listOf(
                                field.field.fieldId,
                                field.field.name,
                                field.field.dateSown,
                                resources.getStringArray(R.array.soil_types)[field.field.soilType],
                                field.field.notes,
                                surveyAndRecords.survey.surveyId,
                                surveyAndRecords.survey.time,
                                record.sample,
                                record.species
                            )
                        )
                    }
                }
            }
        }
        thunk()
    }
}