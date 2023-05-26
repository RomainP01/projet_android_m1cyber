package com.sauce_hannibal.projet_android_m1cyber.ui.screens.profile

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material3.NavigationBarDefaults.windowInsets
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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.google.accompanist.insets.LocalWindowInsets
import com.sauce_hannibal.projet_android_m1cyber.ui.Route
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.*

@RequiresApi(Build.VERSION_CODES.R)
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
        rememberAsyncImagePainter(remember(uiState.user?.profilePictureUrl) {
            ImageRequest.Builder(context).data(uiState.user?.profilePictureUrl)
                .memoryCachePolicy(CachePolicy.DISABLED).build()
        })
    } else {
        rememberAsyncImagePainter(model = uiState.newUri)
    }

    LaunchedEffect(key1 = uiState, block = {
        if (!uiState.isConnected) {
            navController.navigate(Route.LOGIN)
        }
    })

    Column(
        modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val circleRadius = 180f
        val circleSize = with(LocalDensity.current) { circleRadius.toDp() * 2 }
        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.weight(3f))
            Text(
                text = "Profile",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium.copy(
                    shadow = Shadow(
                        color = Green100, offset = Offset(1f, 1f), blurRadius = 1f
                    )
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    viewModel.logout()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Purple200,
                ),
                border = BorderStroke(2.dp, Color.Black),
                modifier = Modifier
                    .size(100.dp, 30.dp)
                    .padding(end = 10.dp)
            ) {
                Text(
                    text = "Logout",
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 10.sp
                )
            }
        }
        Box(
            modifier = Modifier
                .size(circleSize)
                .clip(CircleShape)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = Color.LightGray, radius = circleRadius, center = center
                )
            }
            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(circleSize)
                    .clip(CircleShape)
            )
        }
        Button(
            onClick = {
                getContent.launch("image/*")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple100,
            ),
            border = BorderStroke(2.dp, Color.Black),

            ) {
            Text(
                "Change Image", color = Color.White, style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = uiState.user?.pseudo ?: "",
            modifier = Modifier.padding(bottom = 16.dp),
            color = Color.White,
            fontSize = 35.sp
        )

        TextField(
            value = uiState.newPseudo ?: "",
            onValueChange = {
                viewModel.setNewPseudo(it)
            },
            label = {
                Text(
                    "New pseudo",
                )
            },
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.saveChanges(uiState.newUri, uiState.newPseudo)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple100,
            ),
            border = BorderStroke(2.dp, Color.Black),
        ) {
            Text(
                "Save changes", color = Color.White, style = MaterialTheme.typography.bodyMedium
            )
        }
        if (uiState.errorMessage != null) {
            Text(uiState.errorMessage!!, color = Color.White)
        }
    }
}
