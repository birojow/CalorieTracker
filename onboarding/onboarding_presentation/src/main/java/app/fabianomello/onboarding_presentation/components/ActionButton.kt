package app.fabianomello.onboarding_presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import app.fabianomello.core_ui.LocalDimensions

@Composable
fun ActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    val dimensions = LocalDimensions.current
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(dimensions.spaceExtraLarge)
    ) {
        Text(
            text = text,
            style = textStyle,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.padding(dimensions.spaceSmall)
        )
    }
}