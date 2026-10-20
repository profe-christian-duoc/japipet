
package cl.duoc.japipet.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.duoc.japipet.ui.theme.*

enum class PetCareBadgeStyle {
    SUCCESS,
    WARNING,
    ERROR,
    INFO
}

@Composable
fun PetCareBadge(
    text: String,
    style: PetCareBadgeStyle,
    modifier: Modifier = Modifier
) {
    val backgroundColor: Color
    val textColor: Color

    when (style) {
        PetCareBadgeStyle.SUCCESS -> {
            backgroundColor = PetCareSuccessContainer
            textColor = PetCareOnSuccessContainer
        }

        PetCareBadgeStyle.WARNING -> {
            backgroundColor = PetCareWarningContainer
            textColor = PetCareOnWarningContainer
        }

        PetCareBadgeStyle.ERROR -> {
            backgroundColor = MaterialTheme.colorScheme.errorContainer
            textColor = MaterialTheme.colorScheme.onErrorContainer
        }

        PetCareBadgeStyle.INFO -> {
            backgroundColor = PetCareInfoContainer
            textColor = PetCareOnInfoContainer
        }
    }

    Text(
        text = text,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(50)
            )
            .padding(
                horizontal = 12.dp,
                vertical = 6.dp
            ),
        color = textColor,
        style = MaterialTheme.typography.labelMedium,
        fontWeight = FontWeight.SemiBold
    )
}
