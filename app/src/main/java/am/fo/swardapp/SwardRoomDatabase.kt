package am.fo.swardapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Sward::class), version = 1, exportSchema = false)
public abstract class SwardRoomDatabase : RoomDatabase() {

   abstract fun swardDao(): SwardDao

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SwardRoomDatabase? = null

        fun getDatabase(context: Context): SwardRoomDatabase {
            val tempInstance = INSTANCE
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
