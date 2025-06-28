package com.ad.angrypongers.ui.app.composable

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.ad.angrypongers.ui.app.viewmodel.GameViewModel
import com.ad.angrypongers.util.GRID_HEIGHT
import com.ad.angrypongers.util.GRID_WIDTH
import com.ad.angrypongers.util.SQUARE_SIZE


@Composable
fun GameCanvas(viewModel: GameViewModel, modifier: Modifier = Modifier) {
    val state by viewModel.state


    Canvas(modifier = modifier) {

        // Draw grid
        for (x in 0 until GRID_WIDTH) {
            for (y in 0 until GRID_HEIGHT) {
                drawRect(
                    color = state.grid[x][y],
                    topLeft = Offset(x * SQUARE_SIZE.toFloat(), y * SQUARE_SIZE.toFloat()),
                    size = Size(SQUARE_SIZE.toFloat(), SQUARE_SIZE.toFloat())
                )
            }
        }

        // Draw balls
        state.balls.forEach { ball ->
            drawCircle(
                color = ball.ballColor,
                radius = ball.radius,
                center = Offset(ball.x, ball.y)
            )
        }
    }
}
