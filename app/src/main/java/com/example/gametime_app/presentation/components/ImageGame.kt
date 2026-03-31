package com.example.gametime_app.presentation.components

import android.graphics.Bitmap
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun ImageGame(
    modifier: Modifier = Modifier,
    bitmap: Bitmap
) {
    val tiles = remember(bitmap) {
        val size = minOf(bitmap.width, bitmap.height) / 3
        val list = mutableListOf<ImageBitmap>()
        for (i in (0..2)) {
            for (j in (0..2)) {
                val x = j * size
                val y = i * size
                list.add(Bitmap.createBitmap(bitmap, x, y, size, size).asImageBitmap())
            }
        }
        list
    }

    var board by remember { mutableStateOf((0..8).toList().shuffled()) }
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    val isWin = board == (0..8).toList()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color(0xFFF22E63))
                .padding(vertical = 20.dp, horizontal = 10.dp)
        ) {
            itemsIndexed(board) { gridIndex, tileIndex ->
                val isSelected = selectedIndex == gridIndex
                val scale by animateFloatAsState(
                    targetValue = if (isSelected) 1.2f else 1f, // Увеличиваем на 15%
                    label = "tileScale"
                )

                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            shadowElevation = if (isSelected) 20f else 0f,
                        )
                        .zIndex(if (isSelected) 1f else 0f)
                        .clickable {
                            if (selectedIndex != null) {
                                val newList = board.toMutableList()
                                val temp = newList[selectedIndex!!]
                                newList[selectedIndex!!] = newList[gridIndex]
                                newList[gridIndex] = temp

                                board = newList
                                selectedIndex = null // Сбрасываем выбор
                            } else {
                                selectedIndex = gridIndex
                            }
                        }
                ) {
                    Image(
                        bitmap = tiles[tileIndex],
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
        if (isWin) {
            Text(
                "УРА! ПАЗЛ СОБРАН",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF2E7D32)
            )
        }
    }
}