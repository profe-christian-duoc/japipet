
package cl.duoc.japipet.di

import android.content.Context
import androidx.room3.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import cl.duoc.japipet.data.local.PetCareDatabase
import cl.duoc.japipet.data.local.dao.ClienteDao
import cl.duoc.japipet.data.local.dao.MascotaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): PetCareDatabase {
        return Room.databaseBuilder<PetCareDatabase>(
            context,
            "petcare_database"
        )
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    @Provides
    fun provideClienteDao(
        database: PetCareDatabase
    ): ClienteDao {
        return database.clienteDao()
    }

    @Provides
    fun provideMascotaDao(
        database: PetCareDatabase
    ): MascotaDao {
        return database.mascotaDao()
    }
}
