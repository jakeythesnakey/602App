package com.example.a602app

//This class creates and holds a timestamped youtube video link
class YouTubeConnect constructor(var _url: String, var _timeStamp: TimeStamp = TimeStamp(0,0)) {

    constructor(newlr: String, secs: Int = 0, mins: Int = 0): this(newlr,  TimeStamp(secs, mins))

    var url: String = "https://www.google.com/"
    var timeStamp = TimeStamp(0, 0)



    init {
        this.url = _url
        this.timeStamp = _timeStamp

        var connector = "&t="
        if (!url.contains("?"))
        {
            connector = "?t="
        }
        this.url = this.url + connector + this.timeStamp.minutes + "m" + this.timeStamp.seconds + "s"
        print(this.url)
    }

}