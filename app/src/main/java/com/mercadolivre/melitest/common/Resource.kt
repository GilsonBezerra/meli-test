package com.mercadolivre.melitest.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import java.lang.Exception

sealed class Resource<out R> {
    data class Success<out R>(val result: R) : Resource<R>(), Flow<Resource<Any>> {
        override suspend fun collect(collector: FlowCollector<Resource<Any>>) {
            TODO("Not yet implemented")
        }
    }

    data class Failure(val exception: Exception) : Resource<Nothing>()

    object Loading : Resource<Nothing>(), Flow<Resource<Any>> {
        override suspend fun collect(collector: FlowCollector<Resource<Any>>) {
            TODO("Not yet implemented")
        }
    }
}
