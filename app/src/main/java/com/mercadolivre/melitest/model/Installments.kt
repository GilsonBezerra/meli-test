package com.mercadolivre.melitest.model

import com.google.gson.annotations.SerializedName

data class Installments(
    val quantity: Int,
    val amount: Double,
    val rate: Double,
    @SerializedName("currency_id") val currencyId: String,
)
