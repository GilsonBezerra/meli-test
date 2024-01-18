package com.mercadolivre.melitest.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mercadolivre.melitest.R

@Composable
fun CommonProductImageDetailScreen(
    imageUrl: String?,
) {
    Box(
        modifier = Modifier
            .padding(top = 16.dp),
    ) {
        ImageCard(
            imageUrl = imageUrl,
            modifier = Modifier
                .height(300.dp)
                .width(200.dp),
        )
    }
}

@Composable
fun CommonProductImageList(
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
    Card(
        modifier = modifier,
    ) {
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
) {
    AsyncImage(
        model = data,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize(),
        onError = {
            Log.e("Image Loading", "Failed to load image: $data", it.result.throwable)
        },
    )
}
