package com.example.a602app.Sounds;

public class Sound {
    String reference;
    TimeStamp timeStamp;
    MEDIATYPE mediaType;

    public Sound(MEDIATYPE mediatype, String reference, TimeStamp timeStamp) {
        this.mediaType = mediatype;
        this.reference = reference;
        this.timeStamp = timeStamp;
    }

    public Sound() {
        this(MEDIATYPE.UNKNOWN, "", new TimeStamp());
    }

    // Get the reference
    public String getReference() {
        return this.reference;
    }

    public MEDIATYPE getMediaType() {
        return this.mediaType;
    }

    // Get the timeStamp
    public TimeStamp getTimeStamp() {
        return this.timeStamp;
    }

    // Extra data for auto-playing the sound
    String getIntentExtra() {
        return "";
    }
}
