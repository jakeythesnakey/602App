package com.example.a602app

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_quick_select.*
import kotlinx.android.synthetic.main.content_main.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream

class QuickSelect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_select)

        //got to YouTubeActivity
        this.youtubebutton.setOnClickListener()
        {
            clickedBtnyt()
        }

        this.homebutton.setOnClickListener()
        {
            clickedBtn2home()
        }
        //Sets first button to take user to yt player section TEST DATA
        this.mediabutton1.setOnClickListener {
            //test
            var testYT = YouTubeConnect("https://youtu.be/ZbZSe6N_BXs", 0, 30)// TEST DATA
            clickedBtn(testYT)
        }
        this.mediabutton2.setOnClickListener {
            var testYT = YouTubeConnect("https://youtu.be/d8ekz_CSBVg",2,4)
            clickedBtn(testYT)
        }
        this.mediabutton3.setOnClickListener {
            var testYT = YouTubeConnect("https://youtu.be/f7McpVPlidc",0,1)
            clickedBtn(testYT)
        }
        this.mediabutton4.setOnClickListener {
            var testYT = YouTubeConnect("https://youtu.be/bq4SGw_L-c4",22,4)
            clickedBtn(testYT)
        }
        this.mediabutton5.setOnClickListener {
            var testYT = YouTubeConnect("https://youtu.be/OK4fJhbRL1g",0,40)
            clickedBtn(testYT)
        }
        this.mediabutton6.setOnClickListener {
            var testYT = YouTubeConnect("https://www.youtube.com/watch?v=rQO8escJC78",0,20)
            clickedBtn(testYT)
        }
        this.mediabutton7.setOnClickListener {
            var testYT = YouTubeConnect("https://youtu.be/2ZBtPf7FOoM",0,33)
            clickedBtn(testYT)
        }
        this.mediabutton8.setOnClickListener {
            var testYT = YouTubeConnect("https://youtu.be/QZXc39hT8t4",0,8)
            clickedBtn(testYT)
        }
        this.mediabutton9.setOnClickListener {
            var testYT = YouTubeConnect("https://youtu.be/d8ekz_CSBVg",2,4)
            clickedBtn(testYT)
        }

        if (this.intent != null && this.intent.extras != null) {//checks if there are extras attached to intent
            val connectString = this.intent.getStringExtra("buttonNumber")//attaches extra to local variable
            if (connectString == "1"){
                mediabutton1.performClick()
            }
            if (connectString == "2"){
                mediabutton2.performClick()
            }
            if (connectString == "3"){
                mediabutton3.performClick()
            }
            if (connectString == "4"){
                mediabutton4.performClick()
            }
            if (connectString == "5"){
                mediabutton5.performClick()
            }
            if (connectString == "6"){
                mediabutton6.performClick()
            }
            if (connectString == "7"){
                mediabutton7.performClick()
            }
            if (connectString == "8"){
                mediabutton8.performClick()
            }
            if (connectString == "9"){
                mediabutton9.performClick()
            }
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

    fun clickedBtnyt()//Open youtube activity
    {
        var intent = Intent(this, YouTubeActivity::class.java)
        startActivity(intent)

    }

    fun clickedBtn2home()//Open Kobe Activity
    {
        var intent = Intent(this, MusicPlayer::class.java)
        startActivity(intent)
    }
}