package com.mercadolivre.melitest.domain

import com.mercadolivre.melitest.data.repository.ProductRepository
import com.mercadolivre.melitest.model.Product

class GetProductUseCase(
    private val repository: ProductRepository,
) {

    data class Params(
        val product: String,
    )

    suspend fun execute(params: Params): List<Product>? {
        return repository.listProductByParameters(params.product)
            .execute()
            .body()?.results
    }
}
