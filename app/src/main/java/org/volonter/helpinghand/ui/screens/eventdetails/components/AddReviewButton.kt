package org.volonter.helpinghand.ui.screens.eventdetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.theme.GreenGray40

@Composable
fun AddReviewButton(
    modifier: Modifier = Modifier,
    onAddReviewClick: () -> Unit
) {
    Icon(
        modifier = modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(GreenGray40)
            .clickable { onAddReviewClick() }
            .padding(18.dp),
        painter = painterResource(R.drawable.ic_pen),
        contentDescription = null
    )
}
