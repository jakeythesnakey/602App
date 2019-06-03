package com.example.a602app.Sounds;

public class Local extends Sound {

    public Local(String filePath, TimeStamp timeStamp) {
        super(MEDIATYPE.LOCAL, filePath, timeStamp);
    }

    public String getFilePath() {
        return this.reference;
    }

    @Override
    public String getIntentExtra() {
        return this.reference
                + "," + this.timeStamp.getStartTime()
                + "," + this.timeStamp.getStopTime();
    }
}
