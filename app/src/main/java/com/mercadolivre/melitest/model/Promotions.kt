package com.mercadolivre.melitest.model

import com.google.gson.annotations.SerializedName

data class Promotions(
    @SerializedName("promotions")
    val promotions: List<Promotion>? = null
)
