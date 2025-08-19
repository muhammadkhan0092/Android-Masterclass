package com.example.androidmasterclass.modules.firestore.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmasterclass.databinding.ItemFirestoreDisplayBinding
import com.example.androidmasterclass.modules.firestore.domain.models.DataUser

class FirestoreDisplayAdapter : RecyclerView.Adapter<FirestoreDisplayAdapter.FirestoreDisplayViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FirestoreDisplayViewHolder {
        val binding = ItemFirestoreDisplayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FirestoreDisplayViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FirestoreDisplayViewHolder,
        position: Int
    ) {
        val currentData = differ.currentList[position]
        holder.bind(currentData)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    val diffUtil = object : DiffUtil.ItemCallback<DataUser>() {
        override fun areItemsTheSame(
            oldItem: DataUser,
            newItem: DataUser
        ): Boolean {
          return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: DataUser,
            newItem: DataUser
        ): Boolean {
            return oldItem.email==newItem.email
        }
    }

    val differ = AsyncListDiffer(this,diffUtil)
    var onClick : ((DataUser)-> Unit)? = null

    inner class FirestoreDisplayViewHolder(val binding: ItemFirestoreDisplayBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataUser) {
            binding.ivDelete.setOnClickListener {
                onClick?.invoke(data)
            }
        }
    }
}