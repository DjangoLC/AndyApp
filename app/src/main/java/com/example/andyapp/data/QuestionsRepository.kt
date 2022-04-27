package com.example.andyapp.data

import com.example.andyapp.data.models.Quiz
import com.example.andyapp.data.models.Topic
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
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

    suspend fun getTopics(): List<Topic> = suspendCancellableCoroutine { continuation ->
        db.collection(TOPICS_COLLECTION)
            .get()
            .addOnSuccessListener { task ->
                val topics = mutableListOf<Topic>()
                task.documents.forEach {
                    val topic = it.toObject<Topic>()
                    topic?.id = it.id
                    topic?.let { topics.add(topic) }
                }
                continuation.resume(topics)
            }
            .addOnFailureListener {
                continuation.resume(emptyList())
            }
    }

    suspend fun createQuestionByTopic(documentId: String, questions: List<Quiz>): Boolean =
        suspendCancellableCoroutine { continuation ->
            db.collection(TOPICS_COLLECTION)
                .document(documentId)
                .update("quiz",questions)
                .addOnSuccessListener {
                    continuation.resume(true)
                }
                .addOnFailureListener {
                    continuation.resume(true)
                }
        }

}