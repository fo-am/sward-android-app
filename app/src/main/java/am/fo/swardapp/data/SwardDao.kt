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
    @Query("SELECT * from sown_table Where fieldId=:fieldId")
    fun getSown(fieldId: Long): LiveData<List<Sown>>



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
    @Query("DELETE FROM field_table where fieldId=:fieldId")
    suspend fun deleteField(fieldId: Long)
    @Query("DELETE FROM survey_table where fieldId=:fieldId")
    suspend fun deleteSurveys(fieldId: Long)
    @Query("DELETE FROM record_table where surveyId=:surveyId")
    suspend fun deleteRecords(surveyId: Long)

    // blocking versions
    @Transaction
    @Query("SELECT * from survey_table Where fieldId=:fieldId order by time desc limit :limit")
    fun syncGetSurveysAndRecords(fieldId: Long, limit: Int): List<SurveyAndRecords>
    @Query("SELECT * from survey_table Where fieldId=:fieldId")
    fun syncGetSurveys(fieldId: Long): List<Survey>
    @Query("SELECT * from record_table Where surveyId=:surveyId")
    fun syncGetRecords(surveyId: Long): List<Record>
}

