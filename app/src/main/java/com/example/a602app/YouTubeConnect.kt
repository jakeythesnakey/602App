package com.example.a602app

//This class creates and holds a timestamped youtube video link
class YouTubeConnect constructor(var _url: String, var _timeStamp: TimeStamp = TimeStamp(0,0)) {

    //Secondary constructor. Allows user to input integers instead of a TimeStamp object, dynamically creating the timestamp
    constructor(newlr: String, mins: Int = 0, secs: Int = 0): this(newlr,  TimeStamp(mins, secs))

    //Default url is google.com
    var url: String = "https://www.google.com/"
    //Default timestamp: 0m0s
    var timeStamp = TimeStamp(0, 0)

    //code that runs after cunstructor. Sets members to inputs and sets up youtube link
    init {
        this.url = _url
        this.timeStamp = _timeStamp

        //determines whether youtube link is a full address or a shortlink and applies syntax correctly
        var connector = "&t="
        if (!url.contains("?"))
        {
            connector = "?t="
        }
        //constructs link
        this.url = this.url + connector + this.timeStamp.minutes + "m" + this.timeStamp.seconds + "s"
    }

}