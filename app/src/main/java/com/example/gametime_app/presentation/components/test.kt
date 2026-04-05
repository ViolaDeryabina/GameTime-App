package com.example.gametime_app.presentation.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.zIndex
import io.appmetrica.analytics.impl.bi
import io.appmetrica.analytics.impl.bo

@Composable
fun PuzzleTiles(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick:()->Unit,
    bitmap: ImageBitmap

) {
    val scale by animateFloatAsState(
        targetValue = if(isSelected)1.2f else 1f
    )
    Box(
        modifier=modifier
            .aspectRatio(1f)
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
            .zIndex(if (isSelected) 20f else 0f)
            .clickable(onClick = { onClick() })
    ) {
        Image(
            bitmap = bitmap,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            contentDescription = ""
        )
    }
}


@Composable
fun PuzzleGrid(
    modifier: Modifier = Modifier,
    tiles: List<ImageBitmap>,
    board: List<Int>,
    selectedIndex: Int?,
    onClick: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxSize()
    ) {
        itemsIndexed(board){gridItems,tileItem->
            PuzzleTiles(
                isSelected = selectedIndex==gridItems,
                bitmap = tiles[tileItem],
                onClick = {onClick()}
            )
        }
    }
}