package com.example.gametime_app.presentation.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.gametime_app.R
import com.example.gametime_app.presentation.viewModel.ProfileViewModel
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.components.SpacerWidth
import com.example.gametime_uikit.ui.theme.CustomTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel(),
    name: String = "Scott Brown",
    status: String = "Online",
    earned: Int = 5000,
    staked: Int = 2000,
    playerPosition: String = "Gold Player"
) {
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                viewModel.imageURI = uri
            }
        }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.white, shape = RoundedCornerShape(10.dp))
            .border(1.dp, CustomTheme.colors.accent, shape = RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SpacerHeight(16)
            Box(
                modifier.clickable {
                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            ) {
                val painter = viewModel.imageURI?.let { uri ->
                    rememberAsyncImagePainter(uri)
                } ?: painterResource(id = R.drawable.avatar_player_info)
                Image(
                    painter = painter,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(76.41.dp)
                        .fillMaxSize()
                        .border(1.dp, CustomTheme.colors.accent, shape = CircleShape)
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .offset(x = -(3).dp, y = (3).dp)
                        .background(CustomTheme.colors.success, shape = CircleShape)
                        .size(16.dp)
                ) {
                }
            }
            SpacerHeight(8)
            Text(name, style = CustomTheme.typography.Caption2Bold12)

            SpacerHeight(2)
            val color =
                if (status == "Online") CustomTheme.colors.success else CustomTheme.colors.description
            Text(
                buildAnnotatedString {
                    append("Status: ")
                    withStyle(
                        SpanStyle(
                            color
                        )
                    ) {
                        append(status)
                    }
                },
                style = CustomTheme.typography.Caption2Regular12, fontSize = 8.sp,
            )
            SpacerHeight(11)
            Row() {
                Column() {
                    Text(
                        "Earned:",
                        style = CustomTheme.typography.Caption2Regular12,
                        fontSize = 10.sp
                    )
                    SpacerHeight(2)
                    Text(
                        "\$$earned", style = CustomTheme.typography.Caption2Bold12,
                        color = CustomTheme.colors.accent,
                        fontSize = 10.sp
                    )

                }
                SpacerWidth(5)
                Column() {
                    Text(
                        "|", style = CustomTheme.typography.Caption2Bold12,
                        fontSize = 10.sp,
                        color = CustomTheme.colors.description
                    )
                    SpacerHeight(2)
                    Text(
                        "|",
                        style = CustomTheme.typography.Caption2Bold12,
                        fontSize = 10.sp,
                        color = CustomTheme.colors.description
                    )
                }
                SpacerWidth(5)
                Column() {
                    Text(
                        "Staked:",
                        style = CustomTheme.typography.Caption2Regular12,
                        fontSize = 10.sp
                    )
                    SpacerHeight(2)
                    Text(
                        "\$$staked", style = CustomTheme.typography.Caption2Bold12,
                        color = CustomTheme.colors.accent,
                        fontSize = 10.sp
                    )
                }
            }
            SpacerHeight(32)
            val colorPlayer =
                if (playerPosition == "Gold Player") Color(0xFFF4C73E) else Color(0xFFB7B7B7)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.royal_crown_2),
                    contentDescription = "",
                    tint = colorPlayer,
                    modifier = Modifier.size(29.dp)
                )
                SpacerWidth(9)
                Text(
                    playerPosition,
                    style = CustomTheme.typography.Caption2Regular12,
                    fontSize = 18.sp,
                    color = colorPlayer
                )
            }
            SpacerHeight(45)
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPrev() {
    ProfileScreen()
}