package org.volonter.helpinghand.ui.screens.addreview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.volonter.helpinghand.ui.screens.addreview.components.AddReviewCard
import org.volonter.helpinghand.ui.screens.addreview.components.ButtonPair
import org.volonter.helpinghand.ui.theme.LightBrown

@Composable
fun AddReviewScreen(
    viewModel: AddReviewViewModel,
    onCancelClick: () -> Unit,
    modifier: Modifier = Modifier,
    onPostClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource by remember { mutableStateOf(MutableInteractionSource()) }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(LightBrown)
            .clickable(interactionSource, null) { focusManager.clearFocus() }
    ) {
        LazyColumn(
            modifier = modifier
                .align(Alignment.Center)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = viewModel.viewState.value.eventName,
                        fontSize = 30.sp,
                        lineHeight = 34.sp
                    )
                    Spacer(Modifier.height(16.dp))
                    AddReviewCard(
                        viewState = viewModel.viewState.value,
                        onAction = viewModel::onAddReviewScreenAction
                    )
                    ButtonPair(
                        onPostClick = {
                            viewModel.onPostClick()
                            onPostClick()
                        },
                        onCancelClick = onCancelClick
                    )
                }
            }
        }
    }
}
