package com.sauce_hannibal.projet_android_m1cyber.ui.screens.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.sauce_hannibal.projet_android_m1cyber.ui.Route
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Blue100
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.Green100

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState = viewModel.profileUiState.collectAsState().value
    val getContent =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { imageUri ->
                viewModel.setNewUri(imageUri)
            }
        }
    val context = LocalContext.current
    val imagePainter = if (uiState.newUri == null) {
        rememberAsyncImagePainter(
            remember(uiState.user?.profilePictureUrl) {
                ImageRequest.Builder(context)
                    .data(uiState.user?.profilePictureUrl)
                    .memoryCachePolicy(CachePolicy.DISABLED)
                    .build()
            }
        )
    } else {
        rememberAsyncImagePainter(model = uiState.newUri)
    }

    LaunchedEffect(key1 = uiState, block = {
        if (!uiState.isConnected) {
            navController.navigate(Route.LOGIN)
        }
    })

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val circleRadius = 250f
        val circleSize = with(LocalDensity.current) { circleRadius.toDp() * 2 }
        Row(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    viewModel.logout()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Blue100)
            ) {
                Text("Logout")
            }
        }
        Box(
            modifier = Modifier
                .size(circleSize)
                .clip(CircleShape)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color.LightGray,
                    radius = circleRadius,
                    center = center
                )
            }
            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
            )
        }
        Button(onClick = {
            getContent.launch("image/*")
        }) {
            Text("Change Image")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = uiState.user?.pseudo ?: "",
            modifier = Modifier.padding(bottom = 16.dp),
            color = Color.White,
            style = TextStyle(
                shadow = Shadow(
                    color = Green100,
                    offset = Offset(3f, 3f),
                    blurRadius = 1f
                )
            ),
            fontSize = 35.sp
        )

        TextField(
            value = uiState.newPseudo ?: "",
            onValueChange = {
                viewModel.setNewPseudo(it)
            },
            label = { Text("New pseudo") },
        )

        Button(onClick = {
            viewModel.saveChanges(uiState.newUri, uiState.newPseudo)
        }) {
            Text("Save changes")
        }
        if (uiState.errorMessage != null) {
            Text(uiState.errorMessage!!)
        }


    }
}
