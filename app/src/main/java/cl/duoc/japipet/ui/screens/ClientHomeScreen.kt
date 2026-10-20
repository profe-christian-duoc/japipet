package cl.duoc.japipet.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duoc.japipet.R
import cl.duoc.japipet.navigation.AppRoutes
import cl.duoc.japipet.ui.components.DashboardCard

private data class ClientModule(
    val title: String,
    val description: String,
    @DrawableRes val iconRes: Int,
    val route: String
)

@Composable
fun ClientHomeScreen(
    onLogout: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val modules = listOf(
        ClientModule(
            title = "Mis mascotas",
            description = "Registrar y consultar",
            iconRes = R.drawable.ic_pets,
            route = AppRoutes.PETS
        ),
        ClientModule(
            title = "Servicios",
            description = "Catálogo y precios",
            iconRes = R.drawable.ic_services,
            route = AppRoutes.SERVICES
        ),
        ClientModule(
            title = "Reservar hora",
            description = "Agendar una atención",
            iconRes = R.drawable.ic_book_now,
            route = AppRoutes.BOOK_NOW
        ),
        ClientModule(
            title = "Mis reservas",
            description = "Próximas atenciones",
            iconRes = R.drawable.ic_my_bookings,
            route = AppRoutes.MY_BOOKINGS
        )
    )

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
                .padding(20.dp)
        ) {
            Text(
                text = "¡Bienvenido a PetCare!",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Todo lo que necesitas para cuidar a tu mascota.",
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(Modifier.height(24.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(modules) { module ->
                    DashboardCard(
                        title = module.title,
                        description = module.description,
                        iconRes = module.iconRes,
                        onClick = {
                            onNavigate(module.route)
                        }
                    )
                }
            }
        }
    }
}