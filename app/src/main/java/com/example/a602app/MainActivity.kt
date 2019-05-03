package com.example.a602app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity()
{
    //This function sets up the main screen of the app and attaches functionality
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)//call super
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)//setup toolbar

        //Sets first button to take user to youtube section
        button1.setOnClickListener {
            clickedBtn()
        }

        //Sets second button to take user to local player section
        button2.setOnClickListener {
            clickedBtn2()
        }

    }

    fun clickedBtn()//Open youtube activity
    {
        var intent = Intent(this, YouTubeActivity::class.java)
        startActivity(intent)
    }

    fun clickedBtn2()//Open Kobe Activity
    {
        var intent = Intent(this, KobeActivity::class.java)
        startActivity(intent)
    }
}
