package com.example.gametime_app.presentation.screens

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gametime_app.presentation.components.PuzzleGrid
import com.example.gametime_uikit.ui.components.PrimaryButton
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.components.Timer
import com.example.gametime_uikit.ui.theme.CustomTheme
import com.example.image.presentation.viewModels.ImageGameViewModel
import io.appmetrica.analytics.AppMetrica

@Composable
fun ImageGameScreen(
    modifier: Modifier = Modifier,
    viewModel: ImageGameViewModel = viewModel(),
    bitmap: Bitmap
) {

    val tiles = viewModel.tiles
    val board = viewModel.board
    val selectedIndex = viewModel.selectedIndex
    val isWin = viewModel.isWin

    LaunchedEffect(bitmap) {
        AppMetrica.reportEvent("Initial game")
        viewModel.start(bitmap)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SpacerHeight(55)
        Text(
            "Game Image",
            style = CustomTheme.typography.Caption2Bold12,
            fontSize = 22.sp,
            color = CustomTheme.colors.accent
        )
        SpacerHeight(30)
        Timer()
        SpacerHeight(26)
        PuzzleGrid(
            tiles = tiles,
            board = board,
            selectedIndex = selectedIndex,
            onTileClick = {
                viewModel.onTileClick(it)
            }
        )
        SpacerHeight(26)
        if (isWin) {
            AppMetrica.reportEvent("Game finished")
            Text(
                "УРА! ПАЗЛ СОБРАН",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF2E7D32)
            )
        }
        PrimaryButton(
            onClick = {
                AppMetrica.reportEvent("Game restarted")
                viewModel.restart()
            },
            text = "Surrender"
        )
        SpacerHeight(69)
    }
}