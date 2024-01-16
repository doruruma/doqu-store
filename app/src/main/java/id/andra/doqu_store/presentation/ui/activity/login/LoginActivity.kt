package id.andra.doqu_store.presentation.ui.activity.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import id.andra.doqu_store.R
import id.andra.doqu_store.databinding.ActivityLoginBinding
import id.andra.doqu_store.presentation.ui.activity.main.MainActivity
import id.andra.doqu_store.presentation.ui.activity.signup.SignUpActivity
import id.andra.doqu_store.utils.Var
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = PreferenceManager.getDefaultSharedPreferences(this)
        editor = pref.edit()
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        setEventListeners()
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                if (it.token != null) {
                    editor.putString(Var.PREF_TOKEN, it.token.access)
                    editor.putString(Var.PREF_REFRESH_TOKEN, it.token.refresh)
                    editor.apply()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                    viewModel.onEvent(LoginEvent.ResetToken)
                }
                if (it.isError) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Akun tidak ditemukan",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.onEvent(LoginEvent.ResetError)
                }
            }
        }
    }

    private fun setEventListeners() {
        binding.inputEmail.addTextChangedListener {
            viewModel.onEvent(LoginEvent.OnEmailChanged(it.toString()))
        }
        binding.inputPassword.addTextChangedListener {
            viewModel.onEvent(LoginEvent.OnPasswordChanged(it.toString()))
        }
        binding.btnTogglePassword.setOnClickListener {
            viewModel.onEvent(LoginEvent.OnTogglePassword)
        }
        binding.textRedirectSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        binding.btnSubmit.setOnClickListener {
            viewModel.onEvent(LoginEvent.OnSubmit)
        }
    }

}