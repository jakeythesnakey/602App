package com.example.a602app

/**
 * This class holds timestamp information for media, e.g. youtube video or local audio
 *
 * @property  _minutes user inputted minutes
 * @property  _seconds duser inputted seconds
 *
 * @author Jake Kampkes.
 * @since 23/4/19
 */
class TimeStamp constructor(var _minutes : Int = 0, var _seconds : Int = 0)
{
    /**
     * Sets default minutes to 0.
     * */
    var minutes : Int = 0
    /**
     * Sets seconds minutes to 0.
     */
    var seconds : Int = 0

    /**
     * Set members to user input
     */
    init {
        this.minutes = _minutes
        this.seconds = _seconds
    }

    /**
     * This method allows custom access to change timestamp
     */
    fun setTime( _mins : Int,  _secs : Int)
    {
        this.minutes = _mins
        this.seconds = _secs

    }

    /**
     * turns a TimeStamp object into a string
     */
    override fun toString(): String
    {
        return (this.minutes.toString() + "," + this.seconds.toString())
    }
}