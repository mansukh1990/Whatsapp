package com.example.whatsapp.domain.repository

import com.example.whatsapp.presentation.MainActivity
import com.example.whatsapp.utils.Resource
import com.google.firebase.auth.PhoneAuthCredential
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun phoneNumberSignIn(phoneNumber: String, activity: MainActivity): Flow<Resource<Boolean>>

    fun isUserAuthenticated(): Boolean

    fun getUserId(): String

    suspend fun signInWithAthCredential(phoneAuthCredential: PhoneAuthCredential): Resource<Boolean>

}