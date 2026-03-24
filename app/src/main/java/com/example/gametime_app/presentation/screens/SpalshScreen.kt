package com.example.gametime_app.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gametime_uikit.ui.components.Helpers
import com.example.gametime_uikit.ui.theme.CustomTheme


// Автор: Дерябина Виолетта Николаевна
// Дата создания: 12.02.26

@Composable
fun SpalshScreen(modifier: Modifier = Modifier) {
    Helpers.gradientActive()
}

@Preview
@Composable
private fun SpalshScreenPreview() {
    CustomTheme {
        SpalshScreen()
    }
}