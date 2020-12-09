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
                    "Data type",
                    "Species",
                    "Sample no",
                    "Date",
                    "Name",
                    "Soil type",
                    "Notes"
                )
            )

            data.forEach { field ->
                writeRow(
                    listOf(
                        "Field",
                        "",
                        "",
                        field.field.dateSown,
                        field.field.name,
                        resources.getStringArray(R.array.soil_types)[field.field.soilType],
                        field.field.notes,
                    )
                )

                for (sown in field.sownSpecies) {
                    writeRow(
                        listOf(
                            "Sown species",
                            sown.species
                        )
                    )
                }

                for (surveyAndRecords in field.surveysAndRecords) {
                    writeRow(
                        listOf(
                            "Survey",
                            "",
                            "",
                            surveyAndRecords.survey.time,
                        )
                    )

                    for (record in surveyAndRecords.records) {
                        writeRow(
                            listOf(
                                "Species seen",
                                record.species,
                                record.sample
                            )
                        )
                    }
                }
            }
        }
        thunk()
    }
}