package am.fo.swardapp

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="field_table")
data class Field(@PrimaryKey val name: String)

@Entity(tableName="survey_table")
data class Survey(@PrimaryKey(autoGenerate = true) val id: Int,
                  val time: String,
                  @Embedded val trip: Field)

@Entity(tableName="species_table")
data class Species(@PrimaryKey(autoGenerate = true) val id: Int,
                  val sample: Int,
                  val type: String,
                  @Embedded val survey: Survey)
