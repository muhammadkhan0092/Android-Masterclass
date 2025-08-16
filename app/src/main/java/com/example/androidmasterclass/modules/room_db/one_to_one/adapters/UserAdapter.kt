package com.example.androidmasterclass.modules.room_db.one_to_one.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmasterclass.databinding.ItemUserDetailBinding
import com.example.androidmasterclass.modules.room_db.one_to_one.models.DataUserWithAddress

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserViewHolder {
        val binding = ItemUserDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: UserViewHolder,
        position: Int
    ) {
        val currentUser = differ.currentList[position]
        holder.bind(currentUser)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    val diffUtil = object : DiffUtil.ItemCallback<DataUserWithAddress>() {
        override fun areItemsTheSame(
            oldItem: DataUserWithAddress,
            newItem: DataUserWithAddress
        ): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: DataUserWithAddress,
            newItem: DataUserWithAddress
        ): Boolean {
            return oldItem.user.userId==newItem.user.userId
        }

    }

    val differ = AsyncListDiffer(this,diffUtil)


    inner class UserViewHolder(val binding : ItemUserDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userWithAddress: DataUserWithAddress) {
            binding.apply {
                tvName.text = userWithAddress.user.userName
                tvEmail.text = userWithAddress.user.userEmail
                tvAddress.text = userWithAddress.address.city
            }
        }
    }
}