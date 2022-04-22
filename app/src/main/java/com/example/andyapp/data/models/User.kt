package com.example.andyapp.data.models

data class User(
    val name: String,
    val username: String,
    val address: String,
    val country: String,
    val phone: String,
    val email: String,
    val password: String
) {
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