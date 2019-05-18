package com.example.a602app

//This class holds timestamp information for media, e.g. youtube video or spotify audio
class TimeStamp constructor(var _minutes : Int = 0, var _seconds : Int = 0)
{
    //set defaults
    var minutes : Int = 0
    var seconds : Int = 0

    //set members to user input
    init {
        this.minutes = _minutes
        this.seconds = _seconds
    }

    //This method allows custom access to change timestamp
    fun setTime( _mins : Int,  _secs : Int)
    {
        this.minutes = _mins
        this.seconds = _secs

    }

    override fun toString(): String
    {
        return (this.minutes.toString() + "," + this.seconds.toString())
    }
}