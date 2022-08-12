package app.fabianomello.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.fabianomello.calorietracker.navigation.navigate
import app.fabianomello.calorietracker.ui.theme.CalorieTrackerTheme
import app.fabianomello.core.navigation.Route
import app.fabianomello.onboarding_presentation.gender.GenderScreen
import app.fabianomello.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, 
                    startDestination = Route.ONBOARDING_WELCOME 
                ) {
                    composable(Route.ONBOARDING_WELCOME) {
                        WelcomeScreen(onNavigate = navController::navigate)
                    }
                    composable(Route.ONBOARDING_GENDER) {
                        GenderScreen(onNavigate = navController::navigate)
                    }
                }
            }
        }
    }
}
