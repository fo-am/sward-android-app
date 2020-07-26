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

    @Query("SELECT * from species_table WHERE survey_id=:survey_id")
    fun getSpeciesForSurvey(survey_id: Int): LiveData<List<Species>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertField(field: Field)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSurvey(survey: Survey)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSpecies(species: Species)

    @Query("DELETE FROM field_table")
    suspend fun deleteAllFields()
}

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class SwardRepository(private val swardDao: SwardDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allFields: LiveData<List<Field>> = swardDao.getFields()

    fun getField(field_id: Int) { swardDao.getField(field_id) }
    fun getSurveysForField(field: Field) { swardDao.getSurveysForField(field.field_id) }
    fun getSpeciesForSurvey(survey: Survey) { swardDao.getSpeciesForSurvey(survey.survey_id) }

    suspend fun insertField(field: Field) { swardDao.insertField(field) }
    suspend fun insertSurvey(survey: Survey) { swardDao.insertSurvey(survey) }
    suspend fun insertSpecies(species: Species) { swardDao.insertSpecies(species) }
}