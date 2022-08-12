package app.fabianomello.core_ui.util

sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    data class ShowSnackbar(val message: UiText): UiEvent()
}
