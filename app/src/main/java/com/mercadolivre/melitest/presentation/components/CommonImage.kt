package com.mercadolivre.melitest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter

@Composable
fun CommonImage(
    data: String?,
    modifier: Modifier = Modifier.fillMaxSize(),
    contentScale: ContentScale = ContentScale.Crop
) {
    val painter = rememberImagePainter(data = data)
    Image(
        painter = painter,
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier,
    ).apply {
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
    }
}