package com.example.mathgame

import android.content.Intent
import android.content.IntentSender.OnFinished
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.*
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var score : TextView
    lateinit var life : TextView
    lateinit var time : TextView
    lateinit var question : TextView
    lateinit var answer : EditText
    lateinit var ok : Button
    lateinit var next : Button

    var correctanswer = 0
    var userlife = 3
    var userscore = 0


     lateinit var timer : CountDownTimer
     private val startTimerInMillis : Long = 20000
     var timeLeftInMillis : Long = startTimerInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

         score = findViewById(R.id.textViewScore)
         life = findViewById(R.id.textViewLife)
         time = findViewById(R.id.textViewTime)
         question = findViewById(R.id.questionView)
         answer = findViewById(R.id.answerBox)
         ok = findViewById(R.id.buttonOk)
         next = findViewById(R.id.buttonNext)

         gameContinue()


        ok.setOnClickListener {

            var input = answer.text.toString()

            if (input == "" )
            {
                Toast.makeText(applicationContext,"Please write an answer or click Next button",
                    Toast.LENGTH_LONG).show()
            }
            else
            {
                pauseTimer()

                val answer = input.toInt()

                if( answer == correctanswer)
                {
                    userscore += 10
                    question.text = "Congratulations, your answer is Correct"
                    score.text = userscore.toString()
                }

                else
                {

                    userlife--
                    question.text = "Sorry, your answer is wrong"
                    life.text = userlife.toString()

                }

            }

        }

        next.setOnClickListener {

            pauseTimer()
            resetTimer()

            answer.setText("")

            if (userlife <= 0)
            {
                Toast.makeText(applicationContext,"Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity,ResultActivity::class.java)
                intent.putExtra("score",userscore)
                startActivity(intent)
                finish()
            }
            else
            {
                gameContinue()
            }


        }

    }
    fun gameContinue() {

        val number1 = Random.nextInt(0, 100)
        val number2 = Random.nextInt(0, 100)
        question.text = "$number1+$number2"
        correctanswer = number1 + number2


        startTimer()
    }

    fun startTimer() {
    timer = object : CountDownTimer(timeLeftInMillis, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            timeLeftInMillis = millisUntilFinished
            updateText()
        }

        override fun onFinish() {

            pauseTimer()
            resetTimer()
            updateText()

            userlife--
            life.text = userlife.toString()
            question.text = "Sorrry, Time is up!"
        }
    }.start()
}

    fun updateText() {
        val remainingTime: Int = (timeLeftInMillis / 1000).toInt()
        time.text = String.format(Locale.getDefault(), "%02d", remainingTime)
    }

    fun pauseTimer() {
        timer.cancel()
    }

    fun resetTimer() {
        timeLeftInMillis = startTimerInMillis
        updateText()
    }



}