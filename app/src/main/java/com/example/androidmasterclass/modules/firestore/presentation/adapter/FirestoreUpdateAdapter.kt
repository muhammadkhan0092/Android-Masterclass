package com.example.androidmasterclass.modules.firestore.presentation.adapter

import android.text.Editable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmasterclass.databinding.ItemFirestoreUpdateBinding
import com.example.androidmasterclass.databinding.ItemMainMenuBinding
import com.example.androidmasterclass.main_menu.presentation.model.DataMainMenu
import com.example.androidmasterclass.modules.firestore.domain.models.DataUser

class FirestoreUpdateAdapter : RecyclerView.Adapter<FirestoreUpdateAdapter.FirestoreUpdateViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FirestoreUpdateViewHolder {
        val binding = ItemFirestoreUpdateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FirestoreUpdateViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FirestoreUpdateViewHolder,
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

    inner class FirestoreUpdateViewHolder(val binding: ItemFirestoreUpdateBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataUser) {
            binding.apply {
                tvEmail.text = user.email
                btnUpdate.setOnClickListener {
                    val newName = etName.text.toString()
                    if(newName==user.name){
                        Toast.makeText(binding.root.context, "Update Name", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        onClick?.invoke(user.copy(name = newName))
                    }
                }
            }
        }
    }
}