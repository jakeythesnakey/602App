package com.example.a602app;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MusicPlayer extends AppCompatActivity {


    /**
     * listView
     * arrayList
     * arrayAdapter
     * mediaPlayer
     * SONG_PLAYING
     */
    ListView listView;
    ArrayList<String> arrayList;

    ArrayAdapter arrayAdapter;
    MediaPlayer mediaPlayer;

    public static String SONG_PLAYING;

    /**
     *
     * @param savedInstanceState
     * get all the songs listed
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<String>();
        Field[]fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++) {
            arrayList.add(fields[i].getName());
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             *
             * @param parent
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {


                SONG_PLAYING = arrayList.get(i);

                openPlayerMedia();
//                System.out.println(SONG_PLAYING);



//                if(mediaPlayer != null){
//                    mediaPlayer.release();
//                }
//
//                int resId = getResources().getIdentifier(arrayList.get(i),"raw",getPackageName());
//                mediaPlayer = MediaPlayer.create(MusicPlayer.this,resId);
//                mediaPlayer.start();
            }
        });


    }

    /**
     *start activity
     */
    public void openPlayerMedia(){
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }


    /**
     * stops music when push back
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
    }
}
