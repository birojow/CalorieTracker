package app.fabianomello.onboarding_presentation.weightgoal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.fabianomello.core.domain.model.WeightGoal
import app.fabianomello.core.domain.preferences.Preferences
import app.fabianomello.core.navigation.Route
import app.fabianomello.core.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightGoalViewModel @Inject constructor(
   private val preferences: Preferences
) : ViewModel() {

    var selectedWeightGoal by mutableStateOf<WeightGoal>(WeightGoal.Keep)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onWeightGoalClick(weightGoal: WeightGoal) {
        this.selectedWeightGoal = weightGoal
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveWeightGoal(selectedWeightGoal)
            _uiEvent.send(UiEvent.Navigate(Route.ONBOARDING_NUTRIENT_GOAL))
        }
    }
}
