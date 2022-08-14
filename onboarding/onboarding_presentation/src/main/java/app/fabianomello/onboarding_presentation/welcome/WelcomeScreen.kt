package app.fabianomello.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import app.fabianomello.core.navigation.Route
import app.fabianomello.core_ui.LocalDimensions
import app.fabianomello.core.util.UiEvent
import app.fabianomello.onboarding_presentation.R
import app.fabianomello.onboarding_presentation.components.ActionButton

@Composable
fun WelcomeScreen(
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val dimensions = LocalDimensions.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensions.spaceMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.welcome_text),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(dimensions.spaceMedium))
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = { onNavigate(UiEvent.Navigate(Route.ONBOARDING_GENDER)) },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}