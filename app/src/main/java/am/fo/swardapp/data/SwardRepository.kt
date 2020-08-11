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

    fun getField(fieldId: Long): LiveData<Field> = swardDao.getField(fieldId)

    fun getSown(fieldId: Long): LiveData<List<Sown>> =
        swardDao.getSown(fieldId)

    fun getSurveysAndRecords(fieldId: Long): LiveData<List<SurveyAndRecords>> = swardDao.getSurveysAndRecords(fieldId)

    suspend fun insertField(field: Field) : Long = swardDao.insertField(field)
    suspend fun insertSurvey(survey: Survey) = swardDao.insertSurvey(survey)
    suspend fun insertSown(sown: Sown) = swardDao.insertSown(sown)
    suspend fun insertRecord(record: Record) = swardDao.insertRecord(record)

}