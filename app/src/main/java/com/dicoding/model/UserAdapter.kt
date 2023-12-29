package com.dicoding.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.data.DataItem
import com.dicoding.suitmediaapp.R
import com.dicoding.suitmediaapp.databinding.ItemUserBinding

class UserAdapter(private val onclick: (username: String) -> Unit): PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding, onclick)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }

    class MyViewHolder(private val binding: ItemUserBinding, onclick: (username: String) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val username = binding.tvName.text.toString()
                onclick.invoke(username)
            }
        }
        fun bind(data: DataItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(data.avatar)
                    .circleCrop()
                    .into(userAvatar)
                tvName.text = itemView.context.getString(R.string.username, data.firstName, data.lastName)
                tvEmail.text = data.email
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}