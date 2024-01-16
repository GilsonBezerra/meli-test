package com.mercadolivre.melitest.presentation.view.states

import com.mercadolivre.melitest.model.Product

data class ProductUIState(
    val isLoading: Boolean = false,
    val product: List<Product> = emptyList(),
    val error: String = ""
)
