package com.barqueroingresso.features.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.barqueroingresso.MainActivity
import com.barqueroingresso.databinding.ActivityLoginBinding
import com.barqueroingresso.features.login.data.api.RetrofitService
import com.barqueroingresso.features.login.data.model.LoginRequest
import com.barqueroingresso.features.login.data.repository.UserRepository
import com.barqueroingresso.features.login.viewmodel.LoginViewModel
import com.barqueroingresso.features.login.viewmodel.LoginViewModelFactory
import com.barqueroingresso.util.Validator



class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel: LoginViewModel
    private val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(
                this,
                LoginViewModelFactory(UserRepository(retrofitService))
            )[LoginViewModel::class.java]

        setupUi()

    }
    private fun setupUi() = binding.apply {

        btnLogin.setOnClickListener {

            if (!Validator.validateEmail(emailEditLogin.text.toString())) {
                emailEditLogin.error = "Preencha o nome corretamente"
                emailEditLogin.requestFocus()
                return@setOnClickListener
            }
            if (!Validator.validateSenha(passwordEditLogin.text.toString())) {
                passwordEditLogin.error = "Preencha a senha corretamente"
                passwordEditLogin.requestFocus()
                return@setOnClickListener
            }

            viewModel.login(
                LoginRequest(
                    emailEditLogin.text.toString(),
                    passwordEditLogin.text.toString()
                )
            )
            binding.progressBar.visibility = View.VISIBLE
        }

    }
    override fun onStart() {
        super.onStart()
        viewModel.success.observe(this@LoginActivity, Observer {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        })

        viewModel.errorMessages.observe(this@LoginActivity, Observer {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this@LoginActivity, it, Toast.LENGTH_SHORT).show()
        })
    }
}
