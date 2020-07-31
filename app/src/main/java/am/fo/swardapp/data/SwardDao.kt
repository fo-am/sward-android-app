package am.fo.swardapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SwardDao {
    @Query("SELECT * from field_table ORDER BY name ASC")
    fun getFields(): LiveData<List<Field>>

    @Query("SELECT * from field_table Where fieldId=:fieldId ORDER BY name ASC")
    fun getField(fieldId: Int): LiveData<Field>

    @Query("SELECT * from survey_table WHERE fieldId=:fieldId")
    fun getSurveysForField(fieldId: Int): LiveData<List<Survey>>

    @Query("SELECT * from species_table")
    fun getSpecies(): LiveData<List<Species>>

    @Query("SELECT * from field_species_sown_table WHERE fieldId=:fieldId")
    fun getSpeciesSownForField(fieldId: Int): LiveData<List<FieldSpeciesSown>>

    @Query("SELECT * from survey_species_recorded_table WHERE surveyId=:surveyId")
    fun getSpeciesRecordedForSurvey(surveyId: Int): LiveData<List<SurveySpeciesRecorded>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertField(field: Field)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSurvey(survey: Survey)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpecies(record: Species)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFieldSpeciesSown(sown: FieldSpeciesSown)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSurveySpeciesRecorded(sown: SurveySpeciesRecorded)

    @Query("DELETE FROM field_table")
    suspend fun deleteAllFields()
}

