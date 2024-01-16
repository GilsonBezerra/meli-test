package com.mercadolivre.melitest.data.repository

import com.mercadolivre.melitest.data.remote.model.ProductResponse
import retrofit2.Call

interface ProductRepository {

//    suspend fun listProductByParameters(product: String): Call<ProductResponse>

    suspend fun listProductByParameters(product: String): Call<ProductResponse>
}
