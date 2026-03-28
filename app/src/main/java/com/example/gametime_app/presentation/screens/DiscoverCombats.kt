package com.example.gametime_app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import com.example.gametime_uikit.ui.components.PopularPlayer
import com.example.gametime_uikit.ui.components.Search
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.components.SpacerWidth
import com.example.gametime_uikit.ui.components.TapBar
import com.example.gametime_uikit.ui.theme.CustomTheme

//
// Дата создания: 25.03.2026
// Автор: Дерябина Виолетта

@Composable
fun DiscoverCombats(
    modifier: Modifier = Modifier,
    onClickCard:()->Unit,
    onClickPopular:()->Unit
) {
    Scaffold(
        bottomBar = {
            TapBar(
                onScheduleClick = { },
                onStatisticsClick = {},
                onDiscoverClick = {},
                onChatClick = { },
                onProfileClick = {}
            )
        },
    ) { innerPadding ->
        val scroll = rememberScrollState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(CustomTheme.colors.accent)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(CustomTheme.colors.accent)
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.35f)
                        .fillMaxWidth()
                        .background(CustomTheme.colors.accent)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 30.dp)
                    ) {
                        SpacerHeight(14)
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.back_icon),
                                contentDescription = "",
                                tint = CustomTheme.colors.white
                            )
                        }
                        SpacerHeight(36)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                "Discover Combats",
                                style = CustomTheme.typography.Caption2Bold12,
                                fontSize = 22.sp,
                                lineHeight = 30.sp,
                                color = CustomTheme.colors.white,
                                modifier = Modifier.width(107.dp)
                            )
                            Text(
                                "FILTER",
                                style = CustomTheme.typography.Caption2Bold12,
                                color = CustomTheme.colors.white,
                            )

                        }
                        SpacerHeight(20)

                        Search(placeholder = "Search Combat", modifier = Modifier.fillMaxWidth())
                        SpacerHeight(29)
                    }
                }

                Column(
                    modifier = Modifier
                        .weight(0.65f)
                        .fillMaxWidth()
                        .background(CustomTheme.colors.white)
                        .padding(horizontal = 30.dp).verticalScroll(scroll)
                ) {
                    SpacerHeight(24)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            "Trending this week",
                            style = CustomTheme.typography.Caption2Bold12,
                            color = CustomTheme.colors.accent,
                        )
                        Row() {
                            Text(
                                "View All",
                                style = CustomTheme.typography.Caption2Bold12,
                                color = CustomTheme.colors.black,
                                fontSize = 8.sp
                            )
                            SpacerWidth(5)

                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.access_icon),
                                contentDescription = "",
                                tint = CustomTheme.colors.accent
                            )
                        }
                    }
                    SpacerHeight(20)
                    CardTwoPlayer(
                        modifier= Modifier.clickable(onClick = {onClickCard()}),
                        gameName = "Halo 5",
                        status = "Open",
                        winnerPrice = 4000,
                        nameFirstPlayer = "Scott Brown",
                        nameSecondPlayer = "Stone Stella"


                    )
                    SpacerHeight(10)

                    CardTwoPlayer(
                        gameName = "Halo 5",
                        status = "Cancelled",
                        winnerPrice = 4000,
                        nameFirstPlayer = "Scott Brown",
                        nameSecondPlayer = "Stone Stella"


                    )
                    SpacerHeight(10)

                    CardTwoPlayer(
                        gameName = "Halo 5",
                        status = "Active",
                        winnerPrice = 4000,
                        nameFirstPlayer = "Scott Brown",
                        nameSecondPlayer = "Stone Stella"


                    )
                    SpacerHeight(10)

                    CardTwoPlayer(
                        gameName = "Halo 5",
                        status = "Active",
                        winnerPrice = 4000,
                        nameFirstPlayer = "Scott Brown",
                        nameSecondPlayer = "Stone Stella"


                    )
                    SpacerHeight(41)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            "Most Popular Players",
                            style = CustomTheme.typography.Caption2Bold12,
                            color = CustomTheme.colors.accent,
                        )
                        Row() {
                            Text(
                                "View All",
                                style = CustomTheme.typography.Caption2Bold12,
                                color = CustomTheme.colors.black,
                                fontSize = 8.sp
                            )
                            SpacerWidth(5)

                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.access_icon),
                                contentDescription = "",
                                tint = CustomTheme.colors.accent
                            )
                        }
                    }
                    SpacerHeight(20)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        PopularPlayer(
                            modifier= Modifier.clickable(onClick = {onClickPopular()}),
                            name = "Scott Brown",
                            level = "Gold Player",
                            status = "Oneline",
                            category = "Action, Soccer..."
                        )
                        PopularPlayer(
                            name = "Teslar fullar",
                            level = "Silver Player",
                            status = "Away",
                            category = "Action, Soccer..."
                        )
                    }
                    SpacerHeight(34)


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            "Latest Combats",
                            style = CustomTheme.typography.Caption2Bold12,
                            color = CustomTheme.colors.accent,
                        )
                        Row() {
                            Text(
                                "View All",
                                style = CustomTheme.typography.Caption2Bold12,
                                color = CustomTheme.colors.black,
                                fontSize = 8.sp
                            )
                            SpacerWidth(5)

                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.access_icon),
                                contentDescription = "",
                                tint = CustomTheme.colors.accent
                            )
                        }
                    }
                    SpacerHeight(20)

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
    }
}

@Preview
@Composable
private fun DiscoverCombatsPrev() {
    CustomTheme {
        //DiscoverCombats()
    }
}

