package com.example.andyapp.presentation.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andyapp.data.models.Topic
import com.example.andyapp.databinding.HeaderBinding
import com.example.andyapp.databinding.TopicRowBinding
import kotlin.properties.Delegates

typealias onClickTopic = (Topic) -> Unit

@SuppressLint("NotifyDataSetChanged")
class TopicsAdapter(private val callback: onClickTopic) :
    RecyclerView.Adapter<TopicsViewHolder>() {

    var topics: List<Topic> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsViewHolder {
       return TopicsViewHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: TopicsViewHolder, position: Int) {
        val item = topics[position]
        holder.bind(item, callback)
    }

    override fun getItemCount(): Int {
        return topics.size
    }

}

class TopicsViewHolder(val view: TopicRowBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: Topic, onClickTopic: onClickTopic) {
        view.tvTopicTitle.text = item.title
        view.root.setOnClickListener { onClickTopic.invoke(item) }
    }

    companion object {
        fun createHolder(parent: ViewGroup): TopicsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return TopicsViewHolder(TopicRowBinding.inflate(inflater, parent, false))
        }
    }

}