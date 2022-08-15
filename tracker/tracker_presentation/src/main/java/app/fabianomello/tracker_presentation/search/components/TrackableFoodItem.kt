package app.fabianomello.tracker_presentation.search.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import app.fabianomello.core.util.UiText
import app.fabianomello.core_ui.LocalDimensions
import app.fabianomello.tracker_presentation.search.TrackableFoodUiState
import app.fabianomello.tracker_presentation.R
import app.fabianomello.tracker_presentation.components.NutrientInfo

@ExperimentalCoilApi
@Composable
fun TrackableFoodItem(
    trackableFoodUiState: TrackableFoodUiState,
    onClick: () -> Unit,
    onAmountChange: (String) -> Unit,
    onTrack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val food = trackableFoodUiState.food
    val dimensions = LocalDimensions.current
    val context = LocalContext.current
    val amountUiText = UiText.StringResource(R.string.amount)
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(dimensions.trackableFoodItemCornerRadius))
            .padding(dimensions.spaceExtraSmall)
            .shadow(
                elevation = dimensions.elevation,
                shape = RoundedCornerShape(dimensions.trackableFoodItemCornerRadius)
            )
            .background(MaterialTheme.colors.surface)
            .clickable { onClick() }
            .padding(end = dimensions.spaceMedium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = food.imageUrl,
                        builder = {
                            crossfade(true)
                            error(R.drawable.ic_burger)
                            fallback(R.drawable.ic_burger)
                        }
                    ),
                    contentDescription = food.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(topStart = dimensions.trackableFoodItemCornerRadius))
                )
                Spacer(modifier = Modifier.width(dimensions.spaceMedium))
                Column(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text(
                        text = food.name,
                        style = MaterialTheme.typography.body1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(dimensions.spaceSmall))
                    Text(
                        text = stringResource(
                            id = R.string.kcal_per_100g,
                            food.caloriesPer100g
                        ),
                        style = MaterialTheme.typography.body2
                    )
                }
            }
            Row {
                NutrientInfo(
                    name = stringResource(id = R.string.carbs),
                    amount = food.carbsPer100g,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = dimensions.nutrientInfoAmountTextSize,
                    unitTextSize = dimensions.nutrientInfoUnitTextSize,
                    nameTextStyle = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.width(dimensions.spaceSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.protein),
                    amount = food.proteinPer100g,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = dimensions.nutrientInfoAmountTextSize,
                    unitTextSize = dimensions.nutrientInfoUnitTextSize,
                    nameTextStyle = MaterialTheme.typography.body2
                )
                Spacer(modifier = Modifier.width(dimensions.spaceSmall))
                NutrientInfo(
                    name = stringResource(id = R.string.fat),
                    amount = food.fatPer100g,
                    unit = stringResource(id = R.string.grams),
                    amountTextSize = dimensions.nutrientInfoAmountTextSize,
                    unitTextSize = dimensions.nutrientInfoUnitTextSize,
                    nameTextStyle = MaterialTheme.typography.body2
                )
            }
        }
        AnimatedVisibility(visible = trackableFoodUiState.isExpanded) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensions.spaceMedium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row {
                    BasicTextField(
                        value = trackableFoodUiState.amount,
                        onValueChange = onAmountChange,
                        keyboardOptions = KeyboardOptions(
                            imeAction = if(trackableFoodUiState.amount.isNotBlank()) {
                                ImeAction.Done
                            } else ImeAction.Default,
                            keyboardType = KeyboardType.Number
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                onTrack()
                                defaultKeyboardAction(ImeAction.Done)
                            }
                        ),
                        singleLine = true,
                        modifier = Modifier
                            .border(
                                shape = RoundedCornerShape(dimensions.textFieldCornerRadius),
                                width = dimensions.textFieldBorderWidth,
                                color = MaterialTheme.colors.onSurface
                            )
                            .alignBy(LastBaseline)
                            .padding(dimensions.spaceMedium)
                            .semantics {
                                contentDescription = amountUiText.asString(context)
                            }
                    )
                    Spacer(modifier = Modifier.width(dimensions.spaceExtraSmall))
                    Text(
                        text = stringResource(id = R.string.grams),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.alignBy(LastBaseline)
                    )
                }
                IconButton(
                    onClick = onTrack,
                    enabled = trackableFoodUiState.amount.isNotBlank()
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = stringResource(id = R.string.track)
                    )
                }
            }
        }
    }
}
