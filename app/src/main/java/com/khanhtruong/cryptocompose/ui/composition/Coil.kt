package com.khanhtruong.cryptocompose.ui.composition

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable

@Composable
fun rememberImagePainter(
    data: Any?,
    @DrawableRes placeholder: Int? = null,
    crossFade: Boolean = true,
) = coil.compose.rememberImagePainter(
    data = data,
    builder = {
        crossfade(crossFade)
        placeholder?.let {
            this.placeholder(placeholder)
            this.error(placeholder)
            this.fallback(placeholder)
        }
    }
)