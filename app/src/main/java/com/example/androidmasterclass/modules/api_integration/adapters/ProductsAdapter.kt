package com.example.androidmasterclass.modules.api_integration.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmasterclass.databinding.ItemMainMenuBinding
import com.example.androidmasterclass.databinding.ItemProductBinding
import com.example.androidmasterclass.main_menu.model.DataMainMenu
import com.example.androidmasterclass.modules.api_integration.models.Product

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductsViewHolder,
        position: Int
    ) {
        val currentData = differ.currentList[position]
        holder.bind(currentData)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    val diffUtil = object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
          return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: Product,
            newItem: Product
        ): Boolean {
            return oldItem.id==newItem.id
        }
    }

    val differ = AsyncListDiffer(this,diffUtil)
    var onClick : ((String)-> Unit)? = null

    inner class ProductsViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
           binding.tvProductTitle.text = product.title
        }
    }
}