package cl.duoc.japipet.data.repository

import android.content.Context
import cl.duoc.japipet.data.local.dao.ClienteDao
import cl.duoc.japipet.data.local.entity.ClienteEntity
import cl.duoc.japipet.data.toEntity
import cl.duoc.japipet.data.toModel
import cl.duoc.japipet.model.Cliente
import cl.duoc.japipet.model.ClientesEjemplo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ClienteLocalRepository @Inject constructor(
    private val clienteDao: ClienteDao,
    @ApplicationContext private val context: Context
) : ClienteRepository {

    override val clientes: Flow<List<Cliente>> =
        clienteDao.observarClientes().map { entidades ->
            entidades.map { it.toModel() }
        }

    override suspend fun obtenerCliente(id: Int): Cliente? {
        return clienteDao.obtenerPorId(id)?.toModel()
    }

    override suspend fun agregarCliente(
        nombre: String,
        apellido: String,
        correo: String,
        telefono: String
    ): Boolean {
        clienteDao.insertar(
            ClienteEntity(
                nombre = nombre.trim(),
                apellido = apellido.trim(),
                correo = correo.trim(),
                telefono = telefono.trim()
            )
        )
        return true
    }

    override suspend fun actualizarCliente(
        cliente: Cliente
    ): Boolean {
        return clienteDao.actualizar(
            cliente.toEntity()
        ) > 0
    }

    override suspend fun eliminarCliente(
        id: Int
    ): ResultadoEliminarCliente {
        val cliente = clienteDao.obtenerPorId(id)
            ?: return ResultadoEliminarCliente.NO_ENCONTRADO

        if (clienteDao.contarMascotasDeCliente(id) > 0) {
            return ResultadoEliminarCliente.TIENE_MASCOTAS
        }

        return if (clienteDao.eliminar(cliente) > 0) {
            ResultadoEliminarCliente.ELIMINADO
        } else {
            ResultadoEliminarCliente.NO_ENCONTRADO
        }
    }

    override suspend fun prepararClientesIniciales() {
        val preferencias = context.getSharedPreferences(
            "petcare_inicializacion",
            Context.MODE_PRIVATE
        )

        if (preferencias.getBoolean("clientes_creados", false)) {
            return
        }

        if (clienteDao.contarClientes() == 0) {
            ClientesEjemplo.clientes.forEach { cliente ->
                clienteDao.insertar(cliente.toEntity())
            }
        }

        preferencias.edit()
            .putBoolean("clientes_creados", true)
            .apply()
    }
}
