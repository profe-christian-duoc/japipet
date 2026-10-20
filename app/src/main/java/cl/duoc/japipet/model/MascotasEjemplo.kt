package cl.duoc.japipet.model

object MascotasEjemplo {

    val mascotas = listOf(
        Mascota(
            id = 1,
            clienteId = 1,
            nombre = "Luna",
            especie = "Perro",
            raza = "Golden Retriever",
            edad = 3,
            observaciones = "Muy sociable"
        ),
        Mascota(
            id = 2,
            clienteId = 1,
            nombre = "Milo",
            especie = "Gato",
            raza = "Mestizo",
            edad = 2,
            observaciones = "Alimentación especial"
        ),
        Mascota(
            id = 3,
            clienteId = 2,
            nombre = "Rocky",
            especie = "Perro",
            raza = "Beagle",
            edad = 5,
            observaciones = "Le gusta jugar"
        )
    )
}
