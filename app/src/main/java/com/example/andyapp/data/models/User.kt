package com.example.andyapp.data.models

import java.io.Serializable

data class User(
    val name: String,
    val username: String,
    val address: String,
    val country: String,
    val phone: String,
    val email: String,
    val password: String
) : Serializable {
    constructor() : this(
        name = "",
        username = "",
        address = "",
        country = "",
        phone = "",
        email = "",
        password = ""
    )
}