package com.example.mycamera.repository

import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun login(email: String?, password: String?, callback: (Boolean, String?) -> Unit) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            callback(false, "Email dan password tidak boleh kosong")
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }


    fun register(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }
}
