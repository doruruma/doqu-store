package id.andra.doqu_store.presentation.ui.activity.signup

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import id.andra.doqu_store.R
import id.andra.doqu_store.databinding.ActivitySignUpBinding
import id.andra.doqu_store.presentation.ui.activity.login.LoginActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        setEventListeners()
    }

    private fun setEventListeners() {
        binding.btnTogglePassword.setOnClickListener {
            viewModel.onEvent(SignUpEvent.OnTogglePassword)
        }
        binding.btnTogglePasswordConfirm.setOnClickListener {
            viewModel.onEvent(SignUpEvent.OnTogglePasswordConfirm)
        }
        binding.textRedirectLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}