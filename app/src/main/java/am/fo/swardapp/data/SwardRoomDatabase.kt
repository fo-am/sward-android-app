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

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities= [Settings::class, Field::class, Sown::class, Survey::class, Record::class], version = 4, exportSchema = false)
abstract class SwardRoomDatabase : RoomDatabase() {

    abstract fun swardDao(): SwardDao

    private class SwardDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val swardDao = database.swardDao()
/*
                    // Add sample fields
                    val fieldId = swardDao.insertField(Field("Top field", "", 0, ""))
                    swardDao.insertSown(Sown(fieldId,"grass_cocksfoot"))
                    swardDao.insertSown(Sown(fieldId,"grass_timothy"))
                    swardDao.insertSown(Sown(fieldId,"herb_sheepsburnet"))
                    swardDao.insertSown(Sown(fieldId,"legume_sainfoin"))
                    val surveyId = swardDao.insertSurvey(Survey("2020-08-10",fieldId))
                    swardDao.insertRecord(Record(surveyId,"grass_cocksfoot",1))
                    swardDao.insertRecord(Record(surveyId,"herb_sheepsburnet",1))
                    swardDao.insertRecord(Record(surveyId,"legume_sainfoin",1))
                    swardDao.insertRecord(Record(surveyId,"grass_cocksfoot",2))

                    swardDao.insertField(Field("Marshy field", "", 0, ""))
*/

                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            /*INSTANCE?.let { database ->
                scope.launch {
                    val swardDao = database.swardDao()
                }
            }*/
        }
    }

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SwardRoomDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
             // remove species and replaced with string
             /*database.execSQL("""
                CREATE TABLE new_Song (
                    id INTEGER PRIMARY KEY NOT NULL,
                    name TEXT,
                    tag TEXT NOT NULL DEFAULT ''
                )
                """.trimIndent())
        database.execSQL("""
                INSERT INTO new_Song (id, name, tag)
                SELECT id, name, tag FROM Song
                """.trimIndent())
        database.execSQL("DROP TABLE Song")
        database.execSQL("ALTER TABLE new_Song RENAME TO Song")
            */
            }
        }

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
                        "sward_database")
                    //.addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration() // TODO: remove
                    .addCallback(SwardDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
   }
}
