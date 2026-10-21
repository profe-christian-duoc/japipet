
package cl.duoc.japipet.data.repository

import cl.duoc.japipet.data.local.dao.ClienteDao
import cl.duoc.japipet.data.local.dao.MascotaDao
import cl.duoc.japipet.data.local.entity.MascotaEntity
import cl.duoc.japipet.data.toEntity
import cl.duoc.japipet.data.toModel
import cl.duoc.japipet.model.Cliente
import cl.duoc.japipet.model.Mascota
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MascotaLocalRepository @Inject constructor(
    private val clienteDao: ClienteDao,
    private val mascotaDao: MascotaDao
) : MascotaRepository {

    override val clientes: Flow<List<Cliente>> =
        clienteDao.observarClientes().map { entidades ->
            entidades.map { it.toModel() }
        }

    override val mascotas: Flow<List<Mascota>> =
        mascotaDao.observarMascotas().map { entidades ->
            entidades.map { it.toModel() }
        }

    override suspend fun agregarMascota(
        clienteId: Int,
        nombre: String,
        especie: String,
        raza: String,
        edad: Int,
        observaciones: String
    ): Boolean {

        if (clienteDao.obtenerPorId(clienteId) == null) {
            return false
        }

        mascotaDao.insertar(
            MascotaEntity(
                clienteId = clienteId,
                nombre = nombre.trim(),
                especie = especie,
                raza = raza.trim(),
                edad = edad,
                observaciones = observaciones.trim()
            )
        )

        return true
    }

    override suspend fun actualizarMascota(
        mascota: Mascota
    ): Boolean {

        if (clienteDao.obtenerPorId(mascota.clienteId) == null) {
            return false
        }

        return mascotaDao.actualizar(
            mascota.toEntity()
        ) > 0
    }

    override suspend fun eliminarMascota(
        id: Int
    ): Boolean {

        val mascota = mascotaDao.obtenerPorId(id)
            ?: return false

        return mascotaDao.eliminar(mascota) > 0
    }
}
