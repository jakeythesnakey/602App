package com.example.a602app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_quick_select.*
import kotlinx.android.synthetic.main.content_main.*

class QuickSelect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_select)

        //Sets first button to take user to yt player section TEST DATA
        this.mediabutton1.setOnClickListener {
            //test
            var testYT = YouTubeConnect("https://www.youtube.com/watch?v=AD5qt7xoUU8", 1, 10)// TEST DATA
            clickedBtn(testYT)
        }

    }

    fun clickedBtn(mediaLink : MediaHolder)//determines what type of media is being requested and responds appropriately
    {
        if (mediaLink.mediaType == 1)//checks if subclass is correct type
        {
            if (mediaLink is YouTubeConnect)//casts to YTC subclass
            launchYTVideo(mediaLink)
        }

    }

    fun launchYTVideo(ytc : YouTubeConnect)//Open youtube activity
    {
        var intent = Intent(this, YouTubeActivity::class.java)
        intent.putExtra("connection", ytc.toString())
        startActivity(intent)
    }
}