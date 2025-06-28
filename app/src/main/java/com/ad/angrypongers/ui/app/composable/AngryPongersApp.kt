package com.ad.angrypongers.ui.app.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ad.angrypongers.ui.app.viewmodel.GameViewModel


@Composable
fun AngryPongersApp() {
    val viewModel: GameViewModel = hiltViewModel()
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                GameCanvas(viewModel, modifier = Modifier.size(200.dp))
            }
        }
    }
}