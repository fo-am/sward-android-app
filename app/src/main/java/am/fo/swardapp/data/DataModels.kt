package am.fo.swardapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="field_table")
data class Field(val name: String,
                 val dateSown:String,
                 val soilType:String,
                 val notes:String) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}

@Entity(tableName="survey_table")
data class Survey(@PrimaryKey(autoGenerate = true) val id: Int,
                  val time: String,
                  @Embedded val trip: Field
)

@Entity(tableName="species_table")
data class Species(@PrimaryKey(autoGenerate = true) val id: Int,
                  val sample: Int,
                  val type: String,
                  @Embedded val survey: Survey
)
