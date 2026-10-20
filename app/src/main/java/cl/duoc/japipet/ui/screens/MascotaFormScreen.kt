package cl.duoc.japipet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cl.duoc.japipet.model.*
import cl.duoc.japipet.ui.components.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MascotaFormScreen(
    clientes: List<Cliente>,
    mascotaEditar: Mascota? = null,
    onSave: (
        clienteId: Int,
        nombre: String,
        especie: String,
        raza: String,
        edad: Int,
        observaciones: String
    ) -> Unit,
    onCancel: () -> Unit
) {

    var nombre by rememberSaveable(mascotaEditar?.id) {
        mutableStateOf(mascotaEditar?.nombre ?: "")
    }

    var especie by rememberSaveable(mascotaEditar?.id) {
        mutableStateOf(mascotaEditar?.especie ?: "Perro")
    }

    var raza by rememberSaveable(mascotaEditar?.id) {
        mutableStateOf(mascotaEditar?.raza ?: "")
    }

    var edad by rememberSaveable(mascotaEditar?.id) {
        mutableStateOf(mascotaEditar?.edad?.toString() ?: "")
    }

    var observaciones by rememberSaveable(mascotaEditar?.id) {
        mutableStateOf(mascotaEditar?.observaciones ?: "")
    }

    var mostrarErrores by rememberSaveable(mascotaEditar?.id) {
        mutableStateOf(false)
    }
    var clienteId by rememberSaveable(mascotaEditar?.id) {
        mutableStateOf(mascotaEditar?.clienteId)
    }

    var menuClientesExpandido by remember {
        mutableStateOf(false)
    }

    val clienteSeleccionado = clientes.find {
        it.id == clienteId
    }

    val clienteValido = clienteSeleccionado != null

    val nombreValido = nombre.isNotBlank()
    val razaValida = raza.isNotBlank()
    val edadNumero = edad.toIntOrNull()
    val edadValida = edadNumero != null && edadNumero in 0..40

    val formularioValido =
        clienteValido &&
                nombreValido &&
                razaValida &&
                edadValida

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Surface(tonalElevation = 3.dp) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (mascotaEditar == null) {
                            "Registrar mascota"
                        } else {
                            "Editar mascota"
                        },
                        style = MaterialTheme.typography.titleLarge
                    )

                    TextButton(onClick = onCancel) {
                        Text("Volver")
                    }
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Datos de la mascota",
                style = MaterialTheme.typography.headlineSmall
            )

            ExposedDropdownMenuBox (
                expanded = menuClientesExpandido,
                onExpandedChange = {
                    menuClientesExpandido = it
                }
            ) {
                OutlinedTextField(
                    value = clienteSeleccionado?.nombreCompleto ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Propietario") },
                    placeholder = { Text("Seleccionar cliente") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = menuClientesExpandido
                        )
                    },
                    isError = mostrarErrores && !clienteValido,
                    supportingText = {
                        if (mostrarErrores && !clienteValido) {
                            Text("Selecciona un cliente existente")
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(
                            ExposedDropdownMenuAnchorType.PrimaryNotEditable
                        )
                )

                ExposedDropdownMenu (
                    expanded = menuClientesExpandido,
                    onDismissRequest = {
                        menuClientesExpandido = false
                    }
                ) {
                    clientes.forEach { cliente ->
                        DropdownMenuItem(
                            text = {
                                Text(cliente.nombreCompleto)
                            },
                            onClick = {
                                clienteId = cliente.id
                                menuClientesExpandido = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                singleLine = true,
                isError = mostrarErrores && !nombreValido,
                supportingText = {
                    if (mostrarErrores && !nombreValido) {
                        Text("Ingresa el nombre de la mascota")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Especie",
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf("Perro", "Gato", "Otro").forEach { opcion ->
                    FilterChip(
                        selected = especie == opcion,
                        onClick = { especie = opcion },
                        label = { Text(opcion) }
                    )
                }
            }

            OutlinedTextField(
                value = raza,
                onValueChange = { raza = it },
                label = { Text("Raza") },
                singleLine = true,
                isError = mostrarErrores && !razaValida,
                supportingText = {
                    if (mostrarErrores && !razaValida) {
                        Text("Ingresa la raza")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = edad,
                onValueChange = { valor ->
                    if (valor.all { it.isDigit() }) {
                        edad = valor
                    }
                },
                label = { Text("Edad en años") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                isError = mostrarErrores && !edadValida,
                supportingText = {
                    if (mostrarErrores && !edadValida) {
                        Text("Ingresa una edad entre 0 y 40 años")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = observaciones,
                onValueChange = { observaciones = it },
                label = { Text("Observaciones (opcional)") },
                minLines = 3,
                maxLines = 5,
                modifier = Modifier.fillMaxWidth()
            )

            PetCareButton(
                text = if (mascotaEditar == null) {
                    "Guardar mascota"
                } else {
                    "Guardar cambios"
                },
                onClick = {
                    mostrarErrores = true

                    val propietarioId = clienteId
                    val edadValidada = edadNumero

                    if (
                        formularioValido &&
                        propietarioId != null &&
                        edadValidada != null
                    ) {
                        onSave(
                            propietarioId,
                            nombre.trim(),
                            especie,
                            raza.trim(),
                            edadValidada,
                            observaciones.trim()
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            PetCareButton(
                text = "Cancelar",
                onClick = onCancel,
                style = PetCareButtonStyle.SECONDARY,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
