package com.mercadolivre.melitest.data.remote.network

import com.mercadolivre.melitest.data.remote.network.commons.ApiConstants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            val http = OkHttpClient.Builder()
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl(ApiConstants.BASE_URL)
                    .client(http.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE
        }

        fun <S> createService(service: Class<S>): S {
            return getRetrofitInstance().create(service)
        }
    }
}