package com.gl4.pizzashop

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var txtOrder: TextView
    private lateinit var txtTime: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val order = intent.getStringExtra("order");
        txtOrder = findViewById(R.id.splashText);
        txtTime = findViewById(R.id.timerView);
        txtOrder.text = order;
        timer.start()
    }

    private val timer = object : CountDownTimer(5000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            txtTime.text = (millisUntilFinished / 1000).toString()
        }

        override fun onFinish() {
            val main = Intent(this@SplashScreen, MainActivity::class.java);
            startActivity(main);
        }
    }
}