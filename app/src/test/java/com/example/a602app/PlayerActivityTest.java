package com.example.a602app;

import org.junit.Test;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;

public class PlayerActivityTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void openSongsList() {

    }

    @Test
    public void createTimeLabelTest() {
        PlayerActivity ka = new PlayerActivity();
        String testTime = ka.createTimeLabel(120000);

        String expectedTime = "2:00";
        assertEquals(expectedTime,testTime);
    }

    @Test
    public void playBtnClick() {

    }

//
//    @Test
//    public void createTimeLabelTest() {
//        PlayerActivity ka = new PlayerActivity();
//        String testTime = ka.createTimeLabel(120000);
//
//        String expectedTime = "2:00";
//        assertEquals(expectedTime,testTime);
//    }

}