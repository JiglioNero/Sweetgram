package com.example.sweetgram.ui.activities.login

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.sweetgram.R
import com.example.sweetgram.SweetgramApplication
import com.example.sweetgram.data.entitys.Lover
import com.example.sweetgram.databinding.ActivityLoginBinding
import javax.inject.Inject

class LoginActivity: AppCompatActivity() {

    lateinit var viewModel: LoginViewModel
    lateinit var binding: ActivityLoginBinding
    @Inject lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SweetgramApplication.instance.injector.inject(this)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.loginActivity = this
        binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        binding.loginVM = viewModel


        if (sharedPreferences.contains("username")) {
            viewModel.tryToLoginByCredentials(Lover(username = sharedPreferences.getString("username", "").orEmpty()).getCredentials())
        }

    }
}