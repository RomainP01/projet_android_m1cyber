package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.*

@Composable
fun PodiumComponent(
    filteredUsers: List<UserFirebase>,
    isAllTimeScore: Boolean
) {
    val context = LocalContext.current
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (filteredUsers.getOrNull(1)?.profilePictureUrl.toString() != null) {
            val imagePainter =
                rememberAsyncImagePainter(remember(filteredUsers.getOrNull(1)?.profilePictureUrl.toString()) {
                    ImageRequest.Builder(context)
                        .data(filteredUsers.getOrNull(1)?.profilePictureUrl.toString())
                        .memoryCachePolicy(CachePolicy.DISABLED).build()
                })
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
                .background(WhiteBackground, RoundedCornerShape(10.dp))
                .border(width = 2.dp, color = Purple200, RoundedCornerShape(10.dp))
                .padding(vertical = 10.dp)
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
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                fontFamily = TacoCrispy

            )
            Text(
                text = filteredUsers.getOrNull(1)?.pseudo.orEmpty().take(11) ?: "",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
            Text(
                text = "#2nd",
                color = Silver,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(bottom = 5.dp),
                style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold)

            )
        }
    }

    Spacer(modifier = Modifier.width(5.dp))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (filteredUsers.getOrNull(0)?.profilePictureUrl.toString() != null) {
            val imagePainter =
                rememberAsyncImagePainter(remember(filteredUsers.getOrNull(0)?.profilePictureUrl.toString()) {
                    ImageRequest.Builder(context)
                        .data(filteredUsers.getOrNull(0)?.profilePictureUrl.toString())
                        .memoryCachePolicy(CachePolicy.DISABLED).build()
                })
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
                .background(WhiteBackground, RoundedCornerShape(10.dp))
                .border(width = 2.dp, color = Purple200, RoundedCornerShape(10.dp))
                .padding(vertical = 10.dp)
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
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                fontFamily = TacoCrispy
            )
            Text(
                text = filteredUsers.getOrNull(0)?.pseudo.orEmpty().take(13) ?: "",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
            Text(
                text = "#1st",
                color = Gold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(bottom = 5.dp),
                style = TextStyle(fontSize = 27.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
    Spacer(modifier = Modifier.width(5.dp))

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (filteredUsers.getOrNull(2)?.profilePictureUrl.toString() != null) {
            val imagePainter =
                rememberAsyncImagePainter(remember(filteredUsers.getOrNull(2)?.profilePictureUrl.toString()) {
                    ImageRequest.Builder(context)
                        .data(filteredUsers.getOrNull(2)?.profilePictureUrl.toString())
                        .memoryCachePolicy(CachePolicy.DISABLED).build()
                })
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
                .background(WhiteBackground, RoundedCornerShape(10.dp))
                .border(width = 2.dp, color = Purple200, RoundedCornerShape(10.dp))
                .padding(vertical = 10.dp)


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
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp,
                fontFamily = TacoCrispy
            )
            Text(
                text = filteredUsers.getOrNull(2)?.pseudo.orEmpty().take(9) ?: "",
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.BottomCenter)
            )
            Text(
                text = "#3rd",
                color = Bronze,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(bottom = 5.dp),
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)

            )

        }
    }

}