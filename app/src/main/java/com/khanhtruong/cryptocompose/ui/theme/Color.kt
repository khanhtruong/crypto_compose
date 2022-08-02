package com.khanhtruong.cryptocompose.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// System design colors
val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


// Optional colors
val GradientColorStart = Color(0xFFF5317F)
val GradientColorEnd = Color(0xFFFF7C6E)

@Composable
fun defaultCardColors(
    containerColor: Color = Color.White,
    contentColor: Color = contentColorFor(containerColor),
    disabledContainerColor: Color = Color.LightGray,
    disabledContentColor: Color = contentColorFor(disabledContainerColor),
): CardColors {
    return CardDefaults.cardColors(
        containerColor,
        contentColor,
        disabledContainerColor,
        disabledContentColor
    )
}