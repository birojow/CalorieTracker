package app.fabianomello.tracker_domain.di

import app.fabianomello.core.domain.preferences.Preferences
import app.fabianomello.tracker_domain.repository.TrackerRepository
import app.fabianomello.tracker_domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFoodUseCase(repository),
            searchFood = SearchFoodUseCase(repository),
            getFoodsForDate = GetFoodsForDateUseCase(repository),
            deleteTrackedFood = DeleteTrackedFoodUseCase(repository),
            calculateMealNutrients = CalculateMealNutrientsUseCase(preferences)
        )
    }
}
