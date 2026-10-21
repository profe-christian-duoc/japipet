package cl.duoc.japipet.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import cl.duoc.japipet.model.UserRole
import cl.duoc.japipet.ui.screens.AdminHomeScreen
import cl.duoc.japipet.ui.screens.ClientHomeScreen
import cl.duoc.japipet.ui.screens.MascotasScreen
import cl.duoc.japipet.ui.screens.ModuleScreen
import cl.duoc.japipet.ui.screens.WelcomeScreen
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.duoc.japipet.viewmodel.MascotaViewModel
import cl.duoc.japipet.ui.screens.MascotaFormScreen

@Composable
fun AppNavigation() {

    val mascotaViewModel: MascotaViewModel = hiltViewModel()
    val navController = rememberNavController()
    val mascotas by mascotaViewModel.mascotas.collectAsStateWithLifecycle()
    val clientes by mascotaViewModel.clientes.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
    ) {

        NavHost(
            navController = navController,
            startDestination = AppRoutes.WELCOME
        ) {
            composable(AppRoutes.WELCOME) {
                WelcomeScreen(
                    onLogin = { role ->
                        val destination = when (role) {
                            UserRole.ADMIN -> AppRoutes.ADMIN_HOME
                            UserRole.CLIENT -> AppRoutes.CLIENT_HOME
                        }
                        navController.navigate(destination) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(AppRoutes.ADMIN_HOME) {
                AdminHomeScreen(
                    onLogout = {
                        navController.popBackStack()
                    },
                    onNavigate = { route ->
                        navController.navigate(route)
                    }
                )
            }

            composable(AppRoutes.CLIENT_HOME) {
                ClientHomeScreen(
                    onLogout = {
                        navController.popBackStack()
                    },
                    onNavigate = { route ->
                        navController.navigate(route)
                    }
                )
            }
            composable(AppRoutes.CLIENTS) {
                ModuleScreen(
                    title = "Clientes",
                    onBack = { navController.popBackStack() }
                )
            }

            composable(AppRoutes.PETS) {
                MascotasScreen(
                    mascotas = mascotas,
                    clientes = clientes,
                    onBack = {
                        navController.popBackStack()
                    },
                    onAddMascota = {
                        navController.navigate(AppRoutes.PET_FORM)
                    },
                    onEditMascota = { mascotaId ->
                        navController.navigate(
                            AppRoutes.petEdit(mascotaId)
                        )
                    },
                    onDeleteMascota = { mascotaId ->
                        mascotaViewModel.eliminarMascota(mascotaId)
                    }
                )
            }

            composable(AppRoutes.PET_FORM) {
                MascotaFormScreen(
                    clientes = clientes,
                    onSave = { clienteId,
                               nombre,
                               especie,
                               raza,
                               edad,
                               observaciones ->


                        mascotaViewModel.agregarMascota(
                            clienteId = clienteId,
                            nombre = nombre,
                            especie = especie,
                            raza = raza,
                            edad = edad,
                            observaciones = observaciones,
                            onResult = { guardado ->
                                if (guardado) {
                                    navController.popBackStack()
                                }
                            }
                        )

                    },
                    onCancel = {
                        navController.popBackStack()
                    }
                )
            }


            composable(AppRoutes.PET_EDIT) { backStackEntry ->

                val mascotaId = backStackEntry.arguments
                    ?.getString("mascotaId")
                    ?.toIntOrNull()

                val mascota = mascotas.find { it.id == mascotaId }

                if (mascota != null) {
                    MascotaFormScreen(
                        clientes = clientes,
                        mascotaEditar = mascota,
                        onSave = { clienteId,
                                   nombre,
                                   especie,
                                   raza,
                                   edad,
                                   observaciones ->

                            mascotaViewModel.actualizarMascota(
                                mascota = mascota.copy(
                                    clienteId = clienteId,
                                    nombre = nombre,
                                    especie = especie,
                                    raza = raza,
                                    edad = edad,
                                    observaciones = observaciones
                                ),
                                onResult = { actualizado ->
                                    if (actualizado) {
                                        navController.popBackStack()
                                    }
                                }
                            )
                        },
                        onCancel = {
                            navController.popBackStack()
                        }
                    )
                } else {
                    ModuleScreen(
                        title = "Mascota no encontrada",
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }


            composable(AppRoutes.SERVICES) {
                ModuleScreen(
                    title = "Servicios",
                    onBack = { navController.popBackStack() }
                )
            }

            composable(AppRoutes.PROFESSIONALS) {
                ModuleScreen(
                    title = "Profesionales",
                    onBack = { navController.popBackStack() }
                )
            }

            composable(AppRoutes.BOOKINGS) {
                ModuleScreen(
                    title = "Reservas",
                    onBack = { navController.popBackStack() }
                )
            }
            composable(AppRoutes.BOOK_NOW) {
                ModuleScreen(
                    title = "Reservar hora",
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(AppRoutes.MY_BOOKINGS) {
                ModuleScreen(
                    title = "Mis reservas",
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}