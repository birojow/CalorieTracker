package app.fabianomello.tracker_presentation.overview

import android.content.Context
import app.fabianomello.tracker_domain.model.TrackedFood

sealed class OverviewEvent {
    object OnNextDayClick: OverviewEvent()
    object OnPreviousDayClick: OverviewEvent()
    data class OnToggleMealClick(val meal: Meal): OverviewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood): OverviewEvent()
    data class OnAddFoodClick(
        val meal: Meal,
        val context: Context
    ): OverviewEvent()
}
