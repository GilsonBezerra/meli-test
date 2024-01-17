package com.mercadolivre.melitest.presentation.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.mercadolivre.melitest.model.Product
import com.mercadolivre.melitest.presentation.viewmodel.ProductDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailScreen(
    viewModel: ProductDetailViewModel = koinViewModel(),
    product: Product,
) {
    Text(text = product.title)
}