package com.example.gametime_app.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.gametime_app.R
import com.example.gametime_uikit.ui.components.BottomSheet
import com.example.gametime_uikit.ui.components.CombatInfo
import com.example.gametime_uikit.ui.components.CustomCheckBox
import com.example.gametime_uikit.ui.components.PrimaryButton
import com.example.gametime_uikit.ui.components.Select
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.components.gradient
import com.example.gametime_uikit.ui.theme.CustomTheme

//
// Дата создания: 25.03.2026
// Автор: Дерябина Виолетта

@Composable
fun CombatInformation(
    modifier: Modifier = Modifier,
    onClickPlayerInfo: () -> Unit,
    onClickBack: () -> Unit,
    onCircleGame: () -> Unit,
    onImageGame: () -> Unit
) {
    val isCircle = false
    var showBottomSheet by remember { mutableStateOf(false) }
    var checked by remember { mutableStateOf(false) }
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
            SpacerHeight(30)
            Text(
                "Combat Information",
                style = CustomTheme.typography.Caption2Bold12,
                fontSize = 22.sp,
                modifier = Modifier.width(139.dp),
                lineHeight = 30.sp,
                color = CustomTheme.colors.accent,
            )
            SpacerHeight(16)
            CombatInfo(modifier = Modifier.fillMaxWidth())

            SpacerHeight(27)
            Text(
                "DESCRIPTION",
                style = CustomTheme.typography.Caption2Bold12,
                fontSize = 6.sp,
                color = CustomTheme.colors.accent
            )
            SpacerHeight(8)
            Text(
                "Hi players, here’s the jerk; I need 6  solid gamers to join me on this quest playing NFS(Rivals 2) you all will be rewarded according to the ya’ positions, hits and points. Ready for this challange? Let’s rock!!! ",
                style = CustomTheme.typography.Caption2Regular12,
                fontSize = 10.sp,
                color = CustomTheme.colors.black
            )

            SpacerHeight(23)

            Text(
                "CATEGORY",
                style = CustomTheme.typography.Caption2Bold12,
                fontSize = 6.sp,
                color = CustomTheme.colors.accent
            )
            SpacerHeight(8)
            Text(
                "Image",
                style = CustomTheme.typography.Caption2Regular12,
                fontSize = 10.sp,
                color = CustomTheme.colors.black
            )
            SpacerHeight(23)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        "1ST POSITION",
                        style = CustomTheme.typography.Caption2Bold12,
                        fontSize = 6.sp,
                        color = CustomTheme.colors.accent
                    )
                    SpacerHeight(8)
                    Text(
                        "$2000",
                        style = CustomTheme.typography.Caption2Regular12,
                        fontSize = 10.sp,
                        color = CustomTheme.colors.black
                    )
                }
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        "2ND POSITION",
                        style = CustomTheme.typography.Caption2Bold12,
                        fontSize = 6.sp,
                        color = CustomTheme.colors.accent
                    )
                    SpacerHeight(8)

                    Text(
                        "$1000",
                        style = CustomTheme.typography.Caption2Regular12,
                        fontSize = 10.sp,
                        color = CustomTheme.colors.black
                    )
                }
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        "3RD POSITION",
                        style = CustomTheme.typography.Caption2Bold12,
                        fontSize = 6.sp,
                        color = CustomTheme.colors.accent
                    )
                    SpacerHeight(8)

                    Text(
                        "$500",
                        style = CustomTheme.typography.Caption2Regular12,
                        fontSize = 10.sp,
                        color = CustomTheme.colors.black
                    )
                }
                Column(horizontalAlignment = Alignment.Start) {
                    Text(
                        "4TH 5TH 6TH POSITION",
                        style = CustomTheme.typography.Caption2Bold12,
                        fontSize = 6.sp,
                        color = CustomTheme.colors.accent
                    )
                    SpacerHeight(8)

                    Text(
                        "$160",
                        style = CustomTheme.typography.Caption2Regular12,
                        fontSize = 10.sp,
                        color = CustomTheme.colors.black
                    )
                }
            }


            SpacerHeight(47)

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .background(brush = gradient(), shape = CustomTheme.shapes.roundedCornerShape)
                    .padding(start = 20.dp, top = 17.dp)
            ) {
                Column() {
                    Row() {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.time_icon),
                            contentDescription = "",
                            tint = CustomTheme.colors.white
                        )
                        Text(
                            "TIME INTERVAL",
                            style = CustomTheme.typography.Caption2Bold12,
                            color = CustomTheme.colors.white,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                    }
                    Select(
                        modifier = Modifier.width(200.dp),
                        label = "FROM",
                        color = CustomTheme.colors.white,
                        textColor = CustomTheme.colors.white
                    )
                    Select(
                        modifier = Modifier.width(200.dp),
                        label = "TO",
                        color = CustomTheme.colors.white,
                        textColor = CustomTheme.colors.white
                    )
                }
            }

            SpacerHeight(57)
            Text(
                "REMINDERS",
                style = CustomTheme.typography.Caption2Regular12,
                color = CustomTheme.colors.accent,
                fontSize = 6.sp,
                modifier = Modifier.padding(start = 12.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {
                    CustomCheckBox(checked = checked, onCheckedChange = { checked = !checked })
                    Text(
                        "Notification",
                        style = CustomTheme.typography.Caption2Regular12,
                    )
                }
            }
            SpacerHeight(45)
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                PrimaryButton(
                    onClick = {
                        //showBottomSheet = true
                        if (isCircle) {
                            onCircleGame()
                        } else {
                            onImageGame()
                        }
                    },
                    modifier = Modifier.width(210.dp),
                    text = "Join Combat!"
                )
            }
            SpacerHeight(44)

            if (showBottomSheet) {
                BottomSheet(
                    showBottomSheet = true,
                    onDismiss = {
                        showBottomSheet = false
                        onClickPlayerInfo()
                    },
                    title = "Successfully Join Combat",
                    description = "Wanna know more information bout’ this competition?",
                    textButton = "Discover combats"
                )
            }

        }
    }
}

@Preview
@Composable
private fun CombatInformationPrev() {
    CustomTheme {
        //CombatInformation()
    }
}
