package com.example.testproject.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testproject.R
import com.example.testproject.data.model.UserModel
import com.example.testproject.databinding.ItemPostAdapterBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.PostViewHolder>() {

    var list: List<UserModel> = mutableListOf()

    fun setData(listTemp:List<UserModel>){
        this.list = listTemp
        notifyDataSetChanged()
    }

    class PostViewHolder(bindingTemp: ItemPostAdapterBinding) :
        RecyclerView.ViewHolder(bindingTemp.root) {

        val binding: ItemPostAdapterBinding = bindingTemp
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view: ItemPostAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_post_adapter, parent, false
        )
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.binding.userModel = list[position]
    }
}