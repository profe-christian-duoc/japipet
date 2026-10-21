
package cl.duoc.japipet.data.local

import androidx.room3.Database
import androidx.room3.RoomDatabase
import cl.duoc.japipet.data.local.dao.ClienteDao
import cl.duoc.japipet.data.local.dao.MascotaDao
import cl.duoc.japipet.data.local.entity.ClienteEntity
import cl.duoc.japipet.data.local.entity.MascotaEntity

@Database(
    entities = [
        ClienteEntity::class,
        MascotaEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PetCareDatabase : RoomDatabase() {

    abstract fun clienteDao(): ClienteDao

    abstract fun mascotaDao(): MascotaDao
}
