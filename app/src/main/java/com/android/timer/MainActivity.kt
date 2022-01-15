package com.android.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    private var time =0
    private var timerTask : Timer?= null
    private var isRunning = false
    private var lap = 1

    lateinit var secTextView : TextView
    lateinit var milliTextView: TextView
    lateinit var  secEditText : EditText
    lateinit var setButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        secTextView = findViewById(R.id.secTextView)
        milliTextView = findViewById(R.id.milliTextView)
        secEditText = findViewById(R.id.secEditText)
        setButton = findViewById(R.id.setButton)

        setButton.setOnClickListener {
            time = secEditText.text.toString().toInt()*100
            start()
        }
    }

    private fun pause(){
        timerTask?.cancel()
    }

    private fun start(){
        timerTask = timer(period=10){
            time--
            val sec = time / 100
            val milli = time % 100

            if(sec==0 && milli==0) timerTask?.cancel()
            runOnUiThread {
                secTextView.text = "$sec"
                milliTextView.text = "$milli"
            }
        }
    }


    private fun reset(){
        timerTask?.cancel()
        time=0
        isRunning=false
        secTextView.text="0"
        milliTextView.text="00"
        lap=1
    }
}