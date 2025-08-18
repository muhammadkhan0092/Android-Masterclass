package com.example.androidmasterclass.modules.api_integration.data.models

import com.example.androidmasterclass.modules.api_integration.domain.models.Product

data class DataProductResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)