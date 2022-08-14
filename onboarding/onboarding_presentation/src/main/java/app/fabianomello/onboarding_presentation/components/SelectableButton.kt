package app.fabianomello.onboarding_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import app.fabianomello.core_ui.LocalDimensions

@Composable
fun SelectableButton(
    text: String,
    isSelected: Boolean,
    color: Color,
    selectedTextColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.button
) {
    val dimensions = LocalDimensions.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(dimensions.spaceExtraLarge))
            .border(
                width = dimensions.buttonBorderWidth,
                color = color,
                shape = RoundedCornerShape(dimensions.spaceExtraLarge)
            )
            .background(
                color = if (isSelected) color else Color.Transparent,
                shape = RoundedCornerShape(dimensions.spaceExtraLarge)
            )
            .clickable { onClick() }
            .padding(dimensions.spaceMedium)
    ) {
        Text(
            text = text,
            style = textStyle,
            color = if (isSelected) selectedTextColor else color,
        )
    }
}
