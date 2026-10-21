package cl.duoc.japipet.data.local.dao

import androidx.room3.*
import cl.duoc.japipet.data.local.entity.MascotaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MascotaDao {

    @Query("SELECT * FROM mascotas ORDER BY id")
    fun observarMascotas(): Flow<List<MascotaEntity>>

    @Query(
        "SELECT * FROM mascotas " +
                "WHERE clienteId = :clienteId ORDER BY nombre"
    )
    fun observarPorCliente(
        clienteId: Int
    ): Flow<List<MascotaEntity>>

    @Query("SELECT * FROM mascotas WHERE id = :id LIMIT 1")
    suspend fun obtenerPorId(id: Int): MascotaEntity?

    @Query("SELECT COUNT(*) FROM mascotas")
    suspend fun contarMascotas(): Int

    @Insert
    suspend fun insertar(mascota: MascotaEntity): Long

    @Update
    suspend fun actualizar(mascota: MascotaEntity): Int

    @Delete
    suspend fun eliminar(mascota: MascotaEntity): Int
}
