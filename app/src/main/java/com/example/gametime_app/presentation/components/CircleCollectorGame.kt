package com.example.gametime_app.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toOffset
import com.example.gametime_app.domain.CircleItem
import kotlin.random.Random

@Composable
fun CircleCollectorGame(modifier: Modifier = Modifier) {
    val mainColor = Color(0xFFFF4081) // Розовый цвет с фото
    val mainRadius = 150f             // Радиус центрального круга

    fun randOffset(size: IntSize): Offset {
        var offset: Offset
        do {
            offset = Offset(
                x = Random.nextFloat() * (size.width - 200f) + 100f,
                y = Random.nextFloat() * (size.height - 200f) + 100f
            )
            // Проверяем, чтобы круг не появился внутри или слишком близко к центру
            val distanceToCenter = (offset - size.center.toOffset()).getDistance()
        } while (distanceToCenter < mainRadius + 110f)
        return offset
    }

    var circles by remember { mutableStateOf((listOf<CircleItem>()))}

    // Индекс перетаскиваемого круга (может быть null)
    var draggedIndex by remember { mutableStateOf<Int?>(null) }

    val isWin = circles.all { it.isFixed }

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { size ->
                    if (circles.isEmpty()) { // Генерируем один раз при получении размера
                        circles = (1..8).map { i ->
                            val radius = mainRadius - (i * 15f) // Пропорциональное уменьшение радиуса
                            val strokeWidth = if (i <= 2) 6f else 3f

                            CircleItem(
                                id = i,
                                current = randOffset(size),
                                radius = radius,
                                width = strokeWidth
                            )
                        }
                    }
                }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            // Ищем индекс круга. Если не нашли, вернет -1
                            val index = circles.indexOfFirst {
                                !it.isFixed && (offset - it.current).getDistance() < (it.radius + 40f)
                            }
                            // Сохраняем только валидный индекс (не -1)
                            draggedIndex = if (index != -1) index else null
                        },
                        onDragEnd = { draggedIndex = null },
                        onDragCancel = { draggedIndex = null },
                        onDrag = { change, dragAmount ->
                            change.consume() // Подтверждаем обработку жеста

                            draggedIndex?.let { index ->
                                val circle = circles[index]
                                val nextPos = circle.current + dragAmount

                                // Используем размер Canvas, который доступен через size.toCenterOffset()
                                // или заранее сохраненную переменную центра.
                                val canvasCenter = Offset(size.width / 2f, size.height / 2f)
                                val distanceToCenter = (nextPos - canvasCenter).getDistance()

                                // Условие захвата: если центр круга подошел близко к центру экрана
                                val isCaptured = distanceToCenter < 50f

                                val newList = circles.toMutableList()
                                if (isCaptured) {
                                    newList[index] = circle.copy(
                                        current = canvasCenter,
                                        isFixed = true
                                    )
                                    draggedIndex =
                                        null // Сбрасываем индекс, так как круг зафиксирован
                                } else {
                                    newList[index] = circle.copy(current = nextPos)
                                }
                                circles = newList
                            }
                        }
                    )
                }
        ) {
            // 1. Рисуем центральный круг (толстая линия)
            drawCircle(
                color = mainColor,
                radius = mainRadius,
                center = center,
                style = Stroke(width = 15f)
            )

            // 2. Рисуем все остальные круги
            circles.forEach { circle ->
                drawCircle(
                    color = mainColor,
                    radius = circle.radius,
                    center = circle.current,
                    style = Stroke(width = circle.width),
                )
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