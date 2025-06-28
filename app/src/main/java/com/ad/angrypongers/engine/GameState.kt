package com.ad.angrypongers.engine

import com.ad.angrypongers.model.Ball
import com.ad.angrypongers.model.GameState
import com.ad.angrypongers.util.CANVAS_HEIGHT
import com.ad.angrypongers.util.CANVAS_WIDTH
import com.ad.angrypongers.util.DAY_BALL_COLOR
import com.ad.angrypongers.util.DAY_COLOR
import com.ad.angrypongers.util.GRID_HEIGHT
import com.ad.angrypongers.util.GRID_WIDTH
import com.ad.angrypongers.util.INITIAL_VELOCITY
import com.ad.angrypongers.util.NIGHT_BALL_COLOR
import com.ad.angrypongers.util.NIGHT_COLOR


object GameStateFactory {
    fun initial(): GameState {
        val grid = Array(GRID_WIDTH) { x ->
            Array(GRID_HEIGHT) { y ->
                if (x < GRID_WIDTH / 2) DAY_COLOR else NIGHT_COLOR
            }
        }

        val dayBall = Ball(
            x = CANVAS_WIDTH / 4f,
            y = CANVAS_HEIGHT / 2f,
            velocityX = INITIAL_VELOCITY,
            velocityY = -INITIAL_VELOCITY,
            teamColor = DAY_COLOR,
            ballColor = DAY_BALL_COLOR
        )

        val nightBall = Ball(
            x = (CANVAS_WIDTH * 3 / 4f),
            y = CANVAS_HEIGHT / 2f,
            velocityX = -INITIAL_VELOCITY,
            velocityY = INITIAL_VELOCITY,
            teamColor = NIGHT_COLOR,
            ballColor = NIGHT_BALL_COLOR
        )

        return GameState(
            grid = grid,
            balls = listOf(dayBall, nightBall),
            dayScore = GRID_WIDTH * GRID_HEIGHT / 2,
            nightScore = GRID_WIDTH * GRID_HEIGHT / 2,
            isPlaying = true,
            frameCount = 0,
            gameTime = 0L
        )
    }
}
