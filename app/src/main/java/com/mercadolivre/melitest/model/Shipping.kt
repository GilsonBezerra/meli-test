package com.mercadolivre.melitest.model

import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("store_pick_up") val storePickUp: Boolean,
    @SerializedName("free_shipping") val freeShipping: Boolean,
    @SerializedName("logistic_type") val logisticType: String,
    val mode: String,
    val tags: List<String>,
    val benefits: Any?,
    val promise: Any? = null,
)
