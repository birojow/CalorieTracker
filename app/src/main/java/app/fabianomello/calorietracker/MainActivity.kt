package app.fabianomello.calorietracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.fabianomello.calorietracker.navigation.navigate
import app.fabianomello.calorietracker.ui.theme.CalorieTrackerTheme
import app.fabianomello.core.navigation.Route
import app.fabianomello.onboarding_presentation.activitylevel.ActivityLevelScreen
import app.fabianomello.onboarding_presentation.age.AgeScreen
import app.fabianomello.onboarding_presentation.gender.GenderScreen
import app.fabianomello.onboarding_presentation.height.HeightScreen
import app.fabianomello.onboarding_presentation.nutrientgoal.NutrientGoalScreen
import app.fabianomello.onboarding_presentation.weight.WeightScreen
import app.fabianomello.onboarding_presentation.weightgoal.WeightGoalScreen
import app.fabianomello.onboarding_presentation.welcome.WelcomeScreen
import app.fabianomello.tracker_presentation.overview.OverviewScreen
import app.fabianomello.tracker_presentation.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
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
                        composable(Route.ONBOARDING_AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ONBOARDING_HEIGHT) {
                           HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ONBOARDING_WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ONBOARDING_ACTIVITY_LEVEL) {
                            ActivityLevelScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ONBOARDING_WEIGHT_GOAL) {
                            WeightGoalScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ONBOARDING_NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                            OverviewScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(
                            route = Route.TRACKER_SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") {
                                    type = NavType.StringType
                                },
                                navArgument("dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument("month") {
                                    type = NavType.IntType
                                },
                                navArgument("year") {
                                    type = NavType.IntType
                                },
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!
                            SearchScreen(
                                scaffoldState = scaffoldState,
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
