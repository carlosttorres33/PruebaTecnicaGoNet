package com.carlostorres.pruebatecnicagonet.ui.theme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val BgBlue = Color(0xFF7CF5FF)
val BgPurple = Color(0xFF6439FF)

val AppBg = Brush.linearGradient(
    colors = listOf(
        BgPurple,
        BgBlue,
    ),
    start = Offset.Zero,
    end = Offset.Infinite
)

val DisableBrush = Brush.linearGradient(
    colors = listOf(
        Color.Gray,
        Color.LightGray,
    ),
    start = Offset.Zero,
    end = Offset.Infinite
)