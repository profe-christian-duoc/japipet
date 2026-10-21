package cl.duoc.japipet.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import cl.duoc.japipet.model.UserRole
import cl.duoc.japipet.ui.screens.*
import androidx.compose.runtime.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.navArgument
import cl.duoc.japipet.viewmodel.MascotaViewModel
import cl.duoc.japipet.ui.screens.MascotaFormScreen
import cl.duoc.japipet.viewmodel.ClienteViewModel
import cl.duoc.japipet.data.repository.ResultadoEliminarCliente


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val mascotaViewModel: MascotaViewModel = hiltViewModel()
    val clienteViewModel: ClienteViewModel = hiltViewModel()

    val clientesGestion by clienteViewModel.clientes
        .collectAsStateWithLifecycle()
    var mensajeClientes by remember {
        mutableStateOf<String?>(null)
    }

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

                ClientesScreen(
                    clientes = clientesGestion,

                    onBack = {
                        navController.popBackStack()
                    },

                    onAddCliente = {
                        navController.navigate(AppRoutes.CLIENT_FORM)
                    },

                    onEditCliente = { id ->
                        navController.navigate(AppRoutes.clientEdit(id))
                    },

                    onDeleteCliente = { id ->
                        clienteViewModel.eliminarCliente(id) { resultado ->

                            mensajeClientes = when (resultado) {
                                ResultadoEliminarCliente.ELIMINADO ->
                                    "Cliente eliminado correctamente."

                                ResultadoEliminarCliente.TIENE_MASCOTAS ->
                                    "No puedes eliminar este cliente " +
                                            "porque tiene mascotas registradas."

                                ResultadoEliminarCliente.NO_ENCONTRADO ->
                                    "El cliente ya no existe."
                            }
                        }
                    }
                )
            }

            composable(AppRoutes.CLIENT_FORM) {

                ClienteFormScreen(
                    onSave = { nombre, apellido, correo, telefono ->

                        clienteViewModel.agregarCliente(
                            nombre = nombre,
                            apellido = apellido,
                            correo = correo,
                            telefono = telefono,

                            onResult = { guardado ->
                                if (guardado) {
                                    navController.popBackStack()
                                } else {
                                    mensajeClientes = "No se pudo registrar el cliente."
                                }
                            }
                        )
                    },

                    onCancel = {
                        navController.popBackStack()
                    }
                )
            }

            composable(
                route = AppRoutes.CLIENT_EDIT,
                arguments = listOf(
                    navArgument("clienteId") {
                        type = NavType.IntType
                    }
                )
            ) { backStackEntry ->

                val clienteId = backStackEntry.arguments
                    ?.getInt("clienteId")

                val clienteEditar = clientesGestion.find {
                    it.id == clienteId
                }

                if (clienteEditar != null) {

                    ClienteFormScreen(
                        clienteEditar = clienteEditar,

                        onSave = { nombre, apellido, correo, telefono ->

                            clienteViewModel.actualizarCliente(
                                cliente = clienteEditar.copy(
                                    nombre = nombre,
                                    apellido = apellido,
                                    correo = correo,
                                    telefono = telefono
                                ),

                                onResult = { actualizado ->
                                    if (actualizado) {
                                        navController.popBackStack()
                                    } else {
                                        mensajeClientes =
                                            "No se pudieron guardar los cambios."
                                    }
                                }
                            )
                        },

                        onCancel = {
                            navController.popBackStack()
                        }
                    )

                } else {
                    Text("Cargando cliente...")
                }
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


        mensajeClientes?.let { mensaje ->

            AlertDialog(
                onDismissRequest = {
                    mensajeClientes = null
                },

                title = {
                    Text("Gestión de clientes")
                },

                text = {
                    Text(mensaje)
                },

                confirmButton = {
                    TextButton (
                        onClick = {
                            mensajeClientes = null
                        }
                    ) {
                        Text("Aceptar")
                    }
                }
            )
        }

    }
}