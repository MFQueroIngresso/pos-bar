package com.barqueroingresso.features.login.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.barqueroingresso.MainActivity
import com.barqueroingresso.R
import com.barqueroingresso.databinding.ActivityLoginBinding

import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: LoginViewModel by viewModels()

        binding.btnLogin.setOnClickListener {
            val isValid = viewModel.validateCredentials(
                email = binding.emailEditLogin.text.toString(),
                password = binding.passwordEditLogin.text.toString()
            )
            if (isValid) {
                binding.btnLogin.isEnabled = false
                viewModel.login(
                    email = binding.emailEditLogin .text.toString(),
                    password = binding.passwordEditLogin.text.toString()
                )
                binding.progressBarLogin.visibility = View.VISIBLE
            } else showErrorMessage(getString(R.string.message_error_login))

        }
        viewModel.success.observe(this) {
            openNextActivity()
        }
        viewModel.errorMessages.observe(this) {
            binding.btnLogin.isEnabled = true
            showErrorMessage(getString(R.string.message_error_login))
            binding.progressBarLogin.visibility = View.GONE
        }
    }

    private fun openNextActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showErrorMessage(message: String) {
        Snackbar.make(binding.root, getString(R.string.message_error_login), Snackbar.LENGTH_LONG)
            .setBackgroundTint(getColor(R.color.error_color))
            .setActionTextColor(getColor(R.color.white))
            .show()
    }
}