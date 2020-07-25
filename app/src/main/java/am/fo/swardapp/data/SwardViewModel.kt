package am.fo.swardapp.data

import am.fo.swardapp.data.Field
import am.fo.swardapp.data.SwardRepository
import am.fo.swardapp.data.SwardRoomDatabase
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

    init {
        val swardDao = SwardRoomDatabase.getDatabase(application,viewModelScope).swardDao()
        repository = SwardRepository(swardDao)
        allFields = repository.allFields
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(field: Field) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(field)
    }
}