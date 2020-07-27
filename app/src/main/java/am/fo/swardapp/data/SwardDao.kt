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

    @Query("SELECT * from field_table Where field_id=:field_id ORDER BY name ASC")
    fun getField(field_id: Int): LiveData<Field>

    @Query("SELECT * from survey_table WHERE field_id=:field_id")
    fun getSurveysForField(field_id: Int): LiveData<List<Survey>>

    @Query("SELECT * from record_table WHERE survey_id=:survey_id")
    fun getRecordForSurvey(survey_id: Int): LiveData<List<Record>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertField(field: Field)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSurvey(survey: Survey)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecord(record: Record)

    @Query("DELETE FROM field_table")
    suspend fun deleteAllFields()
}

