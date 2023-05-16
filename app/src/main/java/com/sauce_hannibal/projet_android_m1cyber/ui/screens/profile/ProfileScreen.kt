package com.sauce_hannibal.projet_android_m1cyber.ui.screens.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState = viewModel.profileUiState.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val circleRadius = 250f
        val circleSize = with(LocalDensity.current) { circleRadius.toDp() * 2 }
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

            uiState.user?.profilePictureUrl?.let { imageUrl ->
                val imagePainter = rememberAsyncImagePainter(model = imageUrl)
                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }
        }
        Button(onClick = {

        }) {
            Text("Change Image")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = uiState.user?.pseudo ?: "",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = uiState.user?.pseudo ?: "",
            onValueChange = { /* Handle pseudo change */ },
            label = { Text("New Pseudo") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = {}) {
            Text("Save")
        }


    }
}
