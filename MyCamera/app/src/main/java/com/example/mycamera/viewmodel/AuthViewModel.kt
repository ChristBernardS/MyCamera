package com.example.mycamera.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mycamera.repository.AuthRepository

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    fun login(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        authRepository.login(email, password, callback)
    }

    fun register(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        authRepository.register(email, password, callback)
    }
}
