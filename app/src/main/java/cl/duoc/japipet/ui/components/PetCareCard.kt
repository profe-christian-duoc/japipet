
package cl.duoc.japipet.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

enum class PetCareCardStyle {
    STANDARD,
    HIGHLIGHTED
}

@Composable
fun PetCareCard(
    modifier: Modifier = Modifier,
    style: PetCareCardStyle = PetCareCardStyle.STANDARD,
    content: @Composable () -> Unit
) {
    val backgroundColor = when (style) {
        PetCareCardStyle.STANDARD ->
            MaterialTheme.colorScheme.surface

        PetCareCardStyle.HIGHLIGHTED ->
            MaterialTheme.colorScheme.primaryContainer
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(
                PaddingValues(16.dp)
            )
        ) {
            content()
        }
    }
}
