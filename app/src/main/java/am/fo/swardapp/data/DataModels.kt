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

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName="field_table")
data class Field(
    val name: String,
    val dateSown: String,
    val soilType: Int,
    val notes: String) {
    @PrimaryKey(autoGenerate = true)
    var fieldId:Long = 0
}

@Entity(tableName="sown_table")
data class Sown(val fieldId: Long,
                val species: String) {
    @PrimaryKey(autoGenerate = true)
    var surveyId: Long = 0
}

@Entity(tableName="survey_table")
data class Survey(val time: String,
                  val fieldId: Long) {
    @PrimaryKey(autoGenerate = true)
    var surveyId: Long = 0
    var complete: Long? = 0
}

@Entity(tableName="record_table")
data class Record(val surveyId: Long,
                  val species: String,
                  val sample: Int) {
    @PrimaryKey(autoGenerate = true)
    var recordId: Long = 0
}

// for querying
data class FieldAndSown(
    @Embedded val field: Field,
    @Relation(
        parentColumn = "fieldId",
        entityColumn = "fieldId"
    )
    val sownSpecies: List<Sown>
)

data class SurveyAndRecords(
    @Embedded val survey: Survey,
    @Relation(
        parentColumn = "surveyId",
        entityColumn = "surveyId"
    )
    val records: List<Record>
)


data class FieldWithSurveysAndRecords (
    @Embedded val field: Field,
    @Relation(
        entity = Survey::class,
        parentColumn = "fieldId",
        entityColumn = "fieldId"
    )
    val surveysAndRecords: List<SurveyAndRecords>
)

@Entity(tableName="settings_table")
data class Settings(@PrimaryKey var settingsId:Long = 0, val email: String)


