package com.example.whatsapp.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.whatsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.MutableStateFlow

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val authenticationViewModel: AuthenticationViewModel by viewModels()

    val optValue = MutableStateFlow<String>("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (checkAuthenticationStatus()) {

        } else {
            binding.userAuthenticationLayout.visibility = View.VISIBLE
            binding.appLogo.visibility = View.VISIBLE
            binding.userNumberLayout.visibility = View.VISIBLE
            binding.textInputLayout1.visibility = View.VISIBLE
            binding.etNumber.visibility = View.VISIBLE
            binding.btProceed.visibility = View.VISIBLE


        }
        binding.btProceed.setOnClickListener {
            authenticationViewModel.signInWithPhoneNumber(
                "+91${binding.etNumber.text.toString()}",
                this
            )
        }
    }

    private fun checkAuthenticationStatus(): Boolean {
        return false
    }

    fun showBottomSheet() {
        val optFragment = OTPFragment()
        supportFragmentManager.beginTransaction().add(optFragment, "bottomSheetOtpFragment")
            .commit()

    }
}