package com.ad.angrypongers.ui.app.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ad.angrypongers.engine.GameEngine
import com.ad.angrypongers.engine.GameStateFactory
import com.ad.angrypongers.model.GameState
import com.ad.angrypongers.util.FRAME_DURATION_MS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor() : ViewModel() {
    private val engine = GameEngine()
    private val _state = mutableStateOf(GameStateFactory.initial())
    val state: MutableState<GameState> = _state

    private var lastFrameTime = System.currentTimeMillis()

    init {
        startGameLoop()
    }

    private fun startGameLoop() {
        viewModelScope.launch(Dispatchers.Default) {
            while (true) {
                val now = System.currentTimeMillis()
                val delta = now - lastFrameTime
                if (delta >= FRAME_DURATION_MS) {
                    _state.value = engine.updateGameState(_state.value, delta)
                    lastFrameTime = now
                }
                delay(1)
            }
        }
    }
}