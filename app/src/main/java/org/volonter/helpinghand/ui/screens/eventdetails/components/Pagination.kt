package org.volonter.helpinghand.ui.screens.eventdetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.screens.eventdetails.EventScreenActions
import org.volonter.helpinghand.ui.screens.eventdetails.FinishedEventDetailsViewState
import org.volonter.helpinghand.ui.screens.eventdetails.OnPaginationLeftClick
import org.volonter.helpinghand.ui.screens.eventdetails.OnPaginationRightClick

@Composable
fun Pagination(
    viewState: FinishedEventDetailsViewState,
    modifier: Modifier = Modifier,
    onScreenAction: (EventScreenActions) -> Unit
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (viewState.currentPage > 1) {
            Icon(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(24.dp)
                    .clickable(interactionSource, null) {
                        onScreenAction(
                            OnPaginationLeftClick
                        )
                    },
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = null
            )
        } else {
            Spacer(Modifier.size(14.dp))
        }
        Text(
            modifier = Modifier.padding(horizontal = 12.dp),
            text = getPaginationText(viewState),
            color = Color.White
        )
        if (viewState.currentPage < (viewState.allReviews.size / viewState.reviewsPerPage) + 1) {
            Icon(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(24.dp)
                    .clickable(interactionSource, null) {
                        onScreenAction(
                            OnPaginationRightClick
                        )
                    }
                    .rotate(180f),
                painter = painterResource(R.drawable.ic_arrow_left),
                contentDescription = null
            )
        } else {
            Spacer(Modifier.size(14.dp))
        }
    }
}

private fun getPaginationText(viewState: FinishedEventDetailsViewState): String {
    return if (viewState.allReviews.size % viewState.reviewsPerPage == 0) {
        "${viewState.currentPage}" + "/${(viewState.allReviews.size / viewState.reviewsPerPage)}"
    } else {
        "${viewState.currentPage}" + "/${(viewState.allReviews.size / viewState.reviewsPerPage) + 1}"
    }
}
