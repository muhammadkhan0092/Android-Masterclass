package com.example.androidmasterclass.main_menu.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmasterclass.databinding.ItemMainMenuBinding
import com.example.androidmasterclass.main_menu.model.DataMainMenu

class MainMenuAdapter : RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainMenuViewHolder {
        val binding = ItemMainMenuBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainMenuViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MainMenuViewHolder,
        position: Int
    ) {
        val currentData = differ.currentList[position]
        holder.bind(currentData)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    val diffUtil = object : DiffUtil.ItemCallback<DataMainMenu>() {
        override fun areItemsTheSame(
            oldItem: DataMainMenu,
            newItem: DataMainMenu
        ): Boolean {
          return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: DataMainMenu,
            newItem: DataMainMenu
        ): Boolean {
            return oldItem.btnName==newItem.btnName
        }
    }

    val differ = AsyncListDiffer(this,diffUtil)
    var onClick : ((String)-> Unit)? = null

    inner class MainMenuViewHolder(val binding: ItemMainMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: DataMainMenu) {
            binding.btnMenuItem.text = menu.btnName
            binding.btnMenuItem.setOnClickListener {
                onClick?.invoke(menu.btnName)
            }
        }
    }
}