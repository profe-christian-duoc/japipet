package cl.duoc.japipet.data

import cl.duoc.japipet.data.local.entity.ClienteEntity
import cl.duoc.japipet.data.local.entity.MascotaEntity
import cl.duoc.japipet.model.Cliente
import cl.duoc.japipet.model.Mascota

fun ClienteEntity.toModel() = Cliente(
    id = id,
    nombre = nombre,
    apellido = apellido,
    correo = correo,
    telefono = telefono
)

fun Cliente.toEntity() = ClienteEntity(
    id = id,
    nombre = nombre,
    apellido = apellido,
    correo = correo,
    telefono = telefono
)

fun MascotaEntity.toModel() = Mascota(
    id = id,
    clienteId = clienteId,
    nombre = nombre,
    especie = especie,
    raza = raza,
    edad = edad,
    observaciones = observaciones
)

fun Mascota.toEntity() = MascotaEntity(
    id = id,
    clienteId = clienteId,
    nombre = nombre,
    especie = especie,
    raza = raza,
    edad = edad,
    observaciones = observaciones
)
