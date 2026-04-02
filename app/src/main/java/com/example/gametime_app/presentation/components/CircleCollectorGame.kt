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
import com.example.gametime_app.presentation.viewModel.CircleGameViewModel
import kotlin.random.Random

@Composable
fun CircleCollectorGame(modifier: Modifier = Modifier,
                        viewModel: CircleGameViewModel) {
    val mainColor = Color(0xFFFF4081) // Розовый цвет с фото

    Box(modifier = modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .onSizeChanged { size ->
                    viewModel.onSizeChanged(size)
                }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            // Ищем индекс круга. Если не нашли, вернет -1
                            val index = viewModel.circles.indexOfFirst {
                                !it.isFixed && (offset - it.current).getDistance() < (it.radius + 40f)
                            }
                            // Сохраняем только валидный индекс (не -1)
                            viewModel.draggedIndex = if (index != -1) index else null
                        },
                        onDragEnd = { viewModel.draggedIndex = null },
                        onDragCancel = { viewModel.draggedIndex = null },
                        onDrag = { change, dragAmount ->
                            change.consume() // Подтверждаем обработку жеста

                            viewModel.draggedIndex?.let { index ->
                                viewModel.handleDrag(index, dragAmount)
                            }
                        }
                    )
                }
        ) {
            // 1. Рисуем центральный круг (толстая линия)
            drawCircle(
                color = mainColor,
                radius = viewModel.mainRadius,
                center = center,
                style = Stroke(width = 15f)
            )

            // 2. Рисуем все остальные круги
            viewModel.circles.forEach { circle ->
                drawCircle(
                    color = mainColor,
                    radius = circle.radius,
                    center = circle.current,
                    style = Stroke(width = circle.width),
                )
            }
        }

        if (viewModel.isWin) {
            Text(
                "УРА! ПАЗЛ СОБРАН",
                style = MaterialTheme.typography.headlineLarge,
                color = Color(0xFF2E7D32)
            )
        }
    }
}