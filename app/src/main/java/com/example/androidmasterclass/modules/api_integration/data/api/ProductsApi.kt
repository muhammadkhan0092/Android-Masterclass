package com.example.androidmasterclass.modules.api_integration.data.api

import com.example.androidmasterclass.modules.api_integration.data.models.DataProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApi {
    @GET("products/")
    suspend fun getProducts(
        @Query("skip")
        skip : Int,
        @Query("limit")
        limit : Int
    ) : Response<DataProductResponse>
}