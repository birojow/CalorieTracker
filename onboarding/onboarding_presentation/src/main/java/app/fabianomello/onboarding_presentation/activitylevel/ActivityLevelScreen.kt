package app.fabianomello.onboarding_presentation.activitylevel

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import app.fabianomello.core.domain.model.ActivityLevel
import app.fabianomello.core_ui.LocalSpacing
import app.fabianomello.core_ui.util.UiEvent
import app.fabianomello.onboarding_presentation.R
import app.fabianomello.onboarding_presentation.components.ActionButton
import app.fabianomello.onboarding_presentation.components.SelectableButton
import kotlinx.coroutines.flow.collect

@Composable
fun ActivityLevelScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: ActivityLevelViewModel = hiltViewModel()
) {
    val dimensions = LocalSpacing.current
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Navigate -> onNavigate(event)
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensions.spaceLarge)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(dimensions.spaceMedium))
            Row {
                SelectableButton(
                   text = stringResource(id = R.string.low),
                   isSelected = viewModel.selectedActivityLevel is ActivityLevel.Low,
                   color = MaterialTheme.colors.primaryVariant,
                   selectedTextColor = Color.White,
                   onClick = { viewModel.onActivityLevelClick(ActivityLevel.Low) },
                   textStyle = MaterialTheme.typography.button.copy(
                       fontWeight = FontWeight.Normal
                   )
                )
                Spacer(modifier = Modifier.width(dimensions.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.medium),
                    isSelected = viewModel.selectedActivityLevel is ActivityLevel.Medium,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = { viewModel.onActivityLevelClick(ActivityLevel.Medium) },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
                Spacer(modifier = Modifier.width(dimensions.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.high),
                    isSelected = viewModel.selectedActivityLevel is ActivityLevel.High,
                    color = MaterialTheme.colors.primaryVariant,
                    selectedTextColor = Color.White,
                    onClick = { viewModel.onActivityLevelClick(ActivityLevel.High) },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = viewModel::onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
