package com.example.gametime_app.presentation.viewModel

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel

class ImageGameViewModel : ViewModel() {
    var tiles by mutableStateOf<List<ImageBitmap>>( emptyList())
    var board by mutableStateOf<List<Int>>(emptyList())
    var selectedIndex by mutableStateOf<Int?>(null)
    var isWin by mutableStateOf(false)

    fun initGame(bitmap: Bitmap) {
        val size = minOf(bitmap.width, bitmap.height) / 3
        val tilesList = mutableListOf<ImageBitmap>()

        for (i in 0..2) {
            for (j in 0..2) {
                val x = j * size
                val y = i * size
                tilesList.add(Bitmap.createBitmap(bitmap, x, y, size, size).asImageBitmap())
            }
        }

        tiles = tilesList
        board = (0..8).toList().shuffled()
        isWin = false
    }

    fun onTileClick(gridIndex: Int) {
        val currentSelected = selectedIndex

        if (currentSelected != null) {
            val newList = board.toMutableList()
            val temp = newList[currentSelected]
            newList[currentSelected] = newList[gridIndex]
            newList[gridIndex] = temp
            board = newList

            selectedIndex = null
            isWin = newList == (0..8).toList()
        } else {
            selectedIndex = gridIndex
        }
    }

    fun shuffleBoard() {
        board = (0..8).toList().shuffled()
        selectedIndex = null
        isWin = false
    }
}