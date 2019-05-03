package com.example.a602app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_you_tube.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_you_tube.*

class YouTubeActivity : AppCompatActivity() {

    //Test data
    var newLink = YouTubeConnect("https://youtu.be/48uAQYf3Uhc?t=31", 8,2)
    lateinit var addressText: EditText
    lateinit var minuteText: EditText
    lateinit var secondText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_you_tube)
        setSupportActionBar(toolbar)

        addressText = findViewById(R.id.address)
        minuteText = findViewById(R.id.minutes)
        secondText = findViewById(R.id.seconds)

        buttonLaunch.setOnClickListener {
            Log.d("Team", "Button 1 was pressed")
            clickedBtn(newLink)
        }
    }

    fun clickedBtn(yt:YouTubeConnect)//feed in a YTC object to bring up video
    {

        newLink = YouTubeConnect(addressText.text.toString(), minuteText.text.toString().toInt(), secondText.text.toString().toInt())
        var openWebPage = Intent(Intent.ACTION_VIEW)
        openWebPage.data = Uri.parse(yt.url)
        startActivity(openWebPage)
    }

}
