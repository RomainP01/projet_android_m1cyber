package com.sauce_hannibal.projet_android_m1cyber.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

val BlueDisabled = Color(0xFF191946)
val Blue100 = Color(0xFF301A6B)
val LightBlue100 = Color(0xFF334B95)
val Purple100 = Color(0xFF6D249B)
val Purple200 = Color(0xFF4E1A6B)
val LightPurple100 = Color(0xFF744EF3)
val LightPurple200 = Color(0xFF603BCF)
val Pink100 = Color(0xFFB23E63)
val Red100 = Color(0xFFEB1000)
val GreyDisabled = Color(0xFFA5A5A5)
val WhiteBackground = Color(0xFFEEEAF5)
val WhiteBackgroundUserList = Color(0xFF4D29A1)
val BlueBorderUserList = Color(0xFF5D40BB)
val Gold = Color(0xFFA0842C)
val Silver = Color(0xFF727272)
val Bronze = Color(0xFFA8692B)
val Green100 = Color(0xFF2C7A5C)
val LightGreen100 = Color(0xFF5CFFB6)
val GreenBlue100 = Color(0xFF2EA3CF)

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

val LightPurpleDeepPurpleBackground = Brush.linearGradient(
    colors = listOf(
        LightPurple100,
        Purple100,
    ),
    start = Offset.Zero,
    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
    tileMode = TileMode.Clamp
)

val GreenBlueLightPurpleBackground = Brush.linearGradient(
    colors = listOf(
        GreenBlue100,
        LightPurple200,
    ),
    start = Offset.Zero,
    end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
    tileMode = TileMode.Clamp
)