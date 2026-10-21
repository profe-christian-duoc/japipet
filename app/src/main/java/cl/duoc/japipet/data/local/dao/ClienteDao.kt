
package cl.duoc.japipet.data.local.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import androidx.room3.Delete
import cl.duoc.japipet.data.local.entity.ClienteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClienteDao {

    @Query("SELECT * FROM clientes ORDER BY nombre")
    fun observarClientes(): Flow<List<ClienteEntity>>

    @Query("SELECT * FROM clientes WHERE id = :id LIMIT 1")
    suspend fun obtenerPorId(id: Int): ClienteEntity?

    @Query("SELECT COUNT(*) FROM clientes")
    suspend fun contarClientes(): Int

    @Insert
    suspend fun insertar(cliente: ClienteEntity): Long

    @Update
    suspend fun actualizar(cliente: ClienteEntity): Int

    @Delete
    suspend fun eliminar(cliente: ClienteEntity): Int

    @Query(
        "SELECT COUNT(*) FROM mascotas WHERE clienteId = :clienteId"
    )
    suspend fun contarMascotasDeCliente(clienteId: Int): Int
}
