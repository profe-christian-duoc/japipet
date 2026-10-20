
package cl.duoc.japipet.viewmodel

import androidx.lifecycle.ViewModel
import cl.duoc.japipet.model.Cliente
import cl.duoc.japipet.model.ClientesEjemplo
import cl.duoc.japipet.model.Mascota
import cl.duoc.japipet.model.MascotasEjemplo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MascotaViewModel : ViewModel() {

    private val _clientes = MutableStateFlow(
        ClientesEjemplo.clientes
    )

    val clientes: StateFlow<List<Cliente>> =
        _clientes.asStateFlow()

    private val _mascotas = MutableStateFlow(
        MascotasEjemplo.mascotas
    )

    val mascotas: StateFlow<List<Mascota>> =
        _mascotas.asStateFlow()

    fun obtenerMascota(id: Int): Mascota? {
        return _mascotas.value.find { it.id == id }
    }

    fun agregarMascota(
        clienteId: Int,
        nombre: String,
        especie: String,
        raza: String,
        edad: Int,
        observaciones: String
    ): Boolean {
        if (!clienteExiste(clienteId)) return false

        val nuevoId =
            (_mascotas.value.maxOfOrNull { it.id } ?: 0) + 1

        val nuevaMascota = Mascota(
            id = nuevoId,
            clienteId = clienteId,
            nombre = nombre.trim(),
            especie = especie,
            raza = raza.trim(),
            edad = edad,
            observaciones = observaciones.trim()
        )

        _mascotas.update { lista ->
            lista + nuevaMascota
        }

        return true
    }

    fun actualizarMascota(
        mascotaActualizada: Mascota
    ): Boolean {
        if (!clienteExiste(mascotaActualizada.clienteId)) {
            return false
        }

        if (obtenerMascota(mascotaActualizada.id) == null) {
            return false
        }

        _mascotas.update { lista ->
            lista.map { mascota ->
                if (mascota.id == mascotaActualizada.id) {
                    mascotaActualizada
                } else {
                    mascota
                }
            }
        }

        return true
    }

    fun eliminarMascota(id: Int) {
        _mascotas.update { lista ->
            lista.filterNot { it.id == id }
        }
    }

    private fun clienteExiste(clienteId: Int): Boolean {
        return _clientes.value.any { it.id == clienteId }
    }
}
