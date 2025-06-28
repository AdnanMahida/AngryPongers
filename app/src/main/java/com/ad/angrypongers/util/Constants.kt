package com.ad.angrypongers.util

import androidx.compose.ui.graphics.Color


const val CANVAS_WIDTH = 600
const val CANVAS_HEIGHT = 600
const val SQUARE_SIZE = 25
const val GRID_WIDTH = CANVAS_WIDTH / SQUARE_SIZE
const val GRID_HEIGHT = CANVAS_HEIGHT / SQUARE_SIZE

const val BALL_RADIUS = SQUARE_SIZE / 2f
const val MIN_SPEED = 5.0f
const val MAX_SPEED = 10.0f
const val INITIAL_VELOCITY = 8.0f
const val RANDOMNESS_FACTOR = 0.01f

const val TARGET_FPS = 100
const val FRAME_DURATION_MS = 1000L / TARGET_FPS

val DAY_COLOR = Color(0xFFD9E8E3)
val DAY_BALL_COLOR = Color(0xFF114C5A)
val NIGHT_COLOR = Color(0xFF114C5A)
val NIGHT_BALL_COLOR = Color(0xFFD9E8E3)
