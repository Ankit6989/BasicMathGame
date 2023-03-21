package com.example.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val add = findViewById<Button>(R.id.buttonAdd)
        val sub = findViewById<Button>(R.id.buttonSub)
        val multi = findViewById<Button>(R.id.buttonMul)

        add.setOnClickListener {

            val intent = Intent(this@MainActivity,GameActivity::class.java)
            startActivity(intent)

        }
        sub.setOnClickListener {

            val intent = Intent(this@MainActivity,GameActivity2::class.java)
            startActivity(intent)

        }
        multi.setOnClickListener {

            val intent = Intent(this@MainActivity,GameActivity3::class.java)
            startActivity(intent)

        }






    }

}