package am.fo.swardapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Field::class), version = 1, exportSchema = false)
public abstract class SwardRoomDatabase : RoomDatabase() {

    abstract fun swardDao(): SwardDao

    private class FieldDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var swardDao = database.swardDao()

                    // Delete all content here.
                    swardDao.deleteAllFields()

                    // Add sample words.
                    var field = Field("Top field")
                    swardDao.insertField(field)
                    field = Field("Marshy field")
                    swardDao.insertField(field)
                }
            }
        }
    }

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SwardRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): SwardRoomDatabase {

            //context.deleteDatabase("sward_database")

            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        SwardRoomDatabase::class.java,
                        "sward_database"
                    ).build()
                INSTANCE = instance
                return instance
            }
        }
   }
}