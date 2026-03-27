package com.example.gametime_app.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.gametime_app.R
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.theme.CustomTheme

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
                SpacerHeight(35)
            }

            Text(
                text = data.title,
                modifier = Modifier.width(224.dp),
                style = CustomTheme.typography.Title1ExtraBold24,
                textAlign = TextAlign.Center,
                color = CustomTheme.colors.accent
            )

            SpacerHeight(36)

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

            SpacerHeight(36)

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