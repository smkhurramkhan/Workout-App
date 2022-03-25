package com.example.workoutscardio.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutscardio.databinding.ItemTagsBinding
import com.example.workoutscardio.ui.main.viewholders.TagsViewHolder
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class AdapterTags(private val tagsList: MutableList<String>) :
    RecyclerView.Adapter<TagsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        return TagsViewHolder(
            ItemTagsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.binding.tagName.text = tagsList[position]
    }

    override fun getItemCount(): Int {
        return tagsList.size
    }



}