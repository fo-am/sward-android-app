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

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SwardViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SwardRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allFields: LiveData<List<Field>>
    val settings: LiveData<Settings>

    fun getField(field_id: Long) : LiveData<Field> = repository.getField(field_id)
    fun getSown(fieldId: Long) : LiveData<List<Sown>> = repository.getSown(fieldId)

    init {
        val swardDao = SwardRoomDatabase.getDatabase(application,viewModelScope).swardDao()
        repository = SwardRepository(swardDao)
        allFields = repository.allFields
        settings = repository.settings
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertFieldWithSpeciesSown(field: Field, sown: List<String>) = viewModelScope.launch(Dispatchers.IO) {
        val fieldId = repository.insertField(field)
        sown.forEach { species ->
            repository.insertSown(Sown(fieldId,species))
        }
    }

    fun updateField(field: Field) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateField(field)
    }

    fun setSettings(settings: Settings) = viewModelScope.launch(Dispatchers.IO) {
        repository.setSettings(settings)
    }

    fun insertSurvey(survey: Survey): LiveData<Long> {
        val liveData = MutableLiveData<Long>()
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(repository.insertSurvey(survey))
        }
        return liveData
    }

    fun insertRecord(record: Record) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertRecord(record)
    }

    fun deleteField(fieldId: Long) = viewModelScope.launch(Dispatchers.IO) {
        // delete all survey records for this field
        repository.syncGetSurveys(fieldId).forEach {
            repository.deleteRecords(it.surveyId)
        }
        // delete all the surveys
        repository.deleteSurveys(fieldId)
        // delete the actual field
        repository.deleteField(fieldId)
    }

    // data processing - general idea is to do as much of it here as possible, outside the UI thread
    data class BiodiversityItem(val date: String, val biodiversity: Int)

    // diversity of each survey for this field
    // where diversity is the number of distinct species across the survey
    fun getBiodiversity(fieldId: Long, limit: Int): LiveData<List<BiodiversityItem>> {
        val liveData = MutableLiveData<List<BiodiversityItem>>()
        viewModelScope.launch(Dispatchers.IO) {
            val ret = mutableListOf<BiodiversityItem>()
            repository.syncGetSurveysAndRecords(fieldId, limit).forEach { surveyAndRecords ->
                val biodiversity = mutableSetOf<String>()
                surveyAndRecords.records.forEach {
                    biodiversity.add(it.species)
                }
                ret.add(
                    BiodiversityItem(
                        surveyAndRecords.survey.time,
                        biodiversity.size
                    )
                )
            }
            ret.sortBy { it.date }
            liveData.postValue(ret)
        }
        return liveData
    }

    // for the detailed field view, we need to more or less display the inverse of
    // what has been recorded - the counts of species for each survey
    data class SpeciesSurveyCount(val survey: Survey, val count: Int)

    fun getSpeciesAndSurveyCounts(fieldId: Long): LiveData<Map<String,List<SpeciesSurveyCount>>> {
        val liveData = MutableLiveData<Map<String,List<SpeciesSurveyCount>>>()
        viewModelScope.launch(Dispatchers.IO) {
            // doing this off the main UI thread like a good person!
            val ret = mutableMapOf<String,MutableList<SpeciesSurveyCount>>()

            repository.syncGetSurveys(fieldId).forEach { survey ->
                // count each species for this survey
                val diversity = mutableMapOf<String,Int>()
                repository.syncGetRecords(survey.surveyId).forEach { record ->
                    if (diversity.containsKey(record.species)) {
                        diversity[record.species]?.let {
                            diversity[record.species]=it+1
                        }
                    } else {
                        diversity[record.species]=1
                    }
                }

                // add this survey to the main list
                for (speciesCount in diversity) {
                    val ssc = SpeciesSurveyCount(survey,speciesCount.value)
                    // if it doesn't exist make a list
                    if (!ret.containsKey(speciesCount.key)) {
                        ret[speciesCount.key]=mutableListOf()
                    }
                    ret[speciesCount.key]!!.add(ssc)
                }
            }
            liveData.postValue(ret)
        }
        return liveData
    }

    fun getExportData(): LiveData<List<FieldWithSurveysAndRecords>> {
        val liveData = MutableLiveData<List<FieldWithSurveysAndRecords>>()
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(repository.syncGetExportData())
        }
        return liveData
    }

}