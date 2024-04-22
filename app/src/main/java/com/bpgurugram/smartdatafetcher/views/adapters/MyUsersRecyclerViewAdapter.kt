package com.bpgurugram.smartdatafetcher.views.adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView


import com.bpgurugram.smartdatafetcher.databinding.FragmentUsersListBinding
import com.bpgurugram.smartdatafetcher.models.UserList
import com.bpgurugram.smartdatafetcher.utility.IUserClickItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyUsersRecyclerViewAdapter(
     val values: UserList,private var clickCallback : IUserClickItem
) : RecyclerView.Adapter<MyUsersRecyclerViewAdapter.ViewHolder>() {

    var isScrollComplete = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentUsersListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.userId.text = item.id.toString()
        holder.title.text = item.title.toString()
        holder.itemHolder.setOnClickListener {

            clickCallback.itemSelected(item)

        }

        if (position==values.size-1){// its a last item
            clickCallback.onScrollComplete(isScrollComplete)
        }


    }

    fun addMoreItems(newValues: UserList)
    {
        isScrollComplete = false

        this.values.addAll(newValues)

    }

    fun enableScroll()
    {
        isScrollComplete = true
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentUsersListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val userId: TextView by lazy{ binding.userUniqueId}
        val title: TextView by lazy { binding.content }
        val itemHolder by lazy {  binding.itemHolder }


        override fun toString(): String {
            return super.toString() + " '" + title.text + "'"
        }
    }

}