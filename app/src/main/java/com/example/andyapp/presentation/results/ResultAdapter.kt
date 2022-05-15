package com.example.andyapp.presentation.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.andyapp.data.models.Score
import com.example.andyapp.databinding.ResultsRowBinding
import kotlin.properties.Delegates

class ResultAdapter : RecyclerView.Adapter<ResultViewHolder>() {

    var items: List<Score> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder.fromParent(parent)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ResultViewHolder(private val binding: ResultsRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(score: Score) {
        binding.tvName.text = score.userName
        binding.tvScore.text = "Score: ${score.score}"
    }

    companion object {
        fun fromParent(parent: ViewGroup): ResultViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ResultViewHolder(ResultsRowBinding.inflate(inflater, parent, false))
        }
    }
}