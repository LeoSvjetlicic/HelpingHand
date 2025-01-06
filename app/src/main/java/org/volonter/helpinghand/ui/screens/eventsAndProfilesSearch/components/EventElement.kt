package org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.components

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.EventViewState
import org.volonter.helpinghand.ui.screens.eventsAndProfilesSearch.EventsAndProfilesSearchActions


@Composable
fun EventElement(
    eventViewState: EventViewState, modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    var isExpanded by remember { mutableStateOf(false) }
    var isOverflowing: Boolean? by remember { mutableStateOf(null) }

    val datePart = eventViewState.time.substringBeforeLast(",").trim()
    val timePart = eventViewState.time.substringAfterLast(",").trim()
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick(eventViewState.id) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth()) {
            Spacer(Modifier.height(12.dp))
            Text(
                text = eventViewState.title,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = datePart,
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Text(
                    text = timePart,
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }

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