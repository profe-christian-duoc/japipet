
package cl.duoc.japipet.data.repository

import cl.duoc.japipet.model.Cliente
import kotlinx.coroutines.flow.Flow

interface ClienteRepository {

    val clientes: Flow<List<Cliente>>

    suspend fun obtenerCliente(id: Int): Cliente?

    suspend fun agregarCliente(
        nombre: String,
        apellido: String,
        correo: String,
        telefono: String
    ): Boolean

    suspend fun actualizarCliente(cliente: Cliente): Boolean

    suspend fun eliminarCliente(id: Int): ResultadoEliminarCliente

    suspend fun prepararClientesIniciales()
}

enum class ResultadoEliminarCliente {
    ELIMINADO,
    TIENE_MASCOTAS,
    NO_ENCONTRADO
}
