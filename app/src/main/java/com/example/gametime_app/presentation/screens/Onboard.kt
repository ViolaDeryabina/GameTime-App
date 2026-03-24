package com.example.gametime_app.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gametime_app.R
import com.example.gametime_uikit.ui.components.Helpers
import com.example.gametime_uikit.ui.components.Pagination
import com.example.gametime_uikit.ui.components.PrimaryButton
import com.example.gametime_uikit.ui.theme.CustomTheme
import kotlinx.coroutines.delay


// Автор: Дерябина Виолетта Николаевна
// Дата создания: 12.02.26

@Composable
fun Onboard(modifier: Modifier = Modifier) {
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

        Helpers.SpacerHeight(120)
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
                onClick = {},
                text = "Let’s Combat!",
                modifier = Modifier
                    .width(224.dp)
                    .height(58.dp)
            )
            Helpers.SpacerHeight(38)
        } else {
            TextButton(onClick = {}) {
                Text(
                    text = "Skip",
                    style = CustomTheme.typography.CaptionSemibold14,
                    textAlign = TextAlign.Center,
                    color = CustomTheme.colors.accent,
                    textDecoration = TextDecoration.Underline
                )
            }
            Helpers.SpacerHeight(71)
        }

        Pagination(3, pagerState.currentPage)
        Helpers.SpacerHeight(45)
    }
}

data class OnboardData(
    val imageRes: Int,
    val title: String,
    val description: String,
    val imagePaddingStart: Int = 48
)

@Composable
fun OnboardPage(
    data: OnboardData,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = data.imagePaddingStart.dp
                    )

            ) {
                Image(
                    bitmap = ImageBitmap.imageResource(data.imageRes),
                    contentDescription = data.title,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (data.imagePaddingStart != 30) {
                Helpers.SpacerHeight(35)
            }

            Text(
                text = data.title,
                modifier = Modifier.width(224.dp),
                style = CustomTheme.typography.Title1ExtraBold24,
                textAlign = TextAlign.Center,
                color = CustomTheme.colors.accent
            )

            Helpers.SpacerHeight(36)

            Text(
                text = data.description,
                modifier = Modifier.width(224.dp),
                style = CustomTheme.typography.CaptionRegular14,
                textAlign = TextAlign.Center,
                color = CustomTheme.colors.black
            )
        }
    }
}

// Использование
@Composable
fun OnboardOne() {
    OnboardPage(
        data = OnboardData(
            imageRes = R.drawable.image1,
            title = "Get Paid! Playing Video Game",
            description = "Earn points and real cash when you win a battle with no delay in cashing out",
            imagePaddingStart = 48
        )
    )
}

@Composable
fun OnboardTwo() {
    OnboardPage(
        data = OnboardData(
            imageRes = R.drawable.image2,
            title = "Schedule Games With Friends",
            description = "Easily create an upcoming event and get ready for battle. Yeah! real combat fella.",
            imagePaddingStart = 27
        )
    )
}

@Composable
fun OnboardThree2() {
    OnboardPage(
        data = OnboardData(
            imageRes = R.drawable.image3,
            title = "Text, Audio and Video Chat",
            description = "Intuitive real life experience on mobile. Chat with fellow gamers before and after combat for free!",
            imagePaddingStart = 27
        )
    )
}

@Composable
fun OnboardThree(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 30.dp, end = 29.dp
                    ),


                ) {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.image3),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Text(
                text = "Text, Audio and Video Chat",
                modifier = Modifier.width(224.dp),
                style = CustomTheme.typography.Title1ExtraBold24,
                textAlign = TextAlign.Center,
                color = CustomTheme.colors.accent
            )

            Helpers.SpacerHeight(36)

            Text(
                text = "Intuitive real life experience on \nmobile. Chat with fellow \ngamers before and after \ncombat for free!",
                modifier = Modifier.width(224.dp),
                style = CustomTheme.typography.CaptionRegular14,
                textAlign = TextAlign.Center,
                color = CustomTheme.colors.black
            )
        }
    }
}


@Preview
@Composable
private fun OnboardOnePrev() {
    CustomTheme {
        OnboardOne()
    }
}

@Preview
@Composable
private fun OnboardPreview() {
    CustomTheme {
        Onboard()
    }
}