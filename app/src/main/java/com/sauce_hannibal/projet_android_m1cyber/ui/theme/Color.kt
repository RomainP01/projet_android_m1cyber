package com.sauce_hannibal.projet_android_m1cyber.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

val Purple100 = Color(0xFF6D249B)
val Purple200 = Color(0xFF4C2292)
val Pink100 = Color(0xFFB23E63)
val Green100 = Color(0xFF4CE0AA)
val Blue100 = Color(0xFF301A6B)

val TextColorDark = Color(0xFF301A6B)
val TextColorLight = Color.White


val PurplePinkBackground = Brush.linearGradient(
    colors = listOf(
        Pink100,
        Purple100
    ),
    start = Offset.Zero,
    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
    tileMode = TileMode.Clamp
)