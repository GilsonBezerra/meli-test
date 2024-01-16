package com.mercadolivre.melitest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Struct(
    @Expose
    @SerializedName("number")
    val number: Int? = 0,
    @Expose
    @SerializedName("unit")
    val unit: String? = null,
)
