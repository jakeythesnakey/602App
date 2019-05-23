package com.example.a602app

/**
 * This class creates and holds a timestamped youtube video link
 * and methods to create a complex link from passed-in variables
 *
 * @property  _url url of desired youtube video
 * @property  _timeStamp desired timestamp user wishes to open the video to
 *
 * @author Jake Kampkes.
 * @since 23/4/19
 */
class YouTubeConnect constructor(var _url: String, var _timeStamp: TimeStamp = TimeStamp(0,0)): MediaHolder(1) {

    /**
     * Secondary constructor. Allows user to input integers instead of a TimeStamp object, dynamically creating the timestamp
     *
     * @param newrl url of desired youtube video
     * @param mins minutes the user desires to open the video to
     * @param secs seconds the user desires to open the video to
     */
    constructor(newrl: String, mins: Int = 0, secs: Int = 0): this(newrl,  TimeStamp(mins, secs))

    /**
     * variable to hold complex URL. Default url is google.com
     * */
    var url: String = "https://www.google.com/"
    /**
     * variable to hold timestamp. Default time is 0 minutes, 0 seconds
     * */
    var timeStamp = TimeStamp(0, 0)

    /**
     * code that runs after constructor. Sets members to inputs and sets up complex youtube link
     */
    init {
        this.url = _url //sets URL variable to inputted URL
        this.timeStamp = _timeStamp //sets timestamp variable to inputted timestamp

        linkConstruct()//launches link constructor
    }

    /**
     * Function takes a youtube link and a timestamp and turns it into a complex link
     * calling the new url results in starting the youtube video at the given timestamp
     */
    fun linkConstruct()
    {
        var connector = "&t="//creates variable that holds standard sting that connects youtube link to a timestamp

        if (!url.contains("?")) //determines whether youtube link is a full address or a shortlink and applies syntax correctly
        {
            connector = "?t=" //sets connector to shortlink connector
        }
        //constructs link
        this.url = this.url + connector + this.timeStamp.minutes + "m" + this.timeStamp.seconds + "s"
    }

    /**
     * turns a YouTubeConnect object into a string
     */
    override fun toString(): String
    {
        return (this.url + "," + this.timeStamp.toString())
    }
}