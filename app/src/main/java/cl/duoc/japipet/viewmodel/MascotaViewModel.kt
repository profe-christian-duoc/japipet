package cl.duoc.japipet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.japipet.data.repository.ClienteRepository
import cl.duoc.japipet.data.repository.MascotaRepository
import cl.duoc.japipet.model.Mascota
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MascotaViewModel @Inject constructor(
    private val mascotaRepository: MascotaRepository,
    private val clienteRepository: ClienteRepository
) : ViewModel() {

    val clientes = mascotaRepository.clientes.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val mascotas = mascotaRepository.mascotas.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    init {
        viewModelScope.launch {
            clienteRepository.prepararClientesIniciales()
        }
    }

    fun agregarMascota(
        clienteId: Int,
        nombre: String,
        especie: String,
        raza: String,
        edad: Int,
        observaciones: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val resultado = mascotaRepository.agregarMascota(
                clienteId = clienteId,
                nombre = nombre,
                especie = especie,
                raza = raza,
                edad = edad,
                observaciones = observaciones
            )
            onResult(resultado)
        }
    }

    fun actualizarMascota(
        mascota: Mascota,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val resultado =
                mascotaRepository.actualizarMascota(mascota)
            onResult(resultado)
        }
    }

    fun eliminarMascota(id: Int) {
        viewModelScope.launch {
            mascotaRepository.eliminarMascota(id)
        }
    }
}
