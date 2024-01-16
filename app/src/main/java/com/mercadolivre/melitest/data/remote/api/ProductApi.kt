package com.mercadolivre.melitest.data.remote.api

import com.mercadolivre.melitest.data.remote.model.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {
    @GET("sites/MLA/search")
    fun searchProducts(@Query("q") query: String): Call<ProductResponse>
}
