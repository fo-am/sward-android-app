package am.fo.swardapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SwardDao {
    @Query("SELECT * from field_table ORDER BY name ASC")
    fun getFields(): LiveData<List<Field>>

    @Query("SELECT * from field_table Where fieldId=:fieldId")
    fun getField(fieldId: Long): LiveData<Field>

    @Transaction
    @Query("SELECT * from field_table Where fieldId=:fieldId")
    fun getFieldAndSown(fieldId: Long): LiveData<FieldAndSown>

    @Query("SELECT * from survey_table Where fieldId=:fieldId")
    fun getSurveys(fieldId: Long): LiveData<List<Survey>>

    @Transaction
    @Query("SELECT * from survey_table Where surveyId=:surveyId")
    fun getSurveyAndRecordedSpecies(surveyId: Long): LiveData<SurveyAndRecords>

    @Transaction
    @Query("SELECT * from field_table Where fieldId=:fieldId")
    fun getFieldWithSurveysAndSpecies(fieldId: Long): LiveData<FieldWithSurveysAndRecords>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertField(field: Field): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSurvey(survey: Survey): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSown(sown: Sown): Long
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecord(recorded: Record): Long


    @Query("DELETE FROM field_table")
    suspend fun deleteAllFields()
}

