package com.example.gametime_app.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gametime_app.R
import com.example.gametime_uikit.ui.components.CardTwoPlayer
import com.example.gametime_uikit.ui.components.PlayerInfo
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.components.SpacerWidth
import com.example.gametime_uikit.ui.theme.CustomTheme

//
// Дата создания: 25.03.2026
// Автор: Дерябина Виолетта

@Composable
fun PlayerInformation(
    modifier: Modifier = Modifier,
    onClickBack: () -> Unit

) {
    val scroll = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.white)
            .verticalScroll(scroll)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .background(CustomTheme.colors.white)
        ) {
            SpacerHeight(14)
            IconButton(onClick = { onClickBack() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.back_icon),
                    contentDescription = "",
                    tint = CustomTheme.colors.accent
                )
            }
            SpacerHeight(36)
            Text(
                "Player Information",
                style = CustomTheme.typography.Caption2Bold12,
                fontSize = 22.sp,
                modifier = Modifier.width(139.dp),
                lineHeight = 30.sp,
                color = CustomTheme.colors.accent,

                )
            SpacerHeight(16)
            PlayerInfo(modifier.fillMaxWidth())
            SpacerHeight(21)
            Text(
                "CATEGORY",
                style = CustomTheme.typography.Caption2Bold12,
                fontSize = 8.sp,
                color = CustomTheme.colors.accent
            )
            SpacerHeight(11)
            val listCategory = listOf("Image", "Circles")
            Row() {
                listCategory.forEach { listCategory ->
                    Box(
                        modifier = Modifier
                            .size(59.dp, 20.dp)
                            .background(CustomTheme.colors.white, shape = RoundedCornerShape(3.dp))
                            .border(
                                1.dp,
                                CustomTheme.colors.accent,
                                shape = RoundedCornerShape(3.dp)
                            ),
                        contentAlignment = Alignment.Center

                    ) {
                        Text(
                            listCategory,
                            style = CustomTheme.typography.Caption2Bold12,
                            fontSize = 10.sp,
                            color = CustomTheme.colors.accent
                        )
                    }
                    SpacerWidth(10)
                }
            }
            SpacerHeight(16)
            Text(
                "Player Combats",
                style = CustomTheme.typography.Caption2Bold12,
                fontSize = 14.sp,
                modifier = Modifier.width(66.dp),
                lineHeight = 20.sp,
                color = CustomTheme.colors.accent,

                )
            SpacerHeight(12)
            CardTwoPlayer(
                gameName = "Halo 5",
                status = "Open",
                winnerPrice = 4000,
                nameFirstPlayer = "Scott Brown",
                nameSecondPlayer = "Stone Stella"


            )
            SpacerHeight(10)
            CardTwoPlayer(
                gameName = "Halo 5",
                status = "Open",
                winnerPrice = 4000,
                nameFirstPlayer = "Scott Brown",
                nameSecondPlayer = "Stone Stella"


            )
            SpacerHeight(10)
            CardTwoPlayer(
                gameName = "Halo 5",
                status = "Open",
                winnerPrice = 4000,
                nameFirstPlayer = "Scott Brown",
                nameSecondPlayer = "Stone Stella"


            )
            SpacerHeight(10)
            CardTwoPlayer(
                gameName = "Halo 5",
                status = "Open",
                winnerPrice = 4000,
                nameFirstPlayer = "Scott Brown",
                nameSecondPlayer = "Stone Stella"


            )
        }
    }
}

@Preview
@Composable
private fun PlayerInformationPrev() {
    CustomTheme {
        //PlayerInformation()
    }
}
