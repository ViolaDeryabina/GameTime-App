package com.example.gametime_app.domain

import androidx.compose.ui.geometry.Offset

data class CircleItem(
    val id: Int,
    var current: Offset,
    val radius: Float,
    val width: Float,
    var isFixed: Boolean = false,
)
