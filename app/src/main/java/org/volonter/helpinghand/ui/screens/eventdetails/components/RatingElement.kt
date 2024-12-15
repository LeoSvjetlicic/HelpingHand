package org.volonter.helpinghand.ui.screens.eventdetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.theme.StarYellow

@Composable
fun RatingElement(
    rating: Int,
    modifier: Modifier = Modifier,
    onRatingChanged: (Int) -> Unit
) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            val interactionSource = remember { MutableInteractionSource() }
            if (i <= rating) {
                Icon(
                    painter = painterResource(R.drawable.ic_filled_star),
                    contentDescription = null,
                    tint = StarYellow,
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { onRatingChanged(i) })
                )
                Spacer(modifier = Modifier.width(4.dp))
            } else {
                Icon(
                    painter = painterResource(R.drawable.ic_empty_star),
                    contentDescription = null,
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = { onRatingChanged(i) })
                )
                if (i != 5) {
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}
