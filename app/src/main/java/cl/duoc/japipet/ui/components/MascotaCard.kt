package cl.duoc.japipet.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cl.duoc.japipet.model.Mascota

@Composable
fun MascotaCard(
    mascota: Mascota,
    propietario: String,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    PetCareCard(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = mascota.nombre,
                    style = MaterialTheme.typography.titleLarge
                )

                PetCareBadge(
                    text = mascota.especie,
                    style = PetCareBadgeStyle.SUCCESS
                )
            }

            Text(
                text = "${mascota.raza} · ${mascota.edad} años",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Propietario: $propietario",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
            if (mascota.observaciones.isNotBlank()) {
                Text(
                    text = mascota.observaciones,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PetCareButton(
                    text = "Editar",
                    onClick = onEdit,
                    style = PetCareButtonStyle.SECONDARY,
                    modifier = Modifier.weight(1f)
                )

                PetCareButton(
                    text = "Eliminar",
                    onClick = onDelete,
                    style = PetCareButtonStyle.DANGER,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
