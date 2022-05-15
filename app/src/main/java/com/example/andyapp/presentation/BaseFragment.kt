package com.example.andyapp.presentation

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

open class BaseFragment(@LayoutRes val res: Int) : Fragment(res) {

    val factory = ViewModelFactory()

}