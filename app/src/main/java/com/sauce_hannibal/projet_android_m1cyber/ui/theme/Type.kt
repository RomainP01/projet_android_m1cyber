package com.sauce_hannibal.projet_android_m1cyber.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sauce_hannibal.projet_android_m1cyber.R

val TacoCrispy = FontFamily(
    Font(R.font.taco_crispy)
)

val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    titleLarge = TextStyle(
        fontFamily = TacoCrispy,
        fontWeight = FontWeight.Normal,
        fontSize = 50.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = TacoCrispy,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = TacoCrispy,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
)


