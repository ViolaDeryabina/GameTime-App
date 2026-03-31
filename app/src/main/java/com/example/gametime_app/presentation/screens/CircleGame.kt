package com.example.gametime_app.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gametime_app.presentation.components.CircleCollectorGame
import com.example.gametime_uikit.ui.components.PrimaryButton
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.components.Timer
import com.example.gametime_uikit.ui.theme.CustomTheme

@Composable
fun CircleGame(modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        SpacerHeight(55)
        Text(
            "Game  Circle",
            style = CustomTheme.typography.Caption2Bold12,
            fontSize = 22.sp,
            color = CustomTheme.colors.accent
        )
        SpacerHeight(30)
        Timer()
        CircleCollectorGame(Modifier.fillMaxWidth().weight(1f))
        PrimaryButton(
            onClick = {},
            text = "Surrender")
        SpacerHeight(69)
    }
}