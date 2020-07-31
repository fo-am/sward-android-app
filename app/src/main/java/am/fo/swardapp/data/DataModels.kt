package am.fo.swardapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="field_table")
data class Field(val name: String,
                 val dateSown: String,
                 val soilType: Int,
                 val notes: String) {
    @PrimaryKey(autoGenerate = true)
    var fieldId:Int = 0
}

// need to pre-populate these
@Entity(tableName="species_table")
data class Species(val type: String) {
    @PrimaryKey(autoGenerate = true)
    var speciesId: Int = 0
}

@Entity(tableName="field_species_sown_table")
data class FieldSpeciesSown(@Embedded val field: Field,
                            @Embedded val species: Species) {
    @PrimaryKey(autoGenerate = true)
    var surveyId: Int = 0
}

@Entity(tableName="survey_table")
data class Survey(val time: String,
                  @Embedded val field: Field) {
    @PrimaryKey(autoGenerate = true)
    var surveyId: Int = 0
}

@Entity(tableName="survey_species_recorded_table")
data class SurveySpeciesRecorded(@Embedded val survey: Survey,
                                 @Embedded val species: Species,
                                 val sample: Int) {
    @PrimaryKey(autoGenerate = true)
    var recordId: Int = 0
}

