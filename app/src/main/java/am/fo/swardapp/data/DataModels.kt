package am.fo.swardapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName="field_table")
data class Field(val name: String,
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


