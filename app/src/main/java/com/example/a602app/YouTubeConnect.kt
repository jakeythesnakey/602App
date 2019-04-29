package com.example.a602app

//This class creates and holds a timestamped youtube video link
class YouTubeConnect constructor(var _url: String, var _timeStamp: TimeStamp) {

    var url: String = "https://www.google.com/"
    var timeStamp = TimeStamp(0, 0)

    init {
        this.url = _url
        this.timeStamp = _timeStamp
        this.url = this.url + "&t=" + this.timeStamp.minutes + "m" + this.timeStamp.seconds + "s"
        print(this.url)
    }

}