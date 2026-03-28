package com.example.gametime_app.presentation.screens


import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gametime_app.R
import com.example.gametime_app.presentation.viewModel.CreateGameViewModel
import com.example.gametime_app.presentation.viewModel.GetCategoryViewModel
import com.example.gametime_networklibrary.data.dataSources.NetworkResult
import com.example.gametime_networklibrary.data.dto.game.CategoryList
import com.example.gametime_networklibrary.data.dto.game.CreateGameRequest
import com.example.gametime_networklibrary.data.repositoryImpl.AuthRepositoryImpl
import com.example.gametime_uikit.ui.components.BottomSheet
import com.example.gametime_uikit.ui.components.CustomCheckBox
import com.example.gametime_uikit.ui.components.Input
import com.example.gametime_uikit.ui.components.PrimaryButton
import com.example.gametime_uikit.ui.components.Select
import com.example.gametime_uikit.ui.components.SelectItem
//import com.example.gametime_uikit.ui.components.SelectItem
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.theme.CustomTheme

//
// Дата создания: 25.03.2026
// Автор: Дерябина Виолетта

@Composable
fun ScheduleGame(
    modifier: Modifier = Modifier,
    viewModel: GetCategoryViewModel,
    viewModelGame: CreateGameViewModel,
    onClickMain: () -> Unit,
    prefs: AuthRepositoryImpl
) {
    var gameName by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var winnerPrice by remember { mutableStateOf("") }

    var description by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }
    val data by viewModel.data.collectAsStateWithLifecycle()

    var listCategory by remember { mutableStateOf(emptyList<String>()) }

    val token = prefs.getToken() ?: null
    LaunchedEffect(Unit) {
        if (token != null) {
            viewModel.getCategory(token)
        }
    }

    when (val result = data) {
        is NetworkResult.Success -> {
            listCategory = result.data.items.map { it.title }
            Log.i("List Category", (listCategory.toString()))
        }

        is NetworkResult.Error -> {}
        is NetworkResult.Exception -> {}
    }
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
            SpacerHeight(14)
            IconButton(onClick = {}) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.back_icon),
                    contentDescription = "",
                    tint = CustomTheme.colors.accent
                )
            }
            SpacerHeight(36)
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

            var selected by remember { mutableStateOf("") }
            SelectItem(
                modifier = Modifier.fillMaxWidth(),
                category = listCategory,
                value = selected,
                onValueChange = { selected = it },
                placeholder = "Category"
            )

            Input(
                modifier = Modifier.fillMaxWidth(),
                value = winnerPrice,
                onValueChange = { winnerPrice = it },
                placeholder = "Winner Price"
            )
            SpacerHeight(47)


            Select(modifier = Modifier.width(200.dp), label = "FROM")
            Select(modifier = Modifier.width(200.dp), label = "TO")


            SpacerHeight(57)


            Input(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it },
                placeholder = "Description"
            )
            SpacerHeight(55)

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
                        viewModelGame.createGame(
                            token ?: "", CreateGameRequest(
                                gameName = gameName,
//                                category = selected,
                                category = "hf9yve2sqvkey21",
                                winningPrice = winnerPrice.toInt(),
                                from = "",
                                to = "",
                                description = description,
                                notification = checked,
                                host = ""
                            )
                        )
                        Log.i("ScheduleGame", "Создание игры")
                        showBottomSheet = true
                    },
                    modifier = Modifier.width(210.dp),
                    text = "Publish"
                )
            }
            SpacerHeight(67)

            if (showBottomSheet) {
                BottomSheet(
                    showBottomSheet = true,
                    onDismiss = {
                        showBottomSheet = false
                        onClickMain()
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
