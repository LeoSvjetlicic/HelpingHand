package org.volonter.helpinghand.ui.screens.settings

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.volonter.helpinghand.R
import org.volonter.helpinghand.ui.common.components.BackgroundTextFieldWithLabel
import org.volonter.helpinghand.ui.common.components.PrimaryButton
import org.volonter.helpinghand.ui.theme.MiddleBrown
import org.volonter.helpinghand.utlis.Constants.SharedPreferencesConstants.SHARED_PREFERENCES_IS_ORGANISATION
import org.volonter.helpinghand.utlis.SharedPreferencesHelper

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    modifier: Modifier = Modifier,
    onSaveClick : () -> Unit
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var uploading by remember { mutableStateOf(false) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let {
                imageUri = it
            }
        }
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 42.dp)

    ){
        Box(modifier = modifier.size(200.dp)){
            AsyncImage(
                model = imageUri ?: viewModel.viewState.value.user.imageLink ?: R.drawable.ic_user,  //imageUri ?: user?.profileImageUrl ?: R.drawable.ic_person,
                contentDescription = "User profile image",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(Color.Black),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                IconButton(
                    onClick = { galleryLauncher.launch("image/*") },
                    modifier = modifier
                        .align(Alignment.Center)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = "Add Image",
                        modifier = modifier.size(56.dp),
                        tint = Color.Black
                        )
                }
            }
        }
        Spacer(modifier = modifier.height(24.dp))

        BackgroundTextFieldWithLabel(
            viewState = viewModel.viewState.value.newUsername,
            maxLines = 1,
            onQueryChange = { value ->
                viewModel.onSettingsScreenAction(ChangeUsername(value))
            }
        )
        Spacer(modifier = modifier.height(16.dp))
        if (SharedPreferencesHelper.getBooleanFromSharedPrefs(SHARED_PREFERENCES_IS_ORGANISATION)) {
            BackgroundTextFieldWithLabel(
                viewState = viewModel.viewState.value.description,
                maxLines = 4,
                onQueryChange = { value ->
                    viewModel.onSettingsScreenAction(ChangeDescription(value))
                }
            )
            Spacer(modifier = modifier.height(16.dp))
        }

        PrimaryButton(
            backgroundColor = MiddleBrown,
            onClick = { onSaveClick() },
        ) {
            Text(text = stringResource(R.string.save), fontSize = 16.sp)
        }
    }
}
