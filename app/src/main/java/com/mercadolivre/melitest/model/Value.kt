package com.mercadolivre.melitest.model

data class Value(
    val id: String,
    val name: String,
    val struct: ValueStruct?,
    val source: Long?
)
