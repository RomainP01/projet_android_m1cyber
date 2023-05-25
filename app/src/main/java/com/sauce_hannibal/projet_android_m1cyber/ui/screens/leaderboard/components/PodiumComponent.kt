package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase

@Composable
fun PodiumComponent(
    filteredUsers :  List<UserFirebase>,
    isAllTimeScore : Boolean
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (filteredUsers.getOrNull(1)?.profilePictureUrl.toString() != null) {
            val imagePainter = rememberAsyncImagePainter(model = filteredUsers.getOrNull(1)?.profilePictureUrl.toString())
            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(75.dp)
                    .clip(CircleShape)
            )
        } else {
            Canvas(
                modifier = Modifier
                    .size(75.dp)
                    .padding(bottom = 5.dp),
                onDraw = { drawCircle(color = Color.LightGray) }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.LightGray)
                .border(width = 2.dp, color = Color.Black)
                .padding(top = 15.dp)
        ) {
            Text(
                text = if (isAllTimeScore) {
                    filteredUsers.getOrNull(1)?.allTimeScore.toString()
                } else {
                    filteredUsers.getOrNull(1)?.dailyScore.toString()
                },
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold

            )
            Text(
                text = filteredUsers.getOrNull(1)?.pseudo.orEmpty().take(11) ?: "",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            Text(
                text = "#2nd",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp)
            )
        }
    }

    Spacer(modifier = Modifier.width(5.dp))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (filteredUsers.getOrNull(0)?.profilePictureUrl.toString() != null) {
            val imagePainter = rememberAsyncImagePainter(model = filteredUsers.getOrNull(0)?.profilePictureUrl.toString())
            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )
        } else {
            Canvas(
                modifier = Modifier
                    .size(90.dp)
                    .padding(bottom = 5.dp),
                onDraw = { drawCircle(color = Color.LightGray) }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier
                .size(150.dp)
                .background(Color.LightGray)
                .border(width = 2.dp, color = Color.Black)
                .padding(top = 5.dp)
        ) {
            Text(
                text = if (isAllTimeScore) {
                    filteredUsers.getOrNull(0)?.allTimeScore.toString()
                } else {
                    filteredUsers.getOrNull(0)?.dailyScore.toString()
                },
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = filteredUsers.getOrNull(0)?.pseudo.orEmpty().take(13) ?: "",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            Text(
                text = "#1st",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp)
            )

        }
    }
    Spacer(modifier = Modifier.width(5.dp))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (filteredUsers.getOrNull(2)?.profilePictureUrl.toString() != null) {
            val imagePainter = rememberAsyncImagePainter(model = filteredUsers.getOrNull(2)?.profilePictureUrl.toString())
            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape)
            )
        } else {
            Canvas(
                modifier = Modifier
                    .size(70.dp)
                    .padding(bottom = 5.dp),
                onDraw = { drawCircle(color = Color.LightGray) }
            )
        }

        Spacer(modifier = Modifier.height(10.dp))


        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.LightGray)
                .border(width = 2.dp, color = Color.Black)
                .padding(top = 5.dp)


        ) {
            Text(
                text = if (isAllTimeScore) {
                    filteredUsers.getOrNull(2)?.allTimeScore.toString()
                } else {
                    filteredUsers.getOrNull(2)?.dailyScore.toString()
                },
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = filteredUsers.getOrNull(2)?.pseudo.orEmpty().take(9) ?: "",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            Text(
                text = "#3rd",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 5.dp)
            )

        }
    }

}