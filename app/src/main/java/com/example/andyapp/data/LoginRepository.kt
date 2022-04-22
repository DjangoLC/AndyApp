package com.example.andyapp.data

import com.example.andyapp.data.models.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class LoginRepository {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    companion object {
        const val USERS = "users"

        //REGISTER
        const val NAME = "name"
        const val USER_NAME = "username"
        const val ADDRESS = "address"
        const val COUNTRY = "country"
        const val PHONE = "phone"
        const val EMAIL = "email"
        const val PASSWORD = "password"

    }

    suspend fun register(
        name: String,
        username: String,
        address: String,
        country: String,
        phone: String,
        email: String,
        password: String
    ): Register = suspendCancellableCoroutine { continuation ->
        val userMap = mutableMapOf<String, Any>()
        userMap[NAME] = name
        userMap[USER_NAME] = username
        userMap[ADDRESS] = address
        userMap[COUNTRY] = country
        userMap[PHONE] = phone
        userMap[EMAIL] = email
        userMap[PASSWORD] = password

        db.collection(USERS)
            .add(userMap)
            .addOnCompleteListener {
                continuation.resume(Register.REGISTER_SUCCESS)
            }
            .addOnFailureListener {
                continuation.resume(Register.REGISTER_ERROR)
            }

    }

    suspend fun verifyUniqueUser(username: String, pass: String): Register {
        return if (login(username, pass)) {
            Register.ALREADY_EXIST
        } else {
            Register.AVAILABLE
        }
    }


    suspend fun login(username: String, pass: String): Boolean =
        suspendCancellableCoroutine { continuation ->
            db.collection(USERS)
                .get()
                .addOnCompleteListener { task ->
                    val users = task.result.toObjects(User::class.java)
                    users.find { it.username == username && it.password == pass }?.let {
                        continuation.resume(true)
                    } ?: kotlin.run {
                        continuation.resume(false)
                    }
                }
                .addOnFailureListener {
                    continuation.resume(false)
                }
        }
}