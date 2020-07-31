package am.fo.swardapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities=arrayOf(Field::class,
    Species::class,
    FieldSpeciesSown::class,
    Survey::class,
    SurveySpeciesRecorded::class), version = 1, exportSchema = false)
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

                    // Add sample fields
                    var field = Field("Top field", "", 0, "")
                    swardDao.insertField(field)
                    field = Field("Marshy field", "", 0, "")
                    swardDao.insertField(field)

                    // add all species here - doesn't matter if they already exist
                    // and allows us to add more species without a database version bump
                    swardDao.insertSpecies(Species("grass_cocksfoot"))
                    swardDao.insertSpecies(Species("grass_meadowfescue"))
                    swardDao.insertSpecies(Species("grass_meadowfoxtail"))
                    swardDao.insertSpecies(Species("grass_perennialryegrass"))
                    swardDao.insertSpecies(Species("grass_tallfescue"))
                    swardDao.insertSpecies(Species("grass_timothy"))
                    swardDao.insertSpecies(Species("herb_chicory"))
                    swardDao.insertSpecies(Species("herb_ribwort"))
                    swardDao.insertSpecies(Species("herb_sheepsburnet"))
                    swardDao.insertSpecies(Species("herb_sheepsparsley"))
                    swardDao.insertSpecies(Species("herb_yarrow"))
                    swardDao.insertSpecies(Species("legume_alsike"))
                    swardDao.insertSpecies(Species("legume_birdsfootrefoil"))
                    swardDao.insertSpecies(Species("legume_lucern"))
                    swardDao.insertSpecies(Species("legume_redclover"))
                    swardDao.insertSpecies(Species("legume_sainfoin"))
                    swardDao.insertSpecies(Species("legume_whiteclover"))
                }
            }
        }

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val swardDao = database.swardDao()
                }
            }
        }
    }

   companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SwardRoomDatabase? = null

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

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
                    .addCallback(SwardDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
   }
}
