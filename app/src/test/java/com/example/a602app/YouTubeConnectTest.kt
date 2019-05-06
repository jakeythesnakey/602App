package com.example.a602app

import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class YouTubeConnectTest {

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun linkConstruct()//tests whether links construct correctly
    {
        val ytc = YouTubeConnect("https://youtu.be/48uAQYf3Uhc", 8,2)

        assertEquals(ytc.url,"https://youtu.be/48uAQYf3Uhc?t=8m2s")

    }
}