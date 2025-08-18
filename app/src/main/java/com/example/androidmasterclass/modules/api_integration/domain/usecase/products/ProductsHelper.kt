package com.example.androidmasterclass.modules.api_integration.domain.usecase.products

fun returnErrorString(code : Int): String {
    return when(code){
        400 ->  "Wrong Inputs"
        401 -> "Unauthorized"
        403 -> "Forbidded"
        404 -> "Resource Not Found"
        500 -> "Server Error"
        else -> "Error $code"
    }
}