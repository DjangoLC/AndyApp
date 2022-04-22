package com.example.andyapp.data

import com.example.andyapp.data.models.Quiz
import com.example.andyapp.data.models.Topic
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class QuestionsRepository {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    companion object {
        const val TOPICS_COLLECTION = "topics"
    }

    suspend fun createQuestion(title: String, quiz: List<Quiz>): Boolean =
        suspendCancellableCoroutine { continuation ->
            val topic = Topic(title, quiz)
            db.collection(TOPICS_COLLECTION)
                .add(topic)
                .addOnSuccessListener {
                    continuation.resume(true)
                }
                .addOnFailureListener {
                    continuation.resume(false)
                }
        }

    suspend fun getTopics() : List<Topic> = suspendCancellableCoroutine { continuation ->
        db.collection(TOPICS_COLLECTION)
            .get()
            .addOnSuccessListener { task ->
                val topics = task.toObjects(Topic::class.java)
                continuation.resume(topics)
            }
            .addOnFailureListener {
                continuation.resume(emptyList())
            }
    }

}