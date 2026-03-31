package com.example.gametime_app.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.zIndex

@Composable
fun PuzzleTile(
    modifier: Modifier = Modifier,
    bitmap: ImageBitmap,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.2f else 1f, // Увеличиваем на 15%
        label = "tileScale"
    )
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                shadowElevation = if (isSelected) 20f else 0f,
            )
            .zIndex(if (isSelected) 1f else 0f)
            .clickable(onClick = onClick)
    ) {
        Image(
            bitmap = bitmap,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}