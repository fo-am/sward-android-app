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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertField(field: Field)

    @Query("DELETE FROM field_table")
    suspend fun deleteAllFields()
}

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class SwardRepository(private val swardDao: SwardDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allFields: LiveData<List<Field>> = swardDao.getFields()

    suspend fun insert(field: Field) {
        swardDao.insertField(field)
    }
}