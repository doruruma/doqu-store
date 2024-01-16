package id.andra.doqu_store.presentation.ui.activity.splash

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import id.andra.doqu_store.databinding.ActivitySplashBinding
import id.andra.doqu_store.presentation.ui.activity.login.LoginActivity
import id.andra.doqu_store.presentation.ui.activity.main.MainActivity
import id.andra.doqu_store.utils.Var
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var pref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var binding: ActivitySplashBinding
    private lateinit var viewModel: SplashViewModel
    private val duration = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = PreferenceManager.getDefaultSharedPreferences(this)
        editor = pref.edit()
        viewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val currentProgress = 85
        ObjectAnimator.ofInt(binding.loader, "progress", currentProgress)
            .setDuration(duration)
            .start()
        viewModel.onEvent(
            SplashEvent.OnLoad(
                accessToken = pref.getString(Var.PREF_TOKEN, "") ?: "",
                refreshToken = pref.getString(Var.PREF_REFRESH_TOKEN, "") ?: ""
            )
        )
        observeState()
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect {
                if (it.token != null) {
                    editor.putString(Var.PREF_TOKEN, it.token.access)
                    editor.putString(Var.PREF_REFRESH_TOKEN, it.token.refresh)
                    editor.apply()
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                    viewModel.onEvent(SplashEvent.ResetToken)
                }
                if (it.isNavigateToLogin) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                        finish()
                    }, duration)
                    viewModel.onEvent(SplashEvent.ResetIsNavigateToLogin)
                }
            }
        }
    }

}