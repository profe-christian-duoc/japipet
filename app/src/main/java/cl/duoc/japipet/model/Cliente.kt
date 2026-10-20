
package cl.duoc.japipet.model

data class Cliente(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val telefono: String
) {
    val nombreCompleto: String
        get() = "$nombre $apellido"
}