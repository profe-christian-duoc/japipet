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

private data class AdminModule(
    val title: String,
    val description: String,
    @DrawableRes val iconRes: Int,
    val route: String
)

@Composable
fun AdminHomeScreen(
    onLogout: () -> Unit,
    onNavigate: (String) -> Unit
) {
    val modules = listOf(
        AdminModule(
            "Clientes",
            "Gestionar clientes",
            R.drawable.ic_clients,
            AppRoutes.CLIENTS
        ),
        AdminModule(
            "Mascotas",
            "Registro de mascotas",
            R.drawable.ic_pets,
            AppRoutes.PETS
        ),
        AdminModule(
            "Servicios",
            "Catálogo y precios",
            R.drawable.ic_services,
            AppRoutes.SERVICES
        ),
        AdminModule(
            "Profesionales",
            "Personal del centro",
            R.drawable.ic_professionals,
            AppRoutes.PROFESSIONALS
        ),
        AdminModule(
            "Reservas",
            "Agenda de atenciones",
            R.drawable.ic_bookings,
            AppRoutes.BOOKINGS
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
                        "PetCare | Administración",
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
                text = "Panel administrativo",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Gestiona tu centro de cuidado de mascotas",
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