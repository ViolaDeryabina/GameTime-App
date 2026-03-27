package com.example.gametime_app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.gametime_uikit.ui.components.Pagination
import com.example.gametime_uikit.ui.components.PrimaryButton
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.theme.CustomTheme
import kotlinx.coroutines.delay


// Автор: Дерябина Виолетта Николаевна
// Дата создания: 12.02.26

@Composable
fun Onboard(
    modifier: Modifier = Modifier,
    onClickRegister: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val pagerState = rememberPagerState(pageCount = { 3 })
            val pagerIsDragged by pagerState.interactionSource.collectIsDraggedAsState()

            val pageInteractionSource = remember { MutableInteractionSource() }
            val pageIsPressed by pageInteractionSource.collectIsPressedAsState()

            val autoAdvance = !pagerIsDragged && !pageIsPressed

            if (autoAdvance) {
                LaunchedEffect(pagerState, pageInteractionSource) {
                    while (true) {
                        delay(2000)
                        val nextPage = (pagerState.currentPage + 1)
                        pagerState.animateScrollToPage(nextPage)
                    }
                }
            }

            SpacerHeight(120)
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
            ) { page ->
                when (page) {
                    0 -> OnboardOne()
                    1 -> OnboardTwo()
                    2 -> OnboardThree()
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            if (pagerState.currentPage == 2) {
                PrimaryButton(
                    onClick = { onClickRegister() },
                    text = "Let’s Combat!",
                    modifier = Modifier
                        .width(224.dp)
                        .height(58.dp)
                )
                SpacerHeight(38)
            } else {
                TextButton(onClick = { onClickRegister() }) {
                    Text(
                        text = "Skip",
                        style = CustomTheme.typography.CaptionSemibold14,
                        textAlign = TextAlign.Center,
                        color = CustomTheme.colors.accent,
                        textDecoration = TextDecoration.Underline
                    )
                }
                SpacerHeight(71)
            }

            Pagination(3, pagerState.currentPage)
            SpacerHeight(45)
        }
    }
}