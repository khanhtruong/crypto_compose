package com.khanhtruong.cryptocompose.ui.composition

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.Dp
import com.khanhtruong.cryptocompose.ui.theme.GradientColorEnd
import com.khanhtruong.cryptocompose.ui.theme.GradientColorStart
import com.khanhtruong.cryptocompose.ui.theme.defaultCardColors
import com.khanhtruong.cryptocompose.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> GradientCard(
    data: T,
    radius: Dp = MaterialTheme.spacing.small,
    onCardClick: ((T) -> Unit)?,
    content: @Composable (T) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                PaddingValues(
                    bottom = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.extraMedium,
                    end = MaterialTheme.spacing.extraMedium
                )
            ),
        colors = defaultCardColors(),
        shape = RoundedCornerShape(radius),
        onClick = {
            onCardClick?.invoke(data)
        }
    ) {
        // Apply gradient colors
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            GradientColorStart,
                            GradientColorEnd
                        ),
                        tileMode = TileMode.Mirror
                    )
                ),
        ) {
            content(data)
        }
    }
}