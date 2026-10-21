
package cl.duoc.japipet.data.local.entity

import androidx.room3.*

@Entity(
    tableName = "mascotas",
    foreignKeys = [
        ForeignKey(
            entity = ClienteEntity::class,
            parentColumns = ["id"],
            childColumns = ["clienteId"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index(value = ["clienteId"])
    ]
)
data class MascotaEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val clienteId: Int,
    val nombre: String,
    val especie: String,
    val raza: String,
    val edad: Int,
    val observaciones: String = ""
)
