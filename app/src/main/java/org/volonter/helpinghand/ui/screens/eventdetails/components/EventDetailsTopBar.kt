package org.volonter.helpinghand.ui.screens.eventdetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.components.PrimaryButton
import org.volonter.helpinghand.ui.screens.eventdetails.EventDetailsViewState
import org.volonter.helpinghand.ui.screens.eventdetails.UnfinishedEventDetailsViewState
import org.volonter.helpinghand.ui.theme.PrimaryCoral

@Composable
fun EventDetailsTopBar(
    viewState: EventDetailsViewState,
    modifier: Modifier = Modifier,
    showApplyButton: Boolean,
    onBackClick: () -> Unit,
    onToggleApplicationToEventClick: () -> Unit
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 22.dp, top = 22.dp, end = 22.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.clickable(interactionSource, null) {
                onBackClick()
            },
            painter = painterResource(R.drawable.ic_arrow_left),
            contentDescription = null
        )
        if (showApplyButton) {
            PrimaryButton(
                backgroundColor = PrimaryCoral,
                onClick = onToggleApplicationToEventClick
            ) {
                Text(
                    text = if ((viewState as UnfinishedEventDetailsViewState).isUserApplied) {
                        stringResource(R.string.undo_apply)
                    } else {
                        stringResource(R.string.apply)
                    }.uppercase(),
                    color = Color.White
                )
            }
        }
    }
}
