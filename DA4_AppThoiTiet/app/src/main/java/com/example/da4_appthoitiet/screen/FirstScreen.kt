package com.example.da4_appthoitiet.screen

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.da4_appthoitiet.MainActivity
import com.example.da4_appthoitiet.R
import com.example.da4_appthoitiet.databinding.ActivityFirstScreenBinding
import kotlin.concurrent.timer

class FirstScreen : AppCompatActivity() {
    private lateinit var binding: ActivityFirstScreenBinding
    private var currentProgress = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        createProgressbar()

    }

    private fun createProgressbar() {
        binding.progressbar.progress = 0
        binding.progressbar.max = 100
        val timer = object : CountDownTimer(5000, 100) {
            override fun onTick(p0: Long) {
                val progress = ((5000 - p0)/50).toInt()
                binding.progressbar.progress = progress

            }
            override fun onFinish() {
                binding.progressbar.progress = 100
                val intent = Intent(this@FirstScreen, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        timer.start()

    }

}