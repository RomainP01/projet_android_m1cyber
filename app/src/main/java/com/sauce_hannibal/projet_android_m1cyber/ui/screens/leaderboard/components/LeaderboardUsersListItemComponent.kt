package com.sauce_hannibal.projet_android_m1cyber.ui.screens.leaderboard.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sauce_hannibal.projet_android_m1cyber.domain.UserFirebase

@Composable
fun LeaderboardUsersListItemComponent(
    currentUser : UserFirebase,
    index : Int,
    isAllTimeScore : Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp, start = 15.dp, end = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "#" + "${index + 4}" + "th",
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
        )
        if (currentUser?.profilePictureUrl != null) {
            val imagePainter = rememberAsyncImagePainter(model = currentUser?.profilePictureUrl)
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
            modifier = Modifier.padding(start = 15.dp) ,
            color = Color.Black
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
            color = Color.Black,
            textAlign = TextAlign.End
        )
    }
}