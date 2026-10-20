
package cl.duoc.japipet.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PetCarePrimary,
    onPrimary = PetCareOnPrimary,
    primaryContainer = PetCarePrimaryContainer,
    onPrimaryContainer = PetCareOnPrimaryContainer,

    secondary = PetCareSecondary,
    onSecondary = PetCareOnSecondary,
    secondaryContainer = PetCareSecondaryContainer,
    onSecondaryContainer = PetCareOnSecondaryContainer,

    tertiary = PetCareTertiary,
    onTertiary = PetCareOnTertiary,
    tertiaryContainer = PetCareTertiaryContainer,
    onTertiaryContainer = PetCareOnTertiaryContainer,

    background = PetCareBackground,
    onBackground = PetCareOnBackground,

    surface = PetCareSurface,
    onSurface = PetCareOnSurface,
    surfaceVariant = PetCareSurfaceVariant,
    onSurfaceVariant = PetCareOnSurfaceVariant,

    surfaceContainer = PetCareSurfaceVariant,

    outline = PetCareOutline,
    error = PetCareError,
    onError = PetCareOnError
)

private val DarkColorScheme = darkColorScheme(
    primary = PetCarePrimaryDark,
    onPrimary = PetCareOnPrimaryDark,
    primaryContainer = PetCarePrimaryContainerDark,
    onPrimaryContainer = PetCareOnPrimaryContainerDark,

    secondary = PetCareSecondaryDark,
    onSecondary = PetCareOnSecondaryDark,
    secondaryContainer = PetCareSecondaryContainerDark,
    onSecondaryContainer = PetCareOnSecondaryContainerDark,

    tertiary = PetCareTertiaryDark,
    onTertiary = PetCareOnTertiaryDark,
    tertiaryContainer = PetCareTertiaryContainerDark,
    onTertiaryContainer = PetCareOnTertiaryContainerDark,

    background = PetCareBackgroundDark,
    onBackground = PetCareOnBackgroundDark,

    surface = PetCareSurfaceDark,
    onSurface = PetCareOnSurfaceDark,
    surfaceVariant = PetCareSurfaceVariantDark,
    onSurfaceVariant = PetCareOnSurfaceVariantDark,

    surfaceContainer = PetCareSurfaceVariantDark,

    outline = PetCareOutlineDark,
    error = PetCareErrorDark,
    onError = PetCareOnErrorDark
)

@Composable
fun JapiPetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
