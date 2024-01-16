package com.mercadolivre.melitest.di // ktlint-disable filename

import android.os.Build
import androidx.annotation.RequiresExtension
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mercadolivre.melitest.data.remote.api.ProductApi
import com.mercadolivre.melitest.data.remote.network.commons.ApiConstants
import com.mercadolivre.melitest.data.repository.ProductRepository
import com.mercadolivre.melitest.data.repository.ProductRepositoryImpl
import com.mercadolivre.melitest.domain.GetProductUseCase
import com.mercadolivre.melitest.presentation.viewmodel.HomeScreenViewModel
import com.mercadolivre.melitest.presentation.viewmodel.ProductDetailViewModel
import com.mercadolivre.melitest.presentation.viewmodel.ProductResultViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
val appModule = module {
    viewModel { HomeScreenViewModel() }
    factory { GetProductUseCase(get()) }
    factory<ProductRepository> { ProductRepositoryImpl(get()) }

    viewModel { ProductResultViewModel(get()) }
    viewModel { ProductDetailViewModel() }
    factory { provideRetrofit() }
    factory { provideProductApi(get()) }
}

fun provideRetrofit(): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(OkHttpClient.Builder().build())
        .build()
}

fun provideProductApi(retrofit: Retrofit): ProductApi =
    retrofit.create(ProductApi::class.java)

