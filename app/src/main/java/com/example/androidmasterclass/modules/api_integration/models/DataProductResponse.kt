package com.example.androidmasterclass.modules.api_integration.models

data class DataProductResponse(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)