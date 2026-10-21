package cl.duoc.japipet.navigation

object AppRoutes {
    const val WELCOME = "welcome"
    const val ADMIN_HOME = "admin_home"
    const val CLIENT_HOME = "client_home"

    const val CLIENTS = "clients"
    const val PETS = "pets"
    const val SERVICES = "services"
    const val PROFESSIONALS = "professionals"
    const val BOOKINGS = "bookings"

    const val BOOK_NOW = "book_now"
    const val MY_BOOKINGS = "my_bookings"

    const val PET_FORM = "pet_form"
    const val PET_EDIT = "pet_edit/{mascotaId}"

    fun petEdit(mascotaId: Int): String {
        return "pet_edit/$mascotaId"
    }

    const val CLIENT_FORM = "client_form"
    const val CLIENT_EDIT = "client_edit/{clienteId}"

    fun clientEdit(id: Int): String = "client_edit/$id"
}
