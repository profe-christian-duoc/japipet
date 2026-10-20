
package cl.duoc.japipet.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cl.duoc.japipet.ui.theme.JapiPetTheme

@Preview(showBackground = true)
@Composable
fun PetCareComponentsPreview() {
    JapiPetTheme(darkTheme = false) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Componentes PetCare",
                style = MaterialTheme.typography.headlineSmall
            )

            PetCareButton(
                text = "Guardar mascota",
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )

            PetCareButton(
                text = "Cancelar",
                onClick = {},
                style = PetCareButtonStyle.SECONDARY,
                modifier = Modifier.fillMaxWidth()
            )

            PetCareButton(
                text = "Eliminar",
                onClick = {},
                style = PetCareButtonStyle.DANGER,
                modifier = Modifier.fillMaxWidth()
            )

            PetCareCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Rambo")
                Text("puddle · 3 años")
            }

            PetCareCard(
                modifier = Modifier.fillMaxWidth(),
                style = PetCareCardStyle.HIGHLIGHTED
            ) {
                Text("Cuidado especial")
                Text("Perro bravo!")
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PetCareBadge(
                    text = "Activa",
                    style = PetCareBadgeStyle.SUCCESS
                )

                PetCareBadge(
                    text = "Pendiente",
                    style = PetCareBadgeStyle.WARNING
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                PetCareBadge(
                    text = "Cancelada",
                    style = PetCareBadgeStyle.ERROR
                )

                PetCareBadge(
                    text = "Información",
                    style = PetCareBadgeStyle.INFO
                )
            }
        }
    }
}
