package cl.duoc.japipet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdminHomeScreen(
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
                        text = "PetCare | Administración",
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
                text = "Panel administrativo",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Gestión de clientes")
            Text("Gestión de mascotas")
            Text("Gestión de servicios")
            Text("Gestión de profesionales")
            Text("Gestión de reservas")
        }
    }
}