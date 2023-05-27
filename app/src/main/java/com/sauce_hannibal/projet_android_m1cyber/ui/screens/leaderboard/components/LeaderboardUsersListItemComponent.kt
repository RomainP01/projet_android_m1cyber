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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.BlueBorderUserList
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.WhiteBackgroundUserList

@Composable
fun LeaderboardUsersListItemComponent(
    currentUser: UserFirebase,
    index: Int,
    isAllTimeScore: Boolean
) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = 25.dp)
            .background(WhiteBackgroundUserList, RoundedCornerShape(10.dp))
            .border(2.dp, BlueBorderUserList, RoundedCornerShape(10.dp))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "#" + "${index + 4}" + "th",
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
            color = Color.White,
        )
        if (currentUser.profilePictureUrl != null) {
            val imagePainter =
                rememberAsyncImagePainter(remember(currentUser.profilePictureUrl) {
                    ImageRequest.Builder(context)
                        .data(currentUser.profilePictureUrl)
                        .memoryCachePolicy(CachePolicy.DISABLED).build()
                })
            Image(
                painter = imagePainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape)
            )
        } else {
            Canvas(
                modifier = Modifier
                    .size(30.dp)
                    .padding(bottom = 5.dp),
                onDraw = { drawCircle(color = Color.LightGray) }
            )
        }

        Text(
            text = currentUser.pseudo ?: "",
            modifier = Modifier.padding(start = 15.dp),
            color = Color.White
        )
        Text(
            text = if (isAllTimeScore) {
                currentUser.allTimeScore?.toString() ?: ""
            } else {
                currentUser.dailyScore?.toString() ?: ""
            },
            modifier = Modifier
                .padding(start = 8.dp, end = 16.dp)
                .weight(1f)
                .wrapContentWidth(Alignment.End),
            color = Color.White,
            textAlign = TextAlign.End
        )
    }
}