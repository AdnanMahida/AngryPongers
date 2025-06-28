package com.ad.angrypongers.model

import androidx.compose.ui.graphics.Color

data class GameState(
    val grid: Array<Array<Color>>,
    val balls: List<Ball>,
    val dayScore: Int,
    val nightScore: Int,
    val isPlaying: Boolean,
    val frameCount: Long,
    val gameTime: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as GameState

        if (dayScore != other.dayScore) return false
        if (nightScore != other.nightScore) return false
        if (isPlaying != other.isPlaying) return false
        if (frameCount != other.frameCount) return false
        if (gameTime != other.gameTime) return false
        if (!grid.contentDeepEquals(other.grid)) return false
        if (balls != other.balls) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dayScore
        result = 31 * result + nightScore
        result = 31 * result + isPlaying.hashCode()
        result = 31 * result + frameCount.hashCode()
        result = 31 * result + gameTime.hashCode()
        result = 31 * result + grid.contentDeepHashCode()
        result = 31 * result + balls.hashCode()
        return result
    }
}