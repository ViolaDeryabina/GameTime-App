package com.example.gametime_app.presentation.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gametime_app.R
import com.example.gametime_app.data.Screens
import com.example.gametime_uikit.ui.components.BottomSheet
import com.example.gametime_uikit.ui.components.CustomCheckBox
import com.example.gametime_uikit.ui.components.Helpers
import com.example.gametime_uikit.ui.components.Input
import com.example.gametime_uikit.ui.components.PrimaryButton
import com.example.gametime_uikit.ui.components.Select
import com.example.gametime_uikit.ui.theme.CustomTheme

//
// Дата создания: 25.03.2026
// Автор: Дерябина Виолетта

@Composable
fun ScheduleGame(modifier: Modifier = Modifier, navController: NavController) {
    var gameName by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var winnerPrice by remember { mutableStateOf("") }

    var description by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.white)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .background(CustomTheme.colors.white)
        ) {
            Helpers.SpacerHeight(14)
            IconButton(onClick = {}) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.back_icon),
                    contentDescription = "",
                    tint = CustomTheme.colors.accent
                )
            }
            Helpers.SpacerHeight(36)
            Text(
                "Schedule Game",
                style = CustomTheme.typography.Caption2Bold12,
                fontSize = 22.sp,
                color = CustomTheme.colors.accent
            )
            Input(
                modifier = Modifier.fillMaxWidth(),
                value = gameName,
                onValueChange = { gameName = it },
                placeholder = "Game Name"
            )
            Input(
                modifier = Modifier.fillMaxWidth(),
                value = category,
                onValueChange = { category = it },
                placeholder = "Category",
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.dropdown),
                        contentDescription = null,
                        tint = CustomTheme.colors.accent
                    )
                }
            )
            Input(
                modifier = Modifier.fillMaxWidth(),
                value = winnerPrice,
                onValueChange = { winnerPrice = it },
                placeholder = "Winner Price"
            )
            Helpers.SpacerHeight(47)


            Select(modifier = Modifier.width(200.dp), label = "FROM")
            Select(modifier = Modifier.width(200.dp), label = "TO")


            Helpers.SpacerHeight(57)


            Input(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it },
                placeholder = "Description"
            )
            Helpers.SpacerHeight(55)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "REMINDERS",
                    style = CustomTheme.typography.Caption2Regular12,
                    color = CustomTheme.colors.accent
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    CustomCheckBox(checked = checked, onCheckedChange = { checked != checked })
                    Text(
                        "Notification",
                        style = CustomTheme.typography.Caption2Regular12,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                PrimaryButton(
                    onClick = {
                        showBottomSheet = true
                    },
                    modifier = Modifier.width(210.dp),
                    text = "Publish"
                )
            }
            Helpers.SpacerHeight(67)

            if (showBottomSheet) {
                BottomSheet(
                    showBottomSheet = true,
                    onDismiss = {
                        showBottomSheet = false
                        navController.navigate(
                            Screens.LandingScreen.route
                        )
                    }
                )
            }

        }
    }
}

@Preview
@Composable
private fun ScheduleGamePrev() {
    CustomTheme {
        //ScheduleGame()
    }
}
