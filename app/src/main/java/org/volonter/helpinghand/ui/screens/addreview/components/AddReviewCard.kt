package org.volonter.helpinghand.ui.screens.addreview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.components.UserShortDetails
import org.volonter.helpinghand.ui.screens.addreview.AddReviewCardActions
import org.volonter.helpinghand.ui.screens.addreview.AddReviewViewState
import org.volonter.helpinghand.ui.screens.addreview.ChangeBody
import org.volonter.helpinghand.ui.screens.addreview.ChangeRating
import org.volonter.helpinghand.ui.screens.addreview.ChangeTitle
import org.volonter.helpinghand.ui.screens.eventdetails.components.RatingElement
import org.volonter.helpinghand.ui.theme.MiddleBrown

@Composable
fun AddReviewCard(
    viewState: AddReviewViewState,
    modifier: Modifier = Modifier,
    onAction: (AddReviewCardActions) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MiddleBrown
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            RatingElement(
                viewState.rating,
                onRatingChanged = { onAction(ChangeRating(it)) }
            )
            Spacer(Modifier.height(8.dp))
            UserShortDetails(viewState.user, onClick = {})
            Spacer(Modifier.height(8.dp))
            BackgroundTextFieldWithLabel(
                label = stringResource(R.string.topic),
                query = viewState.title,
                isError = viewState.isTitleError,
                maxLines = 1,
                onQueryChange = {
                    onAction(ChangeTitle(it))
                }
            )
            Spacer(Modifier.height(12.dp))
            BackgroundTextFieldWithLabel(
                label = stringResource(R.string.body),
                query = viewState.body,
                isError = viewState.isBodyError,
                maxLines = Int.MAX_VALUE,
                onQueryChange = {
                    onAction(ChangeBody(it))
                }
            )
        }
    }
}
