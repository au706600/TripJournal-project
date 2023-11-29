package com.example.tripjournal_project

import android.nfc.Tag
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class firestore(private val api: FirebaseFirestore, private val auth: FirebaseAuth) {
    companion object
    {
        const val Tag = "FIRE_STORE_SERVICE"
    }

    suspend fun signup(email: String, password: String)
    {
        suspendCoroutine {
            continuation -> auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task -> if (task.isSuccessful) {
                Log.d(Tag, "createUserWithEmail:success")
                val user = auth.currentUser ?: throw Exception("something went wrong")
                val signedInUser = user.email?.let { User(user.providerId, it) }
                    ?: throw Exception("createUserWithEmail:$email failure")
                continuation.resume(signedInUser)
            } else {
                Log.w(Tag, "createUserWithEmail: failure", task.exception)
                throw throw Exception("createUserWithEmail: $email failure", task.exception)
            }
            }
        }
    }

    suspend fun login(email: String, password: String)
    {
        suspendCoroutine {
            continuation -> auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task -> if (task.isSuccessful) {
                Log.d(Tag, "createUserWithEmail:success")
                val user = auth.currentUser ?: throw Exception("something went wrong")
                val signedInUser = user.email?.let { User(user.providerId, it) }
                    ?: throw Exception("createUserWithEmail: $email failure")
                continuation.resume(signedInUser)
            } else {
                Log.w(Tag, "createUserWithEmail: failure", task.exception)
                throw throw Exception("createUserWithEmail: $email failure", task.exception)
            }
            }
        }
    }
}