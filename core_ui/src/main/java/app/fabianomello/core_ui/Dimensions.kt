package app.fabianomello.core_ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimensions(
    val default: Dp = 0.dp,
    val spaceExtraSmall: Dp = 4.dp,
    val spaceSmall: Dp = 8.dp,
    val spaceMedium: Dp = 16.dp,
    val spaceLarge: Dp = 32.dp,
    val spaceExtraLarge: Dp = 64.dp,
    val buttonBorderWidth: Dp = 2.dp,
    val unitTextFieldTextSize: TextUnit = 70.sp,
    val textFieldPadding: Dp = 2.dp,
    val textFieldCornerRadius: Dp = 5.dp,
    val trackableFoodItemCornerRadius: Dp = 5.dp,
    val elevation: Dp = 1.dp,
    val nutrientInfoAmountTextSize: TextUnit = 16.sp,
    val nutrientInfoUnitTextSize: TextUnit = 12.sp,
    val textFieldBorderWidth: Dp = 0.5.dp
)

val LocalDimensions = compositionLocalOf { Dimensions() }
