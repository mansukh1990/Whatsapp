package com.example.whatsapp.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.whatsapp.databinding.FragmentOTPBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OTPFragment : BottomSheetDialogFragment() {

    private var binding: FragmentOTPBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentOTPBinding.inflate(layoutInflater, container, false)
        setUpOTPInput()
        return binding!!.root
    }

    private fun setUpOTPInput() {
        binding?.let { it ->
            it.otpBox1.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let { first ->
                        if (first.length == 1) {
                            it.otpBox2.requestFocus()
                        }

                    }

                }

                override fun afterTextChanged(s: Editable?) {
                }

            })

            it.otpBox2.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let { second ->
                        if (second.length == 1) {
                            it.otpBox3.requestFocus()
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
            it.otpBox3.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let { third ->
                        if (third.length == 1) {
                            it.otpBox4.requestFocus()
                        }

                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
            it.otpBox4.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let { fourth ->
                        if (fourth.length == 1) {
                            it.otpBox5.requestFocus()
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
            it.otpBox5.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let { fifth ->
                        if (fifth.length == 1) {
                            it.otpBox6.requestFocus()
                        }

                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
            it.otpBox6.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let { sixth ->
                        if (sixth.length == 1) {
                            val otpValue = it.otpBox1.text.toString() + it.otpBox2.text.toString() +
                                    it.otpBox3.text.toString() + it.otpBox4.text.toString() +
                                    it.otpBox5.text.toString() + it.otpBox6.text.toString()
                            (activity as MainActivity).optValue.value = otpValue
                        }

                    }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })

        }
    }
}