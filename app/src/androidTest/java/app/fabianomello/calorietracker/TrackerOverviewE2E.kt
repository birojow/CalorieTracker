package app.fabianomello.calorietracker

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import app.fabianomello.calorietracker.navigation.Route
import app.fabianomello.calorietracker.repository.TrackerRepositoryFake
import app.fabianomello.calorietracker.ui.theme.CalorieTrackerTheme
import app.fabianomello.core.domain.model.ActivityLevel
import app.fabianomello.core.domain.model.Gender
import app.fabianomello.core.domain.model.UserInfo
import app.fabianomello.core.domain.model.WeightGoal
import app.fabianomello.core.domain.preferences.Preferences
import app.fabianomello.core.domain.usecase.FilterOutDigitsUseCase
import app.fabianomello.tracker_domain.model.TrackableFood
import app.fabianomello.tracker_domain.usecase.*
import app.fabianomello.tracker_presentation.overview.OverviewScreen
import app.fabianomello.tracker_presentation.overview.OverviewViewModel
import app.fabianomello.tracker_presentation.search.SearchScreen
import app.fabianomello.tracker_presentation.search.SearchViewModel
import coil.annotation.ExperimentalCoilApi
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.InternalPlatformDsl.toStr
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.roundToInt

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@HiltAndroidTest
class TrackerOverviewE2E {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    private lateinit var repositoryFake: TrackerRepositoryFake
    private lateinit var trackerUseCases: TrackerUseCases
    private lateinit var preferences: Preferences
    private lateinit var overviewViewModel: OverviewViewModel
    private lateinit var searchViewModel: SearchViewModel

    private lateinit var navController: NavHostController

    @Before
    fun setUp() {
        preferences = mockk(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            weightGoal = WeightGoal.Keep,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        repositoryFake = TrackerRepositoryFake()
        trackerUseCases = TrackerUseCases(
            trackFood = TrackFoodUseCase(repositoryFake),
            searchFood = SearchFoodUseCase(repositoryFake),
            getFoodsForDate = GetFoodsForDateUseCase(repositoryFake),
            deleteTrackedFood = DeleteTrackedFoodUseCase(repositoryFake),
            calculateMealNutrients = CalculateMealNutrientsUseCase(preferences)
        )
        overviewViewModel = OverviewViewModel(
            preferences = preferences,
            trackerUseCases = trackerUseCases
        )
        searchViewModel = SearchViewModel(
            trackerUseCases = trackerUseCases,
            filterOutDigitsUseCase = FilterOutDigitsUseCase()
        )
        composeRule.setContent {
            CalorieTrackerTheme {
                val scaffoldState = rememberScaffoldState()
                navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.TRACKER_OVERVIEW
                    ) {
                        composable(Route.TRACKER_OVERVIEW) {
                            OverviewScreen(
                                onNavigateToSearch = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.TRACKER_SEARCH + "/$mealName" +
                                                "/$day" +
                                                "/$month" +
                                                "/$year"
                                    )
                                },
                                viewModel = overviewViewModel
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
                                },
                                viewModel = searchViewModel
                            )
                        }
                    }
                }
            }
        }
    }

    @Test
    fun addBreakfast_appearsUnderBreakfast_nutrientsProperlyCalculated() {
        repositoryFake.searchResults = listOf(
            TrackableFood(
                name = "banana",
                imageUrl = null,
                caloriesPer100g = 150,
                proteinPer100g = 5,
                carbsPer100g = 50,
                fatPer100g = 1
            )
        )
        val addedAmount = 150
        val expectedCalories = (1.5f * 150).roundToInt()
        val expectedCarbs = (1.5f * 50).roundToInt()
        val expectedProtein = (1.5f * 5).roundToInt()
        val expectedFat = (1.5f * 1).roundToInt()

        composeRule
            .onNodeWithText("Adicionar Café da manhã")
            .assertDoesNotExist()
        composeRule
            .onNodeWithContentDescription("Café da manhã")
            .performClick()
        composeRule
            .onNodeWithText("Adicionar Café da manhã")
            .assertIsDisplayed()
        composeRule
            .onNodeWithText("Adicionar Café da manhã")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.TRACKER_SEARCH)
        ).isTrue()

        composeRule
            .onNodeWithTag("search_textfield")
            .performTextInput("banana")
        composeRule
            .onNodeWithContentDescription("Buscar...")
            .performClick()

        composeRule.onRoot().printToLog("COMPOSE TREE")

        composeRule
            .onNodeWithText("Carb")
            .performClick()
        composeRule
            .onNodeWithContentDescription("Quantidade")
            .performTextInput(addedAmount.toStr())
        composeRule
            .onNodeWithContentDescription("Adicionar")
            .performClick()

        assertThat(
            navController
                .currentDestination
                ?.route
                ?.startsWith(Route.TRACKER_OVERVIEW)
        )

        composeRule
            .onAllNodesWithText(expectedCarbs.toStr())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedProtein.toStr())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedFat.toStr())
            .onFirst()
            .assertIsDisplayed()
        composeRule
            .onAllNodesWithText(expectedCalories.toStr())
            .onFirst()
            .assertIsDisplayed()
    }
}
