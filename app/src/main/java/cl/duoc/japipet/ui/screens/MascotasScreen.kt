package cl.duoc.japipet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duoc.japipet.model.Cliente
import cl.duoc.japipet.model.Mascota
import cl.duoc.japipet.ui.components.MascotaCard
import cl.duoc.japipet.ui.components.PetCareButton

@Composable
fun MascotasScreen(
    mascotas: List<Mascota>,
    clientes: List<Cliente>,
    onBack: () -> Unit,
    onAddMascota: () -> Unit,
    onEditMascota: (Int) -> Unit,
    onDeleteMascota: (Int) -> Unit
) {
    var mascotaAEliminar by remember {
        mutableStateOf<Mascota?>(null)
    }

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
                        text = "Mascotas",
                        style = MaterialTheme.typography.titleLarge
                    )

                    TextButton(onClick = onBack) {
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
                .padding(20.dp)
        ) {
            Text(
                text = "Mascotas registradas",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "${mascotas.size} registros encontrados",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = mascotas,
                    key = { it.id }
                ) { mascota ->

                    val propietario = clientes.find {
                        it.id == mascota.clienteId
                    }

                    MascotaCard(
                        mascota = mascota,
                        propietario = propietario?.nombreCompleto
                            ?: "Propietario no encontrado",
                        onEdit = {
                            onEditMascota(mascota.id)
                        },
                        onDelete = {
                            mascotaAEliminar = mascota
                        }
                    )
                }

            }

            Spacer(Modifier.height(16.dp))

            PetCareButton(
                text = "Registrar mascota",
                onClick = onAddMascota,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    mascotaAEliminar?.let { mascota ->
        AlertDialog(
            onDismissRequest = {
                mascotaAEliminar = null
            },
            title = {
                Text("Eliminar mascota")
            },
            text = {
                Text(
                    "¿Estás seguro de que deseas eliminar " +
                            "a ${mascota.nombre}? Esta acción " +
                            "no se puede deshacer."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteMascota(mascota.id)
                        mascotaAEliminar = null
                    }
                ) {
                    Text(
                        "Eliminar",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mascotaAEliminar = null
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}
