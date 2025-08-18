package com.example.androidmasterclass.hilt.modules

import com.example.androidmasterclass.modules.api_integration.data.api.ProductsApi
import com.example.androidmasterclass.modules.api_integration.data.constants.Constants.PRODUCT_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesProductRetrofitInstance() : Retrofit{
        return Retrofit.Builder().baseUrl(PRODUCT_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun providesProductsApi() : ProductsApi{
        return providesProductRetrofitInstance().create(ProductsApi::class.java)
    }
}