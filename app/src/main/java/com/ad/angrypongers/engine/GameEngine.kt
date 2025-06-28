package com.ad.angrypongers.engine

import androidx.compose.ui.graphics.Color
import com.ad.angrypongers.model.Ball
import com.ad.angrypongers.model.GameState
import com.ad.angrypongers.util.BALL_RADIUS
import com.ad.angrypongers.util.CANVAS_HEIGHT
import com.ad.angrypongers.util.CANVAS_WIDTH
import com.ad.angrypongers.util.DAY_COLOR
import com.ad.angrypongers.util.FRAME_DURATION_MS
import com.ad.angrypongers.util.GRID_HEIGHT
import com.ad.angrypongers.util.GRID_WIDTH
import com.ad.angrypongers.util.MAX_SPEED
import com.ad.angrypongers.util.MIN_SPEED
import com.ad.angrypongers.util.NIGHT_COLOR
import com.ad.angrypongers.util.RANDOMNESS_FACTOR
import com.ad.angrypongers.util.SQUARE_SIZE
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sign
import kotlin.math.sin
import kotlin.random.Random

class GameEngine {
    fun updateGameState(current: GameState, deltaTime: Long): GameState {
        val newBalls = current.balls.map { updateBallPosition(it, deltaTime) }
        val newGrid = current.grid.map { it.copyOf() }.toTypedArray()

        newBalls.forEach { ball ->
            applyTerritoryConquest(ball, newGrid)
        }

        val (dayScore, nightScore) = calculateScore(newGrid)
        val gameTime = current.gameTime + deltaTime

        return current.copy(
            balls = newBalls,
            grid = newGrid,
            dayScore = dayScore,
            nightScore = nightScore,
            frameCount = current.frameCount + 1,
            gameTime = gameTime
        )
    }

    fun updateBallPosition(ball: Ball, deltaTime: Long): Ball {
        var vx =
            ball.velocityX + Random.Default.nextFloat() * 2 * RANDOMNESS_FACTOR - RANDOMNESS_FACTOR
        var vy =
            ball.velocityY + Random.Default.nextFloat() * 2 * RANDOMNESS_FACTOR - RANDOMNESS_FACTOR

        vx = vx.coerceIn(-MAX_SPEED, MAX_SPEED)
        vy = vy.coerceIn(-MAX_SPEED, MAX_SPEED)

        if (abs(vx) < MIN_SPEED) vx = MIN_SPEED * vx.sign
        if (abs(vy) < MIN_SPEED) vy = MIN_SPEED * vy.sign

        var x = ball.x + vx * deltaTime / FRAME_DURATION_MS
        var y = ball.y + vy * deltaTime / FRAME_DURATION_MS

        if (x <= BALL_RADIUS || x >= CANVAS_WIDTH - BALL_RADIUS) vx = -vx
        if (y <= BALL_RADIUS || y >= CANVAS_HEIGHT - BALL_RADIUS) vy = -vy

        x = x.coerceIn(BALL_RADIUS, CANVAS_WIDTH - BALL_RADIUS)
        y = y.coerceIn(BALL_RADIUS, CANVAS_HEIGHT - BALL_RADIUS)

        return ball.copy(x = x, y = y, velocityX = vx, velocityY = vy)
    }

    fun applyTerritoryConquest(ball: Ball, grid: Array<Array<Color>>) {
        for (angle in 0 until 360 step 45) {
            val rad = Math.toRadians(angle.toDouble())
            val cx = (ball.x + cos(rad) * BALL_RADIUS).toInt()
            val cy = (ball.y + sin(rad) * BALL_RADIUS).toInt()
            val gx = cx / SQUARE_SIZE
            val gy = cy / SQUARE_SIZE
            if (gx in 0 until GRID_WIDTH && gy in 0 until GRID_HEIGHT) {
                grid[gx][gy] = ball.teamColor
            }
        }
    }

    fun calculateScore(grid: Array<Array<Color>>): Pair<Int, Int> {
        var day = 0
        var night = 0
        for (x in grid.indices) {
            for (y in grid[x].indices) {
                when (grid[x][y]) {
                    DAY_COLOR -> day++
                    NIGHT_COLOR -> night++
                }
            }
        }
        return day to night
    }
}