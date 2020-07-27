package am.fo.swardapp.data

import androidx.lifecycle.LiveData

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO

// This class is a bit pointless, or at least appears so - but abstracts the DAO so
// we can replace it with other sources of data, or mock it for testing etc.
class SwardRepository(private val swardDao: SwardDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allFields: LiveData<List<Field>> = swardDao.getFields()

    fun getField(field_id: Int): LiveData<Field> = swardDao.getField(field_id)
    fun getSurveysForField(field: Field) = swardDao.getSurveysForField(field.field_id)
    fun getRecordForSurvey(survey: Survey) = swardDao.getRecordForSurvey(survey.survey_id)

    suspend fun insertField(field: Field) = swardDao.insertField(field)
    suspend fun insertSurvey(survey: Survey) = swardDao.insertSurvey(survey)
    suspend fun insertRecord(record: Record) = swardDao.insertRecord(record)
}