package com.khanhtruong.cryptocompose.ui.composition

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest

//@Composable
//fun rememberImagePainter(
//    data: Any?,
//    @DrawableRes placeholder: Int? = null,
//    crossFade: Boolean = true,
//) = coil.compose.rememberImagePainter(
//    data = data,
//    builder = {
//        crossfade(crossFade)
//        placeholder?.let {
//            this.placeholder(placeholder)
//            this.error(placeholder)
//            this.fallback(placeholder)
//        }
//    }
//)

@Composable
fun rememberAsyncImagePainter(
    data: Any?,
    @DrawableRes placeholder: Int? = null,
    crossFade: Boolean = true,
) = ImageRequest.Builder(LocalContext.current).data(data).apply {
    crossfade(crossFade)
    placeholder?.let {
        this.placeholder(placeholder)
        this.error(placeholder)
        this.fallback(placeholder)
    }
}.build()