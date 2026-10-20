package cl.duoc.japipet.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cl.duoc.japipet.model.UserRole

@Composable
fun WelcomeScreen(
    onLogin: (UserRole) -> Unit
) {
    var selectedRole by remember {
        mutableStateOf(UserRole.CLIENT)
    }

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "🐾",
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "PetCare",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Cuidamos a quienes más quieres",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Selecciona tu perfil",
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.fillMaxWidth()
            ) {

                SegmentedButton(
                    selected = selectedRole == UserRole.CLIENT,
                    onClick = {
                        selectedRole = UserRole.CLIENT
                    },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = 0,
                        count = 2
                    )
                ) {
                    Text("Cliente")
                }

                SegmentedButton(
                    selected = selectedRole == UserRole.ADMIN,
                    onClick = {
                        selectedRole = UserRole.ADMIN
                    },
                    shape = SegmentedButtonDefaults.itemShape(
                        index = 1,
                        count = 2
                    )
                ) {
                    Text("Administrador")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    onLogin(selectedRole)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ingresar")
            }
        }
    }
}