/*
   Sward App Copyright (C) 2020 FoAM Kernow

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU Affero General Public License as
   published by the Free Software Foundation, either version 3 of the
   License, or (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU Affero General Public License for more details.

   You should have received a copy of the GNU Affero General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

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
    val settings: LiveData<Settings> = swardDao.getSettings()

    fun getField(fieldId: Long): LiveData<Field> = swardDao.getField(fieldId)

    fun getSown(fieldId: Long): LiveData<List<Sown>> =
        swardDao.getSown(fieldId)

    suspend fun setSettings(settings: Settings) = swardDao.setSettings(settings)

    suspend fun insertField(field: Field): Long = swardDao.insertField(field)
    suspend fun updateField(field: Field) = swardDao.updateField(field)
    suspend fun insertSurvey(survey: Survey): Long = swardDao.insertSurvey(survey)
    suspend fun insertSown(sown: Sown) = swardDao.insertSown(sown)
    suspend fun insertRecord(record: Record) = swardDao.insertRecord(record)

    suspend fun deleteAllFields() = swardDao.deleteAllFields()
    suspend fun deleteAllSown() = swardDao.deleteAllSown()
    suspend fun deleteAllSurveys() = swardDao.deleteAllSurveys()
    suspend fun deleteAllRecords() = swardDao.deleteAllRecords()
    suspend fun deleteField(fieldId: Long) = swardDao.deleteField(fieldId)
    suspend fun deleteSurveys(fieldId: Long) = swardDao.deleteSurveys(fieldId)
    suspend fun deleteRecords(surveyId: Long) = swardDao.deleteRecords(surveyId)

    // blocking calls
    fun syncGetSurveys(fieldId: Long): List<Survey> = swardDao.syncGetSurveys(fieldId)
    fun syncGetRecords(surveyId: Long): List<Record> = swardDao.syncGetRecords(surveyId)
    fun syncGetSurveysAndRecords(fieldId: Long, limit: Int): List<SurveyAndRecords> =
        swardDao.syncGetSurveysAndRecords(fieldId, limit)
    fun syncGetExportData(): List<FieldWithSurveysAndRecords> = swardDao.syncGetExportData()
}