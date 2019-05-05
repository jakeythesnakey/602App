package com.example.a602app;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.a602app.MusicPlayer;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import static com.example.a602app.MusicPlayer.SONG_PLAYING;

public class KobeActivity extends AppCompatActivity {

    /**
     * @param playbtn
     * @param positionBar
     * @param volumeBar
     * @param elapsedTiemLabel
     * @param remainingTimeLabel
     * @param mp
     */
    Button playBtn;
    SeekBar positionBar;
    SeekBar volumeBar;
    TextView elapsedTimeLabel;
    TextView remainingTimeLabel;
    MediaPlayer mp;
    int totalTime;

    private Button button;

    public String music = SONG_PLAYING;

    /**
     * press back and stops music player
     */
    public void onBackPressed() {
        super.onBackPressed();
        mp.stop();
    }

    @Override
    /**
     *  @param savedInstanceState
     *  set content view activity xml file
     */
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kobe);

        playBtn = (Button) findViewById(R.id.playBtn);
        elapsedTimeLabel = (TextView) findViewById(R.id.elapsedTimeLabel);
        remainingTimeLabel = (TextView) findViewById(R.id.remainingTimeLabel);



        TextView textView;
        textView = findViewById(R.id.textViewName);
        textView.setText(SONG_PLAYING);





        // Media Player
        String s = music;
//        String ss = "R.raw."+s;
//        MediaStore.Audio.Media m = new MediaStore.Audio.Media(new File(s).toURI());

//        URI uri = new File(ss).toURI();

//        Uri uri1 = Uri.parse(ss);
        System.out.println("-----------------the uri is ::::::::::::::::"+s+":::::::::::::::::::::::::");

//play selected music
        if(s==null){
            s = "hero";
        }
        if(s.equals("hero")){
            mp = MediaPlayer.create(this,R.raw.hero);
        }else if(s.equals("fresh_eyes")){
            mp = MediaPlayer.create(this, R.raw.fresh_eyes);
        }else if(s.equals("love_yourself")){
            mp=MediaPlayer.create(this,R.raw.love_yourself);
        }

//        mp= MediaPlayer.create(this,uri1);
//        mp = MediaPlayer.create(this,R.raw.hero);

//set the default time volume
        mp.setLooping(true);
        mp.seekTo(1);
        mp.setVolume(0.5f, 0.5f);
        totalTime = mp.getDuration();

        // Position Bar
        positionBar = (SeekBar) findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    /**
                     *
                     * @param seekBar
                     * @param progress
                     * @param fromUser
                     */
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (fromUser) {
                            mp.seekTo(progress);
                            positionBar.setProgress(progress);
                        }
                    }

                    /**
                     *
                     * @param seekBar
                     */
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    /**
                     *
                     * @param seekBar
                     */
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );


        // Volume Bar
        volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        volumeBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    /**
                     *
                     * @param seekBar
                     * @param progress
                     * @param fromUser
                     */
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        float volumeNum = progress / 100f;
                        mp.setVolume(volumeNum, volumeNum);
                    }


                    /**
                     *
                     * @param seekBar
                     */
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    /**
                     *
                     * @param seekBar
                     */
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        /**
         * a new thread
         */
        // Thread (Update positionBar & timeLabel)
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp != null) {
                    try {
                        Message msg = new Message();
                        msg.what = mp.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();

        button = (Button)findViewById(R.id.buttonList);

        button.setOnClickListener(new View.OnClickListener() {

            /**
             *
             * @param v
             * to pause the music if it is playing
             */
            @Override
            public void onClick(View v) {
                openSongsList();
                if(mp.isPlaying()) mp.pause();
            }
        });

    }


    public void openSongsList(){
        Intent intent = new Intent(this, MusicPlayer.class);
        startActivity(intent);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            // Update positionBar.
            positionBar.setProgress(currentPosition);

            // Update Labels.
            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = createTimeLabel(totalTime-currentPosition);
            remainingTimeLabel.setText("- " + remainingTime);
        }
    };

    public String createTimeLabel(int time) {
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    public void playBtnClick(View view) {

        if (!mp.isPlaying()) {
            // Stopping
            mp.start();
            playBtn.setBackgroundResource(R.drawable.stop);

        } else {
            // Playing
            mp.pause();
            playBtn.setBackgroundResource(R.drawable.play);
        }

    }




}