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
import app.fabianomello.calorietracker.ui.theme.CalorieTrackerTheme
import app.fabianomello.core.domain.preferences.Preferences
import app.fabianomello.calorietracker.navigation.Route
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
import javax.inject.Inject

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shouldShowOnboarding = preferences.loadShouldShowOnboarding()
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
                        startDestination = if (shouldShowOnboarding) {
                            Route.ONBOARDING_WELCOME
                        } else {
                            Route.TRACKER_OVERVIEW
                        }
                    ) {
                        composable(Route.ONBOARDING_WELCOME) {
                            WelcomeScreen(onNextClick = {
                                navController.navigate(Route.ONBOARDING_GENDER)
                            })
                        }
                        composable(Route.ONBOARDING_GENDER) {
                            GenderScreen(onNextClick = {
                                navController.navigate(Route.ONBOARDING_AGE)
                            })
                        }
                        composable(Route.ONBOARDING_AGE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.ONBOARDING_HEIGHT)
                                }
                            )
                        }
                        composable(Route.ONBOARDING_HEIGHT) {
                           HeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.ONBOARDING_WEIGHT)
                                }
                            )
                        }
                        composable(Route.ONBOARDING_WEIGHT) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.ONBOARDING_ACTIVITY_LEVEL)
                                }
                            )
                        }
                        composable(Route.ONBOARDING_ACTIVITY_LEVEL) {
                            ActivityLevelScreen(
                                onNextClick = {
                                    navController.navigate(Route.ONBOARDING_WEIGHT_GOAL)
                                }
                            )
                        }
                        composable(Route.ONBOARDING_WEIGHT_GOAL) {
                            WeightGoalScreen(
                                onNextClick = {
                                    navController.navigate(Route.ONBOARDING_NUTRIENT_GOAL)
                                }
                            )
                        }
                        composable(Route.ONBOARDING_NUTRIENT_GOAL) {
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.TRACKER_OVERVIEW)
                                }
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                            OverviewScreen(
                                onNavigateToSearch = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.TRACKER_SEARCH +
                                                "/$mealName" +
                                                "/$day" +
                                                "/$month" +
                                                "/$year"
                                    )
                                }
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW) {
                            OverviewScreen(
                                onNavigateToSearch = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.TRACKER_SEARCH +
                                                "/$mealName" +
                                                "/$day" +
                                                "/$month" +
                                                "/$year"
                                    )
                                }
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
