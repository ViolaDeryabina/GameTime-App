package com.example.gametime_app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gametime_app.R
import com.example.gametime_networklibrary.data.repositoryImpl.AuthRepositoryImpl
import com.example.gametime_uikit.ui.components.gradient
import com.example.gametime_uikit.ui.theme.CustomTheme
import kotlinx.coroutines.delay


// Автор: Дерябина Виолетта Николаевна
// Дата создания: 12.02.26

@Composable
fun SpalshScreen(
    modifier: Modifier = Modifier,
    onClickOnboard: () -> Unit,
    onClickMain: () -> Unit,
    prefsRepository: AuthRepositoryImpl
) {
    LaunchedEffect(Unit) {
        delay(1000)
        if (prefsRepository.getToken() == null) onClickOnboard() else onClickMain()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient())
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(brush = gradient()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.game_time),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(56.86.dp)
            )
            Text(
                "GAAMETIIME",
                color = CustomTheme.colors.white,
                style = CustomTheme.typography.Caption2Bold12,
                fontSize = 32.sp,
                letterSpacing = 2.sp
            )

            Text(
                "CONNECT WITH YOUR REALITY",
                color = CustomTheme.colors.white,
                style = CustomTheme.typography.Caption2Bold12,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.spash_screen),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = 100.dp)
                    .scale(1.15f),
            )
        }
    }
}

@Preview
@Composable
private fun SpalshScreenPreview() {
    CustomTheme {
        //SpalshScreen()
    }
}