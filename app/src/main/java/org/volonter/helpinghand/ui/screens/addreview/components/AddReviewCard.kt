package org.volonter.helpinghand.ui.screens.addreview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.ui.common.components.UserShortDetails
import org.volonter.helpinghand.ui.screens.addreview.AddReviewViewState
import org.volonter.helpinghand.ui.screens.eventdetails.components.RatingElement
import org.volonter.helpinghand.ui.theme.MiddleBrown

@Composable
fun AddReviewCard(
    viewState: AddReviewViewState,
    modifier: Modifier = Modifier,
    onRatingChanged: (Int) -> Unit = {}
) {

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MiddleBrown
        )
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            RatingElement(
                viewState.rating,
                onRatingChanged = onRatingChanged
            )
            Spacer(Modifier.height(8.dp))
            UserShortDetails(viewState.user, onClick = {})
        }
    }

}
