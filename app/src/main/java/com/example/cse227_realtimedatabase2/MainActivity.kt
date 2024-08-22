package com.example.cse227_realtimedatabase2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var btnInsertdata: Button
    lateinit var btnfetchdata : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnInsertdata = findViewById(R.id.button)
        btnfetchdata = findViewById(R.id.button2)

        btnInsertdata.setOnClickListener {
            val intent = Intent(this, InsertionActivity::class.java)
            startActivity(intent)
        }
        btnfetchdata.setOnClickListener {
            val intent = Intent(this, FetchingActivity::class.java)
            startActivity(intent)

        }
    }
}