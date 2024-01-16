package com.mercadolivre.melitest.data.remote.model

import com.google.gson.annotations.SerializedName
import com.mercadolivre.melitest.model.Product

data class ProductResponse(
    @SerializedName("results")
    val results: List<Product>
)

fun ProductResponse.toProduct(): ProductResponse {
    return ProductResponse(
        results = results,
    )
}
