package cl.duoc.japipet.data.local

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

object DatabaseProvider {

    @Volatile
    private var INSTANCE: PetCareDatabase? = null

    fun getDatabase(context: Context): PetCareDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder<PetCareDatabase>(
                context.applicationContext,
                "petcare_database"
            )
                .setDriver(BundledSQLiteDriver())
                .build()
                .also { INSTANCE = it }
        }
    }
}
