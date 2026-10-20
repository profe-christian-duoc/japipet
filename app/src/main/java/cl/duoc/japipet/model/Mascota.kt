package cl.duoc.japipet.model

data class Mascota(
    val id: Int,
    val clienteId: Int,
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: Int,
    val observaciones: String = ""
)
