package com.eleish.geideatask.app.features.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eleish.geideatask.databinding.ItemUserBinding
import com.eleish.geideatask.entities.User

class UsersAdapter(private val onItemClicked: (User) -> Unit) :
    ListAdapter<User, UsersAdapter.ViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            ViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = getItem(position)

        with(holder.binding) {
            idTV.text = user.id.toString()
            nameTV.text = user.fullName

            root.setOnClickListener {
                onItemClicked.invoke(user)
            }
        }
    }

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}