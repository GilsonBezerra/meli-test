package com.mercadolivre.melitest.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.mercadolivre.melitest.model.Product

@Composable
fun ProductListItem(product: Product) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
    ) {
        val thumb = product.thumbnail
        Row {
            CommonImage(thumb)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
            ) {
                Text(text = product.title, style = TextStyle.Default)
                Text(text = "View", style = TextStyle.Default)
            }
        }
    }
}
