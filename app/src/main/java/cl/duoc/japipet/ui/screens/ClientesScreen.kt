
package cl.duoc.japipet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duoc.japipet.model.Cliente

@Composable
fun ClientesScreen(
    clientes: List<Cliente>,
    onBack: () -> Unit,
    onAddCliente: () -> Unit,
    onEditCliente: (Int) -> Unit,
    onDeleteCliente: (Int) -> Unit
) {
    var clientePendienteEliminar by remember {
        mutableStateOf<Cliente?>(null)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        TextButton(onClick = onBack) {
            Text("← Volver")
        }

        Text(
            text = "Gestión de clientes",
            style = MaterialTheme.typography.headlineMedium
        )

        Button(
            onClick = onAddCliente,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar cliente")
        }

        if (clientes.isEmpty()) {
            Text("Todavía no hay clientes registrados.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = clientes,
                    key = { it.id }
                ) { cliente ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement =
                                Arrangement.spacedBy(8.dp)
                        ) {

                            Text(
                                text = cliente.nombreCompleto,
                                style = MaterialTheme.typography.titleMedium
                            )

                            Text(cliente.correo)
                            Text(cliente.telefono)

                            Row(
                                horizontalArrangement =
                                    Arrangement.spacedBy(8.dp)
                            ) {
                                OutlinedButton(
                                    onClick = {
                                        onEditCliente(cliente.id)
                                    }
                                ) {
                                    Text("Editar")
                                }

                                TextButton(
                                    onClick = {
                                        clientePendienteEliminar = cliente
                                    }
                                ) {
                                    Text("Eliminar")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    clientePendienteEliminar?.let { cliente ->
        AlertDialog(
            onDismissRequest = {
                clientePendienteEliminar = null
            },
            title = {
                Text("Eliminar cliente")
            },
            text = {
                Text(
                    "¿Deseas eliminar a ${cliente.nombreCompleto}?"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteCliente(cliente.id)
                        clientePendienteEliminar = null
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        clientePendienteEliminar = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}
