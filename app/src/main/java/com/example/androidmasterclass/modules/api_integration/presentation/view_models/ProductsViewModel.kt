package com.example.androidmasterclass.modules.api_integration.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidmasterclass.utils.Resource
import com.example.androidmasterclass.modules.api_integration.domain.models.Product
import com.example.androidmasterclass.modules.api_integration.domain.usecase.products.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productsUseCase: ProductsUseCase) : ViewModel() {
    var skip : Int? = null
    private val _products = MutableStateFlow<Resource<List<Product>>>(Resource.Unspecified())
    val products : StateFlow<Resource<List<Product>>> = _products.asStateFlow()

    init {
        getProducts()
    }
    fun getProducts() {
        viewModelScope.launch {
            _products.emit(Resource.Loading())
            if(skip==null){
                skip = 0
            }
            else
            {
                skip = skip!! +10
            }
            _products.emit(productsUseCase.getProducts(skip!!,10))
        }
    }
}