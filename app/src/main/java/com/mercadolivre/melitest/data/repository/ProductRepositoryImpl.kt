package com.mercadolivre.melitest.data.repository

import com.mercadolivre.melitest.data.remote.api.ProductApi
import com.mercadolivre.melitest.data.remote.model.ProductResponse
import retrofit2.Call

class ProductRepositoryImpl(
    private val api: ProductApi,
) : ProductRepository {

    override suspend fun listProductByParameters(product: String): Call<ProductResponse> {
        return api.searchProducts(product)
    }
}
