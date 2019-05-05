package com.example.a602app

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TimeStampTest {

    @Before
    fun setUp()
    {
    }

    @After
    fun tearDown()
    {
    }

    @Test
    fun setTime() //Tests whether the setTime function works
    {
        val time = TimeStamp(0,0)

        time.setTime(11,10)

        assertEquals(time.minutes, 11)
        assertEquals(time.seconds, 10)
    }
}