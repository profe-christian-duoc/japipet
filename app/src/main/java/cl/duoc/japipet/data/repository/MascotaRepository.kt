
package cl.duoc.japipet.data.repository

import cl.duoc.japipet.model.Cliente
import cl.duoc.japipet.model.Mascota
import kotlinx.coroutines.flow.Flow

interface MascotaRepository {

    val clientes: Flow<List<Cliente>>

    val mascotas: Flow<List<Mascota>>

    suspend fun agregarMascota(
        clienteId: Int,
        nombre: String,
        especie: String,
        raza: String,
        edad: Int,
        observaciones: String
    ): Boolean

    suspend fun actualizarMascota(
        mascota: Mascota
    ): Boolean

    suspend fun eliminarMascota(
        id: Int
    ): Boolean
}
