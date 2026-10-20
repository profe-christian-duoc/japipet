package cl.duoc.japipet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ClientHomeScreen(
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            Surface(
                tonalElevation = 3.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "PetCare | Cliente",
                        style = MaterialTheme.typography.titleMedium
                    )

                    TextButton(onClick = onLogout) {
                        Text("Salir")
                    }
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            Text(
                text = "Bienvenido a PetCare",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Mis mascotas")
            Text("Servicios disponibles")
            Text("Reservar una hora")
            Text("Mis reservas")
        }
    }
}