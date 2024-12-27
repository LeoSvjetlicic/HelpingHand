package org.volonter.helpinghand.ui.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import org.volonter.helpinghand.ui.common.viewstates.UserViewState
import org.volonter.helpinghand.ui.theme.Gray15

@Composable
fun UserShortDetails(
    viewState: UserViewState,
    firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(),
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    onClickNavigate: () -> Unit,
    color: Color
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(90.dp))
            .then(
                if (viewState.email != firebaseAuth.currentUser?.email) {
                    Modifier.clickable {
                        onClick(viewState.email)
                        onClickNavigate() }
                } else {
                    Modifier
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            AsyncImage(
                model = viewState.imageLink,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(6.dp))
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(text = viewState.name, fontSize = 16.sp, color = color)
            Text(text = viewState.email, fontSize = 16.sp, color = color)
        }
    }
}
