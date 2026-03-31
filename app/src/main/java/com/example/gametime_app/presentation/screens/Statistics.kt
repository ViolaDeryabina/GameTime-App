package com.example.gametime_app.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gametime_app.R
import com.example.gametime_app.presentation.viewModel.GetStatisticViewModel
import com.example.gametime_networklibrary.data.repositoryImpl.AuthRepositoryImpl
import com.example.gametime_uikit.ui.components.BarChart
import com.example.gametime_uikit.ui.components.DonutChart
import com.example.gametime_uikit.ui.components.SpacerHeight
import com.example.gametime_uikit.ui.theme.CustomTheme

@Composable
fun Statistics(
    modifier: Modifier = Modifier,
    viewModel: GetStatisticViewModel,
    onClickBack: () -> Unit,
    prefs: AuthRepositoryImpl
) {
    val token = prefs.getToken() ?: null
    LaunchedEffect(Unit) {
        if (token != null) {
            viewModel.getStatistic(token)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        SpacerHeight(14)
        IconButton(onClick = {onClickBack()}) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.back_icon),
                contentDescription = "",
                tint = CustomTheme.colors.accent
            )
        }
        SpacerHeight(36)
        Text(
            "Statistics",
            style = CustomTheme.typography.Caption2Bold12,
            fontSize = 22.sp,
            color = CustomTheme.colors.accent
        )
        SpacerHeight(25)
        Text("Played Games",
            style = CustomTheme.typography.Caption2Bold12,
            fontSize = 22.sp,
            color = CustomTheme.colors.accent)
        SpacerHeight(35)
        DonutChart(
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.CenterHorizontally)
        )
        SpacerHeight(35)
        Text("Scheduled Games",
            style = CustomTheme.typography.Caption2Bold12,
            fontSize = 22.sp,
            color = CustomTheme.colors.accent)
        SpacerHeight(60)
        BarChart(
            modifier = Modifier
                .size(width = 200.dp, height = 140.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Preview
@Composable
private fun StatisticsPrev() {
    //Statistics(onClickBack = {})
}