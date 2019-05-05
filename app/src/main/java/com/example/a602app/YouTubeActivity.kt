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

class YouTubeActivity : AppCompatActivity()
{

    //Variables for inputting time and address data to youtube
    lateinit var addressText: EditText
    lateinit var minuteText: EditText
    lateinit var secondText: EditText


    //This function sets up the Youtube screen of the app and attaches functionality
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)//super called
        setContentView(R.layout.activity_you_tube)//attaches xml for buttons and labels etc
        setSupportActionBar(toolbar)//Attaches toolbar

        //Set play button
        buttonLaunch.setOnClickListener {
            clickedBtn()
        }
    }

    //This function opens a YouTube window with inputted data
    fun clickedBtn()//feed in a YTC object to bring up video
    {
        //Default data (I hope you like Brooklyn 99)
        var newLink = YouTubeConnect("https://youtu.be/48uAQYf3Uhc", 8,2)

        //Attach variables to text input fields
        addressText = findViewById(R.id.address)
        minuteText = findViewById(R.id.minutes)
        secondText = findViewById(R.id.seconds)

        newLink = YouTubeConnect(addressText.text.toString(), minuteText.text.toString().toInt(), secondText.text.toString().toInt())//sets up variables
        var openWebPage = Intent(Intent.ACTION_VIEW)//Creates new intent to open a new activity/screen
        openWebPage.data = Uri.parse(newLink.url)//sets the target of the new screen to the given youtube link
        startActivity(openWebPage)//starts the new activity
    }
}
