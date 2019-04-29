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

    //Test data
    var testStamp = TimeStamp(0,30)
    var newLink = YouTubeConnect("https://www.youtube.com/watch?v=48uAQYf3Uhc", testStamp)


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        button1.setOnClickListener {
            d("Team", "Button 1 was pressed")
            clickedBtn(newLink)
        }
    }

    fun clickedBtn(yt:YouTubeConnect)
    {
        var openWebPage = Intent(Intent.ACTION_VIEW)
        openWebPage.data = Uri.parse(yt.url)
        startActivity(openWebPage)
    }
}
