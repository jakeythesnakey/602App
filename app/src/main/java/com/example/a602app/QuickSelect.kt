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
        //val b1 : Button(findByViewId(@+id/mediabutton1))
        //Sets first button to take user to yt player section
        this.mediabutton1.setOnClickListener {
            clickedBtn()
        }

    }

    fun clickedBtn()//Open youtube activity
    {
        //test
        var testYT = YouTubeConnect("https://www.youtube.com/watch?v=AD5qt7xoUU8", 1, 10)
        var intent = Intent(this, YouTubeActivity::class.java)
        intent.putExtra("connection", testYT.toString())
        startActivity(intent)

    }
}