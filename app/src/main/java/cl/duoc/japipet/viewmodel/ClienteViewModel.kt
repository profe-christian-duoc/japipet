
package cl.duoc.japipet.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.japipet.data.repository.ClienteRepository
import cl.duoc.japipet.data.repository.ResultadoEliminarCliente
import cl.duoc.japipet.model.Cliente
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClienteViewModel @Inject constructor(
    private val clienteRepository: ClienteRepository
) : ViewModel() {

    val clientes = clienteRepository.clientes.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun agregarCliente(
        nombre: String,
        apellido: String,
        correo: String,
        telefono: String,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val resultado = clienteRepository.agregarCliente(
                nombre = nombre,
                apellido = apellido,
                correo = correo,
                telefono = telefono
            )

            onResult(resultado)
        }
    }

    fun actualizarCliente(
        cliente: Cliente,
        onResult: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            val resultado = clienteRepository.actualizarCliente(cliente)
            onResult(resultado)
        }
    }

    fun eliminarCliente(
        id: Int,
        onResult: (ResultadoEliminarCliente) -> Unit
    ) {
        viewModelScope.launch {
            val resultado = clienteRepository.eliminarCliente(id)
            onResult(resultado)
        }
    }
}
