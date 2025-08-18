package com.example.androidmasterclass.modules.api_integration.data.repository

import com.example.androidlauncher.utils.Resource
import com.example.androidmasterclass.modules.api_integration.domain.usecase.products.returnErrorString
import com.example.androidmasterclass.modules.api_integration.data.api.ProductsApi
import com.example.androidmasterclass.modules.api_integration.data.models.DataProductResponse
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productsApi: ProductsApi) {

    suspend fun getProducts(skip: Int, limit: Int): Resource<DataProductResponse> {
        return try {
            val response = productsApi.getProducts(skip, limit)

            if (response.isSuccessful) {
                response.body()?.let {
                    Resource.Success(it)
                } ?: Resource.Error("No Data Available")
            } else {
                Resource.Error(returnErrorString(response.code()))
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown Error")
        }
    }
}