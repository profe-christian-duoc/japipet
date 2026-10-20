package cl.duoc.japipet.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class PetCareButtonStyle {
    PRIMARY,
    SECONDARY,
    DANGER
}

@Composable
fun PetCareButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: PetCareButtonStyle = PetCareButtonStyle.PRIMARY,
    enabled: Boolean = true
) {
    val colors = when (style) {
        PetCareButtonStyle.PRIMARY ->
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )

        PetCareButtonStyle.SECONDARY ->
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
            )

        PetCareButtonStyle.DANGER ->
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.onErrorContainer
            )
    }

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = colors
    ) {
        Text(text)
    }
}
