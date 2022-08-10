package app.fabianomello.calorietracker.navigation

import androidx.navigation.NavController
import app.fabianomello.core_ui.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}
