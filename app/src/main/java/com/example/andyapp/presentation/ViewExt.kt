package com.example.andyapp.presentation

import android.R
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.example.andyapp.data.models.Topic

fun View.isVisible(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}

fun AutoCompleteTextView.setupWithAdapter(entries: List<Topic>, callback: (String) -> Unit) {
    var selection = ""
    val spinnerAdapter = ArrayAdapter(
        this.context, R.layout.simple_dropdown_item_1line,
        entries
    )
    spinnerAdapter.toString()
    this.setAdapter(spinnerAdapter)

    this.setOnItemClickListener { parent, view, position, id ->
        val s = parent.getItemAtPosition(position) as Topic
        if (selection != s.title) {
            selection = s.title
            callback.invoke(s.id ?:"")
        }
    }
}