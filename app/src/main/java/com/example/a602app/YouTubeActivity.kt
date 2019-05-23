package com.example.a602app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_you_tube.*
import kotlinx.android.synthetic.main.content_you_tube.*


/**
 * This class is the creates an activity screen in the Personal Soundboard app.
 * It is designed to take a text input form the user composed to a YouTube link
 * and a timestamp. It then opens that YouTube video at the given timestamp.
 *
 * @author Jake Kampkes.
 * @since 23/4/19
 */
class YouTubeActivity : AppCompatActivity()
{

    /**
     * Variable for inputting address data
     * */
    lateinit var addressText: EditText
    /**
     * Variable for inputting minutes data
     * */
    lateinit var minuteText: EditText
    /**
     * Variable for inputting seconds data
     * */
    lateinit var secondText: EditText

    /**
     * This function sets up the Youtube screen of the app and attaches functionality
     * to the buttons layed out by content_you_tube.xml
     */
    //This function sets up the Youtube screen of the app and attaches functionality
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)//super called
        setContentView(R.layout.activity_you_tube)//attaches xml for buttons and labels etc
        setSupportActionBar(toolbar)//Attaches toolbar

        //This block allows activity to automatically launch youtube videos on the opening of the class
        if (this.intent != null && this.intent.extras != null) {//checks if there are extras attached to intent
            val connectString = this.intent.getStringExtra("connection")//attaches extra to local variable
            val connectArray = connectString.split(",")//splits extras into three string variables for address and time
            val newConnect = YouTubeConnect(connectArray[0],connectArray[1].toInt(),connectArray[2].toInt())// creates a youTubeConnect class with passed in variables
            launchYT(newConnect)//launches youtube video
        }

        //attaches functionality to launch button
        buttonLaunch.setOnClickListener {
            clickedBtn()
        }
    }

    /**
     * This function takes user-inputted data, and attempts to launch youtube with user-inputted details
     *
     * @param newLink new YouTubeConnect object created from user data
     */
    fun clickedBtn()
    {
        //Default data (I hope you like Brooklyn 99)
        var newLink = YouTubeConnect("https://youtu.be/48uAQYf3Uhc", 8,2)

        //Attach variables to text input fields
        addressText = findViewById(R.id.address)
        minuteText = findViewById(R.id.minutes)
        secondText = findViewById(R.id.seconds)

        //Create YouTubeConnect object with user-inputted data
        newLink = YouTubeConnect(addressText.text.toString(), minuteText.text.toString().toInt(), secondText.text.toString().toInt())//sets up variables
        launchYT(newLink)//calls YouTube launcher
    }

    /**
     * This function takes user-inputted data, and attempts to launch youtube with user-inputted details
     *
     * @param newLink YouTubeConnect object passed in with video data
     * @param openWebPage Intent used to open youtube
     */
    fun launchYT(newLink : YouTubeConnect)
    {
        var openWebPage = Intent(Intent.ACTION_VIEW)//Creates new intent to open a new activity/screen
        openWebPage.data = Uri.parse(newLink.url)//sets the target of the new screen to the given youtube link

        if (openWebPage.resolveActivity(getPackageManager()) != null) {//Safely checks if activity will resolve correctly
            startActivity(openWebPage)//starts the new activity
        }
        else{
            addressText.setText("Not a YouTube address")//If youtube link does not resolve, display error to user
        }
    }
}
