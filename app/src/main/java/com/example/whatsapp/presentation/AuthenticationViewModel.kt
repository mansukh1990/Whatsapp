package com.example.whatsapp.presentation

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsapp.domain.use_case.AuthenticationUseCase
import com.example.whatsapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authUseCase: AuthenticationUseCase
) : ViewModel() {

    fun signInWithPhoneNumber(phoneNumber: String, activity: MainActivity) {

        viewModelScope.launch {
            authUseCase.phoneNumberSignIn(phoneNumber = phoneNumber, activity = activity).collect {
                when (it) {
                    is Resource.Loading -> {
                        Toast.makeText(activity.baseContext, "Loading", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Error -> {
                        Toast.makeText(activity.baseContext, "Error", Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Success -> {
                        Toast.makeText(activity.baseContext, "Success", Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }
    }
}