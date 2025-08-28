package com.example.androidmasterclass.modules.api_integration.presentation.views

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidmasterclass.utils.Resource
import com.example.androidmasterclass.utils.VerticalItemDecoration
import com.example.androidmasterclass.databinding.ProductsActivityBinding
import com.example.androidmasterclass.modules.api_integration.presentation.view_models.ProductsViewModel
import com.example.androidmasterclass.modules.api_integration.domain.models.Product
import com.example.androidmasterclass.modules.api_integration.presentation.adapters.ProductsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsActivity : AppCompatActivity() {
    private lateinit var _binding : ProductsActivityBinding
    private val binding get() = _binding
    private val viewModel by viewModels<ProductsViewModel>()
    private val productsAdapter by lazy { ProductsAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ProductsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onNextPageClicked()
        setupProductsRecyclerView()
        fetchDataFromApi()
    }

    private fun onNextPageClicked() {
        binding.btnNextPage.setOnClickListener {
            viewModel.getProducts()
        }
    }

    private fun setupProductsRecyclerView() {
        binding.productsRv.apply {
            adapter = productsAdapter
            layoutManager =
                LinearLayoutManager(this@ProductsActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(VerticalItemDecoration(20))
        }
    }
    private fun fetchDataFromApi() {
        lifecycleScope.launch {
            viewModel.products.collectLatest {
                when(it){
                    is Resource.Error<*> -> Toast.makeText(this@ProductsActivity,it.message!!, Toast.LENGTH_SHORT).show()
                    is Resource.Loading<*> -> showLoadingState()
                    is Resource.Success<*> -> hideLoadingStateAndSetData(it.data!!)
                    is Resource.Unspecified<*> -> {}
                }
            }
        }
    }
    private fun showLoadingState(){
        binding.apply {
            progressBar.visibility = View.VISIBLE
            btnNextPage.visibility = View.INVISIBLE
        }
    }
    private fun hideLoadingStateAndSetData(data : List<Product>){
        binding.apply {
            progressBar.visibility = View.INVISIBLE
            btnNextPage.visibility = View.VISIBLE
        }
        val adapterData = productsAdapter.differ.currentList.toMutableList()
        adapterData.addAll(data)
        Log.d("khan","data is ${data}")
        productsAdapter.differ.submitList(adapterData)
    }
}