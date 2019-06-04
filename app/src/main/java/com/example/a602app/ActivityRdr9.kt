package com.example.a602app

import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity

class ActivityRdr9 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)//super called

        //Redirect user to quickselect and direct QS to press appropriate button
        var intent = Intent(this, QuickSelect::class.java)
        intent.putExtra("buttonNumber", "9")
        startActivity(intent)
    }
}