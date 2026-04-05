package com.example.gametime_app.presentation.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.toOffset
import androidx.lifecycle.ViewModel
import com.example.gametime_app.domain.CircleItem
import kotlin.random.Random

class CircleGameViewModel : ViewModel() {
    var circles by mutableStateOf<List<CircleItem>>(emptyList())
    var draggedIndex by mutableStateOf<Int?>(null)
    var isWin by mutableStateOf(false)

    val mainRadius = 150f
    private var canvasSize = IntSize.Zero

    fun onSizeChanged(size: IntSize) {
        if (canvasSize == IntSize.Zero && size != IntSize.Zero) {
            canvasSize = size
            initGame()
        }
    }

    fun initGame() {
        if (canvasSize == IntSize.Zero) return

        isWin = false
        circles = (1..8).map { i ->
            val radius = mainRadius - (i * 15f) // Пропорциональное уменьшение радиуса
            val strokeWidth = if (i <= 2) 6f else 3f

            CircleItem(
                id = i,
                current = randomOffset(),
                radius = radius,
                width = strokeWidth
            )
        }
    }

    private fun randomOffset(): Offset {
        var offset: Offset
        do {
            offset = Offset(
                x = Random.nextFloat() * (canvasSize.width - 200f) + 100f,
                y = Random.nextFloat() * (canvasSize.height - 200f) + 100f
            )
            // Проверяем, чтобы круг не появился внутри или слишком близко к центру
            val distanceToCenter = (offset - canvasSize.center.toOffset()).getDistance()
        } while (distanceToCenter < mainRadius + 110f)
        return offset
    }

    fun handleDrag(index: Int, dragAmount: Offset) {
        val circle = circles[index]
        val nextPos = circle.current + dragAmount

        // Используем размер Canvas, который доступен через size.toCenterOffset()
        // или заранее сохраненную переменную центра.
        val canvasCenter = Offset(
            canvasSize.width / 2f,
            canvasSize.height / 2f)
        val distanceToCenter = (nextPos - canvasCenter).getDistance()

        // Условие захвата: если центр круга подошел близко к центру экрана
        val isCaptured = distanceToCenter < 50f

        val newList = circles.toMutableList()
        if (isCaptured) {
            newList[index] = circle.copy(
                current = canvasCenter,
                isFixed = true
            )
            draggedIndex = null // Сбрасываем индекс, так как круг зафиксирован
        } else {
            newList[index] = circle.copy(current = nextPos)
        }
        circles = newList
        isWin = circles.all { it.isFixed }
    }
}