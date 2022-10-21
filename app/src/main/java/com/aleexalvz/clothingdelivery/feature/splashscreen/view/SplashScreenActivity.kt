package com.aleexalvz.clothingdelivery.feature.splashscreen.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import com.aleexalvz.clothingdelivery.R
import com.aleexalvz.clothingdelivery.feature.login.view.LoginActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        initSplashScreen()
    }

    private fun initSplashScreen() {
        val countDownTimer = object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {}
            override fun onFinish() {
                navigateToLogin()
            }
        }
        countDownTimer.start()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}