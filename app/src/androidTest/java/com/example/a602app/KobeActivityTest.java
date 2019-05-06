package com.example.a602app;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)

public class KobeActivityTest {

    @Rule
    public ActivityTestRule<KobeActivity> kobeActivityTestRule = new ActivityTestRule<KobeActivity>(KobeActivity.class);
    private KobeActivity kobeActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(MusicPlayer.class.getName(),null,false);


    @Before
    public void setUp() throws Exception {
        kobeActivity = kobeActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfClick(){
        assertNotNull(kobeActivity.findViewById(R.id.textViewName));
        onView(withId(R.id.textViewName)).perform(click());

        getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        Activity musicplayer =  getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(musicplayer);
        musicplayer.finish();

    }



    @After
    public void tearDown() throws Exception {
        kobeActivity = null;
    }

//    @Test
//    public void onBackPressed() {
//    }
//
//    @Test
//    public void onCreate() {
//    }
//
//    @Test
//    public void openSongsList() {
//    }
//
//    @Test
//    public void createTimeLabel() {
//    }
//
//    @Test
//    public void playBtnClick() {
//    }
}