package com.example.a602app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;
import com.example.a602app.Sounds.*;

public class YouTubeActivity extends AppCompatActivity {
    public YouTubeActivity() {

    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_you_tube); //attaches xml for buttons and labels etc
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar)); //Attaches toolbar

        //This block allows activity to automatically launch youtube videos on the opening of the class
        if (this.getIntent() != null && this.getIntent().getExtras() != null) {
            String connectString = this.getIntent().getStringExtra("connection");
            if (connectString != null) {
                String[] connectArray = connectString.split(",");
                YouTube youTube;

                if (connectArray.length >= 3) {
                    youTube = new YouTube(
                            connectArray[0],
                            new TimeStamp(Integer.parseInt(connectArray[1]), Integer.parseInt(connectArray[2]))
                    );
                } else if (connectArray.length > 0){
                    youTube = new YouTube(connectArray[0], new TimeStamp());
                } else {
                    youTube = new YouTube("https://www.youtube.com/watch?time_continue=97&v=VX5nxFwAQ-M", new TimeStamp());
                }

                Log.d("act", "launchYT(connect) url: " + youTube.getUrl());
                launchYT(youTube); //launches youtube video
            }
        }

        //attaches functionality to launch button
        findViewById(R.id.buttonLaunch).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickedLaunchYouTube();
            }
        });

        // attaches functionality to save button
        findViewById(R.id.buttonSaveToButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickedSaveButton();
            }
        });
    }

    private void clickedSaveButton() {
        String url = ((EditText)findViewById(R.id.address)).getText().toString();
        String textStartTime = ((EditText)findViewById(R.id.minutes)).getText().toString();
        String textStopTime = ((EditText)findViewById(R.id.seconds)).getText().toString();
        String textButtonIndex = ((EditText)findViewById(R.id.editTextButtonIndex)).getText().toString();

        if (url.trim().length() > 0) {
            int startTime = 0;
            int stopTime = 0;
            int buttonIndex = -1;

            // Validate startTime
            try {
                startTime = Integer.parseInt(textStartTime) * 60; // TODO: * 60 is a temp work around. startTime is minutes, and stopTime is seconds.
            } catch(NumberFormatException ignored) { }

            // Validate stopTime
            try {
                stopTime = Integer.parseInt(textStopTime);
            } catch(NumberFormatException ignored) { }

            // Validate buttonIndex
            try {
                buttonIndex = Integer.parseInt(textButtonIndex) - 1; // -1 for index shift
            } catch(NumberFormatException ignored) { }

            FileIO.saveSound(new YouTube(url, new TimeStamp(startTime + stopTime, -1)), buttonIndex);
        }
    }

    public void clickedLaunchYouTube() {
        String url = ((EditText)findViewById(R.id.address)).getText().toString();
        String textStartTime = ((EditText)findViewById(R.id.minutes)).getText().toString();
        String textStopTime = ((EditText)findViewById(R.id.seconds)).getText().toString();

        if (url.trim().length() > 0) {
            int startTime = 0;
            int stopTime = 0;

            // Validate startTime
            try {
                startTime = Integer.parseInt(textStartTime) * 60; // TODO: * 60 is a temp work around. startTime is minutes, and stopTime is seconds.
            } catch(NumberFormatException ignored) { }

            // Validate stopTime
            try {
                stopTime = Integer.parseInt(textStopTime);
            } catch(NumberFormatException ignored) { }

            //Create YouTubeConnect object with user-inputted data and launches youtube
            launchYT(
                    new YouTube(
                            url,
                            new TimeStamp(startTime + stopTime) // TODO: startTime + stopTime is a temp work around. Find a way to stop mobile youtube at a specific point.
                    ));

        }
    }

    // Launches YouTube to the given connection
    public void launchYT(YouTube youTube) {
        Intent openWebPage = new Intent(Intent.ACTION_VIEW); //Creates new intent to open a new activity/screen
        openWebPage.setData(Uri.parse(youTube.getUrl())); //sets the target of the new screen to the given youtube link

        //Safely checks if activity will resolve correctly
        if (openWebPage.resolveActivity(getPackageManager()) != null) {
            startActivity(openWebPage);
        } else{
            EditText editTextURL = findViewById(R.id.address);
//            Toast.makeText(this, "Unusable YouTube url.", Toast.LENGTH_LONG).show();
            editTextURL.setHint(R.string.youtube_url_error);
        }
    }
}
