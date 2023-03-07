package com.example.mylibraryclient

import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.mylibraryclient.databinding.ActivityLoginBinding
import com.lib.loopsdk.auth.LoopInstance

class LoginActivity : AppCompatActivity() {
    private var primaryUserIdReceived: Int = 0
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        primaryUserIdReceived = intent?.getIntExtra("USERID", 0)!!
        when (primaryUserIdReceived) {
            1 -> {
                binding.tvLoginMethod.text = "Email"
                binding.etInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }

            2 -> {
                binding.tvLoginMethod.text = "Mobile"
                binding.etInput.inputType = InputType.TYPE_CLASS_PHONE
            }

        }

        binding.btnLogin.setOnClickListener {
            if(binding.etInput.text.isNullOrEmpty()) {
                Toast.makeText(this, "Please input something", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                when (primaryUserIdReceived) {
                    1 -> {
                        LoopInstance.loginCustomer(this, email = binding.etInput.text?.toString())
                    }

                    2 -> {
                        LoopInstance.loginCustomer(this, mobile = binding.etInput.text?.toString())
                    }

                }
            }

        }

    }
}