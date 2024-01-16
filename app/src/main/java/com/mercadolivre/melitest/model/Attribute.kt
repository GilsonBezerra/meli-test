package com.mercadolivre.melitest.model

import com.google.gson.annotations.SerializedName

data class Attribute(
    val id: String,
    val name: String,
    @SerializedName("value_id")
    val valueId: String,
    @SerializedName("value_name")
    val valueName: String,
    @SerializedName("attribute_group_id")
    val attributeGroupId: String,
    @SerializedName("attribute_group_name")
    val attributeGroupName: String,
    @SerializedName("value_struct")
    val valueStruct: ValueStruct?,
    val values: List<Value>,
    @SerializedName("value_type")
    val valueType: String,
)
