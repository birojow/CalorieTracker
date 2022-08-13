package app.fabianomello.tracker_domain.usecase

data class TrackerUseCases(
    val trackFood: TrackFoodUseCase,
    val searchFood: SearchFoodUseCase,
    val getFoodsForDate: GetFoodsForDateUseCase,
    val deleteTrackedFood: DeleteTrackedFoodUseCase,
    val calculateMealNutrients: CalculateMealNutrientsUseCase
)
