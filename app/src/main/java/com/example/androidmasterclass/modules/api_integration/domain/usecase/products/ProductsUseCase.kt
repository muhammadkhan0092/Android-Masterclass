package com.example.androidmasterclass.modules.api_integration.domain.usecase.products

import com.example.androidmasterclass.utils.Resource
import com.example.androidmasterclass.modules.api_integration.data.repository.ProductRepository
import com.example.androidmasterclass.modules.api_integration.domain.models.Product
import javax.inject.Inject

class ProductsUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun getProducts(skip: Int, limit: Int): Resource<List<Product>> {
        return when (val result = productRepository.getProducts(skip, limit)) {
            is Resource.Success -> {
                Resource.Success(result.data!!.products)
            }
            is Resource.Error -> {
                Resource.Error(result.message ?: "Unknown Error")
            }
            else -> Resource.Unspecified()
        }
    }
}
