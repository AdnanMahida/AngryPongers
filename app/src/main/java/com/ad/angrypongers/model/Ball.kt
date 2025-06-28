package com.ad.angrypongers.model

import androidx.compose.ui.graphics.Color
import com.ad.angrypongers.util.BALL_RADIUS

data class Ball(
    val x: Float,
    val y: Float,
    val velocityX: Float,
    val velocityY: Float,
    val teamColor: Color,
    val ballColor: Color,
    val radius: Float = BALL_RADIUS
)
