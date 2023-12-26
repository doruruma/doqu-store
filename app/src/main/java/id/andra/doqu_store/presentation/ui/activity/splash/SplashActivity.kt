package id.andra.doqu_store.presentation.ui.activity.splash

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import id.andra.doqu_store.databinding.ActivitySplashBinding
import id.andra.doqu_store.presentation.ui.activity.login.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val duration = 5000L
        val currentProgress = 100
        ObjectAnimator.ofInt(binding.loader, "progress", currentProgress)
            .setDuration(duration)
            .start()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, duration)
    }

}