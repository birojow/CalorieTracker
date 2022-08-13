package app.fabianomello.tracker_domain.usecase

import app.fabianomello.tracker_domain.model.TrackedFood
import app.fabianomello.tracker_domain.repository.TrackerRepository

class DeleteTrackedFoodUseCase(
    private val repository: TrackerRepository
) {

    suspend operator fun invoke(trackedFood: TrackedFood) {
        repository.deleteTrackedFood(trackedFood)
    }
}
