package com.example.gametime_app.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gametime_app.R
import com.example.gametime_uikit.ui.components.Back
import com.example.gametime_uikit.ui.components.CustomCard
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.components.SpacerWidth
import com.example.gametime_uikit.ui.components.TapBar
import com.example.gametime_uikit.ui.theme.CustomTheme
import kotlinx.coroutines.launch

//
// Дата создания: 25.03.2026
// Автор: Дерябина Виолетта

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingScreen(
    modifier: Modifier = Modifier,
    onClickScheduleGame: () -> Unit,
    onClickDiscoverCombats: () -> Unit,
    onStatisticsClick: () -> Unit,
    onScheduleClick: () -> Unit,
    onProfileScreen: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(CustomTheme.colors.white)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(CustomTheme.colors.white)

                    ) {

                        Column() {
                            SpacerHeight(24)
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 44.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "",
                                        tint = CustomTheme.colors.accent,
                                        modifier = Modifier
                                            .size(20.dp)
                                            .border(
                                                1.dp,
                                                color = CustomTheme.colors.accent,
                                                shape = CircleShape
                                            )
                                            .padding(3.dp)
                                    )
                                }
                            }
                            SpacerHeight(21)

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 60.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Box(modifier = Modifier.size(51.33.dp)) {
                                    Image(
                                        bitmap = ImageBitmap.imageResource(R.drawable.ellipse_3),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(51.33.dp)
                                            .border(
                                                2.dp,
                                                color = CustomTheme.colors.accent,
                                                shape = CircleShape
                                            )
                                    )
                                }
                                SpacerWidth(13)

                                Column() {
                                    Text(
                                        "Stone Stellar",
                                        style = CustomTheme.typography.Caption2Bold12,
                                        fontSize = 16.sp,
                                        color = CustomTheme.colors.accent
                                    )
                                    SpacerHeight(4)
                                    Row(verticalAlignment = Alignment.CenterVertically) {

                                        Icon(
                                            imageVector = ImageVector.vectorResource(R.drawable.echelon),
                                            contentDescription = "",
                                            modifier = Modifier.size(10.dp),
                                            tint = Color(0xFFF4C73E)
                                        )
                                        SpacerWidth(3)
                                        Text(
                                            "Gold Player",
                                            style = CustomTheme.typography.HeadlineRegular16,
                                            color = Color(0xFFF4C73E),
                                            fontSize = 10.sp
                                        )
                                    }


                                }
                            }
                            SpacerHeight(55)
//
                            Column(
                                modifier = Modifier
                                    .padding(start = 60.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.clickable {
                                        onProfileScreen()
                                    }
                                ) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.profile_icon),
                                        contentDescription = "",
                                        tint = CustomTheme.colors.accent
                                    )
                                    SpacerWidth(24)
                                    Text(
                                        text = "My Profile",
                                        style = CustomTheme.typography.HeadlineRegular16
                                    )
                                }
                                SpacerHeight(40)
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.clickable(onClick = {
                                        onClickScheduleGame()
                                    })
                                ) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.schedule_icon),
                                        contentDescription = "",
                                        tint = CustomTheme.colors.accent
                                    )
                                    SpacerWidth(24)
                                    Text(
                                        text = "Schedule",
                                        style = CustomTheme.typography.HeadlineRegular16
                                    )
                                }
                                SpacerHeight(40)
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.clickable(onClick = {
                                        onStatisticsClick()
                                    })
                                ) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.statistics_icon),
                                        contentDescription = "",
                                        tint = CustomTheme.colors.accent
                                    )
                                    SpacerWidth(24)
                                    Text(
                                        text = "Statistics",
                                        style = CustomTheme.typography.HeadlineRegular16
                                    )
                                }
                                SpacerHeight(40)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.location_pin),
                                        contentDescription = "",
                                        tint = CustomTheme.colors.accent
                                    )
                                    SpacerWidth(24)
                                    Text(
                                        text = "Discover Combat",
                                        style = CustomTheme.typography.HeadlineRegular16
                                    )
                                }
                                SpacerHeight(40)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.chat),
                                        contentDescription = "",
                                        tint = CustomTheme.colors.accent
                                    )
                                    SpacerWidth(24)
                                    Text(
                                        text = "Chat",
                                        style = CustomTheme.typography.HeadlineRegular16
                                    )
                                }
                                SpacerHeight(40)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.change_language_icon),
                                        contentDescription = "",
                                        tint = CustomTheme.colors.accent
                                    )
                                    SpacerWidth(24)
                                    Text(
                                        text = "Change Language",
                                        style = CustomTheme.typography.HeadlineRegular16
                                    )
                                }
                                SpacerHeight(40)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = ImageVector.vectorResource(R.drawable.change_skin),
                                        contentDescription = "",
                                        tint = CustomTheme.colors.accent
                                    )
                                    SpacerWidth(24)
                                    Text(
                                        text = "Change App Skin",
                                        style = CustomTheme.typography.HeadlineRegular16
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.weight(1f))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Back(onClick = {})
                            }
                            SpacerHeight(46)


                        }
                    }
                }
            }
        }
    ) {
        Scaffold(
            bottomBar = {
                TapBar(
                    onScheduleClick = { onScheduleClick() },
                    onStatisticsClick = { onStatisticsClick() },
                    onDiscoverClick = { onClickDiscoverCombats() },
                    onChatClick = { },
                    onProfileClick = {}
                )
            },
            topBar = {
                TopAppBar(

                    colors = TopAppBarColors(
                        containerColor = CustomTheme.colors.white,
                        scrolledContainerColor = CustomTheme.colors.white,
                        navigationIconContentColor = CustomTheme.colors.accent,
                        titleContentColor = CustomTheme.colors.black,
                        actionIconContentColor = CustomTheme.colors.white
                    ),
                    title = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 27.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(modifier = Modifier.size(24.dp)) {
                                Image(
                                    bitmap = ImageBitmap.imageResource(R.drawable.avatar),
                                    contentDescription = "",
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                            SpacerWidth(4)
                            Text("Stone Stellar", style = CustomTheme.typography.HeadlineRegular16)
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            modifier = Modifier.padding(start = 27.dp),
                            onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.hamburger),
                                contentDescription = "Menu",
                                tint = CustomTheme.colors.accent
                            )
                        }
                    }
                )
            }
        ) { padding ->
            val scroll = rememberScrollState()
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .background(CustomTheme.colors.white)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scroll),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SpacerHeight(25)
                    CustomCard(
                        title = "Schedule",
                        description = "Easily schedule event/games\n" +
                                "then find like minded players for battle. You up for it?",
                        onClick = { onClickScheduleGame() },
                        image = R.drawable.custom_card1
                    )
                    SpacerHeight(10)

                    CustomCard(
                        title = "Statistics",
                        description = "All data from previous and \n" +
                                "upcoming games can be found here ",
                        onClick = { onStatisticsClick() },
                        image = R.drawable.custom_card2
                    )
                    SpacerHeight(10)
                    CustomCard(
                        title = "Discover  Combats",
                        description = "Find out what’s new and compete among players with new challenges and earn cash with game points ",
                        onClick = {},
                        image = R.drawable.custom_card3
                    )
                    SpacerHeight(10)
                    CustomCard(
                        title = "Message Players",
                        description = "Found the profile of a player\n" +
                                "that interests you? Start a\n" +
                                "conversation",
                        onClick = {},
                        image = R.drawable.custom_card4
                    )
                    SpacerHeight(10)
                }
            }

        }
    }
}

@Preview
@Composable
private fun LandingScreenPrev() {
    CustomTheme {
        LandingScreen(
            onClickScheduleGame = { },
            onClickDiscoverCombats = { },
            onStatisticsClick = { },
            onScheduleClick = {},
            onProfileScreen = {}
        )
    }
}

