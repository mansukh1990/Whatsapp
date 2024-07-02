package com.example.whatsapp.domain.use_case

import com.example.whatsapp.domain.repository.AuthRepository
import com.example.whatsapp.presentation.MainActivity
import com.google.firebase.auth.PhoneAuthCredential
import javax.inject.Inject

class AuthenticationUseCase @Inject constructor(
    private var authRepository: AuthRepository
) {
    fun phoneNumberSignIn(phoneNumber: String, activity: MainActivity) =
        authRepository.phoneNumberSignIn(phoneNumber = phoneNumber, activity = activity)

    fun isUserAuthenticated() = authRepository.isUserAuthenticated()

    fun getUserId() = authRepository.getUserId()

    suspend fun signWithAuthCredential(phoneAuthCredential: PhoneAuthCredential) =
        authRepository.signInWithAthCredential(phoneAuthCredential)

}