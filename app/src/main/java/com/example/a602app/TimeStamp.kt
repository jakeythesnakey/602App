package com.example.a602app
//This class holds timestamp information for media, e.g. youtube video or spotify audio
class TimeStamp constructor(var _minutes : Int = 0, var _seconds : Int = 0)
{
    var minutes : Int = 0
    var seconds : Int = 0

    init {
        this.minutes = _minutes
        this.seconds = _seconds
    }

    fun setTime( _mins : Int,  _secs : Int)
    {
        this.minutes = _mins
        this.seconds = _secs

    }
}