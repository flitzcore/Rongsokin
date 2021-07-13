package com.example.b1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.masuk.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.masuk)

        button_daftar_sekarang.setOnClickListener {
            startActivity(Intent( this@LoginActivity, RegisterActivity::class.java))
        }
    }
}