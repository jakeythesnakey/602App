package com.example.a602app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.a602app.Sounds.Sound;
import com.example.a602app.Sounds.TimeStamp;
import com.example.a602app.Sounds.YouTube;

public class QuickSelect extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_select);

        //attaches functionality to launch button
        findViewById(R.id.youtubebutton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchYTActivity();
            }
        });

        //attaches functionality to launch button
        findViewById(R.id.homebutton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                launchPlayerActivity();
            }
        });

        //Sets all buttons to relate to their saved data
        for (final int buttonID : FileIO.getButtonIDArray()) {
            findViewById(buttonID).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    YouTube youTube = FileIO.getButtonYoutTubeFromID(buttonID);
//                    YouTube youtube = new YouTube("https://www.youtube.com/watch?v=AD5qt7xoUU8", new TimeStamp(60, -1)); // TODO: link this to getting save data
                    if (youTube != null) {
                        launchYTVideo(youTube);
                    }
                }
            });
        }
    }

    public void launchYTActivity() {
        Intent intent = new Intent(this, YouTubeActivity.class);
        startActivity(intent);
    }

    public void launchPlayerActivity() {
        Intent intent = new Intent(this, MusicPlayer.class);
        startActivity(intent);
    }

    public void launchYTVideo(YouTube youtube) {
        Intent intent = new Intent(this, YouTubeActivity.class);
        intent.putExtra("connection", youtube.getIntentExtra());
        startActivity(intent);
    }
}
