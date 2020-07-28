package am.fo.swardapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="field_table")
data class Field(val name: String,
                 val dateSown:String,
                 val soilType:Int,
                 val notes:String) {
    @PrimaryKey(autoGenerate = true)
    var fieldId:Int = 0
}

@Entity(tableName="survey_table")
data class Survey(val time: String,
                  @Embedded val field: Field) {
    @PrimaryKey(autoGenerate = true)
    var surveyId:Int = 0
}

@Entity(tableName="record_table")
data class Record(val sample: Int,
                   val type: String,
                   @Embedded val survey: Survey) {
    @PrimaryKey(autoGenerate = true)
    var recordId: Int = 0
}
