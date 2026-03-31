package com.example.gametime_app.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp

@Composable
fun PuzzleGrid(
    modifier: Modifier = Modifier,
    tiles: List<ImageBitmap>,
    board: List<Int>,
    selectedIndex: Int?,
    onTileClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(Color(0xFFF22E63))
            .padding(vertical = 20.dp, horizontal = 10.dp)
    ) {
        itemsIndexed(board) { gridIndex, tileIndex ->
            PuzzleTile(
                bitmap = tiles[tileIndex],
                isSelected = selectedIndex == gridIndex,
                onClick = { onTileClick(gridIndex) }
            )
        }
    }
}