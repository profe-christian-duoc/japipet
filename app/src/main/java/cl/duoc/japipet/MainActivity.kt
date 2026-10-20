package cl.duoc.japipet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cl.duoc.japipet.navigation.AppNavigation
import cl.duoc.japipet.ui.theme.JapiPetTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JapiPetTheme() {
                AppNavigation()
            }
        }
    }
}