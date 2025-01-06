package org.volonter.helpinghand.ui.screens.volunteerProfile.components

import org.volonter.helpinghand.ui.screens.eventdetails.components.RatingElement
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.screens.volunteerProfile.EventViewState

import org.volonter.helpinghand.ui.screens.volunteerProfile.FinishedEventsViewState
import org.volonter.helpinghand.ui.screens.volunteerProfile.PaginatedViewState
import org.volonter.helpinghand.ui.screens.volunteerProfile.VolunteerProfileViewState
import org.volonter.helpinghand.ui.screens.volunteerProfile.VolunteerScreenActions

@Composable
fun VolunteerEventElement(
    eventViewState: EventViewState, modifier: Modifier = Modifier,
    viewState: PaginatedViewState,
    onClick: (VolunteerScreenActions) -> Unit
) {
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    var isExpanded by remember { mutableStateOf(false) }
    var isOverflowing: Boolean? by remember { mutableStateOf(null) }
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            if (viewState is FinishedEventsViewState) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    RatingElement(eventViewState.rating)
                }
                Spacer(Modifier.height(12.dp))
            }
            Spacer(Modifier.height(12.dp))
            Text(
                text = eventViewState.title,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(6.dp))

            Text(
                modifier = Modifier.animateContentSize(),
                text = eventViewState.description,
                color = Color.Black,
                maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 16.sp,
                onTextLayout = { textLayoutResult ->
                    if (isOverflowing == null) {
                        isOverflowing = textLayoutResult.hasVisualOverflow
                    }
                },
                fontSize = 16.sp
            )
            if (isOverflowing == true) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .clickable(interactionSource, null) {
                            isExpanded = !isExpanded
                        },
                    text = if (isExpanded) stringResource(R.string.less) else stringResource(R.string.more),
                    fontSize = 12.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
