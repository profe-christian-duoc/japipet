
package cl.duoc.japipet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import android.util.Patterns
import androidx.compose.runtime.saveable.rememberSaveable
import cl.duoc.japipet.model.Cliente

@Composable
fun ClienteFormScreen(
    clienteEditar: Cliente? = null,
    onSave: (
        nombre: String,
        apellido: String,
        correo: String,
        telefono: String
    ) -> Unit,
    onCancel: () -> Unit
) {
    var nombre by rememberSaveable (clienteEditar?.id) {
        mutableStateOf(clienteEditar?.nombre.orEmpty())
    }
    var apellido by rememberSaveable(clienteEditar?.id) {
        mutableStateOf(clienteEditar?.apellido.orEmpty())
    }
    var correo by rememberSaveable(clienteEditar?.id) {
        mutableStateOf(clienteEditar?.correo.orEmpty())
    }
    var telefono by rememberSaveable(clienteEditar?.id) {
        mutableStateOf(clienteEditar?.telefono.orEmpty())
    }

    var intentoGuardar by rememberSaveable {
        mutableStateOf(false)
    }

    val nombreValido = nombre.isNotBlank()
    val apellidoValido = apellido.isNotBlank()
    val correoValido = Patterns.EMAIL_ADDRESS
        .matcher(correo.trim())
        .matches()
    val telefonoValido = telefono.trim().length >= 8 &&
            telefono.trim().all { it.isDigit() || it == '+' || it == ' ' }

    val formularioValido = nombreValido &&
            apellidoValido &&
            correoValido &&
            telefonoValido

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextButton(onClick = onCancel) {
            Text("← Volver")
        }

        Text(
            text = if (clienteEditar == null)
                "Registrar cliente"
            else
                "Editar cliente",
            style = MaterialTheme.typography.headlineMedium
        )

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            isError = intentoGuardar && !nombreValido,
            supportingText = {
                if (intentoGuardar && !nombreValido)
                    Text("Ingresa un nombre")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = apellido,
            onValueChange = { apellido = it },
            label = { Text("Apellido") },
            isError = intentoGuardar && !apellidoValido,
            supportingText = {
                if (intentoGuardar && !apellidoValido)
                    Text("Ingresa un apellido")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo electrónico") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            isError = intentoGuardar && !correoValido,
            supportingText = {
                if (intentoGuardar && !correoValido)
                    Text("Ingresa un correo válido")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            isError = intentoGuardar && !telefonoValido,
            supportingText = {
                if (intentoGuardar && !telefonoValido)
                    Text("Ingresa un teléfono válido")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                intentoGuardar = true
                if (formularioValido) {
                    onSave(
                        nombre.trim(),
                        apellido.trim(),
                        correo.trim(),
                        telefono.trim()
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (clienteEditar == null)
                    "Registrar cliente"
                else
                    "Guardar cambios"
            )
        }
    }
}
