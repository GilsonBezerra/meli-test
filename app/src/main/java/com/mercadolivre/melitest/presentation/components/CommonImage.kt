package com.mercadolivre.melitest.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import com.mercadolivre.melitest.R

@Composable
fun CommonProductImage(
    imageUrl: String?,
) {
    Box(
        modifier = Modifier
            .padding(top = 16.dp),
    ) {
        ImageCard(
            imageUrl = imageUrl,
            modifier = Modifier
                .height(150.dp)
                .width(100.dp),
        )
    }
}

@Composable
fun ImageCard(
    imageUrl: String?,
    modifier: Modifier = Modifier,
) {
    Card(shape = RectangleShape, modifier = modifier) {
        if (!imageUrl.isNullOrBlank()) {
            CommonImage(data = imageUrl)
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_broken_image),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun CommonImage(
    data: String,
    modifier: Modifier = Modifier.fillMaxWidth(),
    contentScale: ContentScale = ContentScale.FillWidth,
) {
    val painter = rememberImagePainter(data = data)

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = contentScale,
        modifier = Modifier.fillMaxWidth().aspectRatio(3.21428f),
    ).apply {
        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator()
        }
    }
}
