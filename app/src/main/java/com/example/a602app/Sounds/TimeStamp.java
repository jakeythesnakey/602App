package com.example.a602app.Sounds;

public class TimeStamp {
    private int startTime; // Time is stored as seconds
    private int stopTime; // Time is stored as seconds

    public TimeStamp(int startTime, int stopTime) {
        this.startTime = startTime;
        this.stopTime = stopTime;

        // Double checking for invalid time stamps
        if (this.startTime <= 0) {
            this.startTime = -1;
        }
        if (this.stopTime <= 0 || this.stopTime <= this.startTime) {
            this.stopTime = -1;
        }
    }

    public TimeStamp(int startTime) {
        this(startTime, -1);
    }

    public TimeStamp() {
        this(-1, -1);
    }

    // Returns if there's a valid start time
    public boolean hasStartTime() {
        return this.startTime >= 0;
    }

    // Returns if there's a valid stop time
    public boolean hasStopTime() {
        return this.stopTime >= 0;
    }

    // Returns if the time stamp is used
    public boolean usedTime() {
        return hasStartTime() || hasStartTime();
    }

    // Returns the start time
    public int getStartTime() {
        return this.startTime;
    }

    // Returns the stop time
    public int getStopTime() {
        return this.stopTime;
    }

    // Returns the number of hours in the start time
    public int getHours() {
        return this.startTime / 3600;
    }

    // Returns the number of minutes in the start time
    public int getMinutes() {
        return (this.startTime % 3600) / 60;
    }

    // Returns the number of seconds in the start time
    public int getSeconds() {
        return (this.startTime % 3600) % 60;
    }
}
