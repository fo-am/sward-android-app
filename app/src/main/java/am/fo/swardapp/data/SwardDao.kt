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
import androidx.room.*

@Dao
interface SwardDao {
    @Query("SELECT * from field_table ORDER BY name ASC")
    fun getFields(): LiveData<List<Field>>

    @Query("SELECT * from settings_table")
    fun getSettings(): LiveData<Settings>

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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSettings(settings: Settings): Long

    @Update
    suspend fun updateSettings(settings: Settings)

    @Transaction
    suspend fun setSettings(settings: Settings) {
        val id = insertSettings(settings)
        if (id == -1L) {
            updateSettings(settings)
        }
    }

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

    @Transaction
    @Query("SELECT * from survey_table Where fieldId=:fieldId")
    fun syncGetSurveys(fieldId: Long): List<Survey>

    @Transaction
    @Query("SELECT * from record_table Where surveyId=:surveyId")
    fun syncGetRecords(surveyId: Long): List<Record>

    @Transaction
    @Query("SELECT * from field_table")
    fun syncGetExportData(): List<FieldWithSurveysAndRecords>
}

