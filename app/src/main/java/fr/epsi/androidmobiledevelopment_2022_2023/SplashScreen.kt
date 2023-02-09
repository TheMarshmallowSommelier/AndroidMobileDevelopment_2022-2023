package fr.epsi.androidmobiledevelopment_2022_2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val newIntent=Intent(application,HomeActivity::class.java)
            startActivity(newIntent)
            finish()
        },2000)
    }

}