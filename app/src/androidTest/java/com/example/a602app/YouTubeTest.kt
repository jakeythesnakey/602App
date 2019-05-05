package com.example.a602app

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.util.Log

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class YouTubeTest {

    @Rule
    @JvmField
    val rule: ActivityTestRule<YouTubeActivity> = ActivityTestRule(YouTubeActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.a602app", appContext.packageName)
    }

    @Test
    fun user_type_link()
    {
        onView(withId(R.id.address)).perform(typeText("https://youtu.be/48uAQYf3Uhc"))
    }

    @Test
    fun user_type_mins()
    {
        onView(withId(R.id.minutes)).perform(typeText("0"))
    }

    @Test
    fun user_type_secs()
    {
        onView(withId(R.id.seconds)).perform(typeText("10"))
    }

    @Test
    fun test_Launch_YouTube()
    {
        onView(withId(R.id.address)).perform(typeText("https://youtu.be/48uAQYf3Uhc"))
        onView(withId(R.id.minutes)).perform(typeText("0"))
        onView(withId(R.id.seconds)).perform(typeText("10"))

        onView(withId(R.id.buttonLaunch)).perform(click())
    }
}
