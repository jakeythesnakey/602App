package com.example.a602app.Sounds;

import android.content.Intent;
import android.util.Log;

public class YouTube extends Sound {
    // Link maker
    private String makeUrl(String url) {
        // Basic step to tidy the url of extra whitespace
        url = url.trim();

        // Add watch?v= if the user is using a short link
        if (!url.contains("watch?v=") && !url.contains("embed/")) {
            url = "watch?v=" + url;
        }

        // Add youtube.com/ if the user is using a short link
        if (!url.contains("youtube.com/")) {
            url = "youtube.com/" + url;
        }

        // Add www. if the user is using a short link
        if (!url.contains("www.") && !url.contains("m.")) {
            url = "www." + url;
        }

        // Add https:// if the user is using a short link
        if (!url.contains("https://")) {
            url = "https://" + url;
        }

        // Add time data if the user entered times
        if (!url.contains("t=") && this.timeStamp.hasStartTime()) {
            if (!url.endsWith("&") && !url.endsWith("?")) {
                url = url + "&";
            }

            url = url + "t=";

            if (this.timeStamp.getHours() > 0) { url = url + this.timeStamp.getHours() + "h"; }
            if (this.timeStamp.getMinutes() > 0) { url = url + this.timeStamp.getMinutes() + "m"; }
            if (this.timeStamp.getSeconds() > 0) { url = url + this.timeStamp.getSeconds() + "s"; }
        }

        return url;
    }

    // Old url maker. Doesn't work for mobile :|
    private String makeOldUrl(String url) {
        // Basic step to tidy the url of extra whitespace
        url = url.trim();
        Log.d("url", "url.trim()" + url);

        // Add watch?v= if the user is using a short link
        if (!url.contains("watch?v=") && !url.contains("embed/")) {
            url = "embed/" + url;
            Log.d("url", "watch?v=" + url);
        }

        // Add youtube.com/ if the user is using a short link
        if (!url.contains("youtube.com/")) {
            url = "youtube.com/" + url;
            Log.d("url", "youtube.com/" + url);
        }

        // Add www. if the user is using a short link
        if (!url.contains("www.") && !url.contains("m.")) {
            url = "www." + url;
            Log.d("url", "www." + url);
        }

        // Add https:// if the user is using a short link
        if (!url.contains("https://")) {
            url = "https://" + url;
            Log.d("url", "https://" + url);
        }

        // Add time data if the user entered times
        if (this.timeStamp.usedTime()) {
            if (!url.contains("?")) {
                url = url + "?";
                Log.d("url", "url.trim() " + url);
            }

            if (this.timeStamp.hasStartTime()) {
                if (!url.endsWith("&") && !url.endsWith("?")) { url = url + "&"; }
                url = url + "start=" + this.timeStamp.getStartTime();
                Log.d("url", "start=" + url);
            }

            if (this.timeStamp.hasStopTime()) {
                if (!url.endsWith("&") && !url.endsWith("?")) { url = url + "&"; }
                url = url + "end=" + this.timeStamp.getStopTime();
                Log.d("url", "end=" + url);
            }
        }

        Log.d("url", "final url= " + url);
        return url;
    }

    public YouTube(String url, TimeStamp timeStamp) {
        super(MEDIATYPE.YOUTUBE, url, timeStamp);
        this.reference = makeUrl(url);
    }

    // Class specific get
    public String getUrl() {
        return this.reference;
    }

    @Override
    public String getIntentExtra() {
        return this.reference
                + "," + this.timeStamp.getMinutes()
                + "," + this.timeStamp.getStopTime();
    }
}
