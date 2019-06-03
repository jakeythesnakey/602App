package com.example.a602app;

import java.io.*;
import java.util.ArrayList;
import android.util.Log;
import android.widget.Toast;
import com.example.a602app.Sounds.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIO {
    // Button save list
    private static final int[] BUTTONIDS = {
            R.id.mediabutton1, R.id.mediabutton2, R.id.mediabutton3,
            R.id.mediabutton4, R.id.mediabutton5, R.id.mediabutton6,
            R.id.mediabutton7, R.id.mediabutton8, R.id.mediabutton9,
    };
    private static final Sound[] BUTTON_ARRAY = new Sound[BUTTONIDS.length];

    // YouTube save list
    private static final ArrayList<YouTube> YOUTUBE_ARRAY_LIST = new ArrayList<>(); // Stored list so things aren't re-loaded for no reason

    // Generic save/load variables
    private static final String FILENAME = "saves.txt";
    private static boolean loaded = false; // Flag so we don't try and load multiple times
    private static BufferedReader br = null;
    private static BufferedWriter bw = null;

    // Basic thing that checks if something is in the buttons array. TODO: Save buttons in a separate file
    private static boolean isButton(String reference) {
        for (Sound sound : BUTTON_ARRAY) {
            if (reference.equals(sound.getReference())) {
                return true;
            }
        }

        return false;
    }

    // Returns the save index of a button or -1 if it isn't a button.
    private static int getButtonSaveIndex(String reference) {
        for (int i = 0; i < BUTTON_ARRAY.length; i++) {
            if (reference.equals(BUTTON_ARRAY[i].getReference())) {
                return i;
            }
        }

        return -1;
    }

    // Returns a save string of a given sound
    private static String makeSaveFromSound(Sound sound) {
        // File structure for <MEDIATYPE>,<is_button boolean>,<url string>,<start_time int>,<stop_time int>
        switch (sound.getMediaType()) {
            case LOCAL:
                return sound.getMediaType().toString()
                        + "," + isButton(sound.getReference())
                        + "," + sound.getReference();
            case YOUTUBE:
                return sound.getMediaType().toString()
                        + "," + isButton(sound.getReference())
                        + "," + sound.getReference()
                        + "," + sound.getTimeStamp().getStartTime()
                        + "," + sound.getTimeStamp().getStopTime();
            default:
                return MEDIATYPE.UNKNOWN.toString()
                        + "," + isButton(sound.getReference())
                        + "," + sound.getReference()
                        + "," + sound.getTimeStamp().getStartTime()
                        + "," + sound.getTimeStamp().getStopTime();
        }
    }

    // Returns a Sound class for a given string
    private static Sound makeSoundFromSave(String save) {
        // File structure for <MEDIATYPE>,<is_button boolean>,<url string>,<start_time int>,<stop_time int>
        String[] parts = save.split(",");

        if (parts.length == 0) {
            return new Sound();
        } else if (parts[0].equals(MEDIATYPE.LOCAL.toString())) {
            return new Local(parts[2], new TimeStamp());
        } else if (parts[0].equals(MEDIATYPE.YOUTUBE.toString())) {
            return new Local(parts[2], new TimeStamp(Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
        } else {
            return new Sound();
        }
    }

    // Overrides the save file to be empty
    private static void deleteContents() {
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(FILENAME);
        } catch (FileNotFoundException ignored) {

        } finally { // Closing the writer
            if(pw != null){
                pw.close();
            }
        }
    }

    // Deletes any existing saved files. Old name cause I'm too lazy to update references.
    private static void deleteSaves() {
        deleteContents();
    }

    // Loads all saved URLs. Will only run once for unless reloadURLs is called.
    private static void loadURLs() {
        if (loaded) { return; }
        loaded = true; // Regardless of weather we succeed or fail, we have tried to load.
        FileInputStream fis = null;
        try { // File loading
//            br = new BufferedReader(new InputStreamReader(new FileInputStream(FILENAME)));
            fis = new FileInputStream(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            String line;
            MEDIATYPE mediatype;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                try {
                    mediatype = MEDIATYPE.valueOf(parts[0]);
                } catch(IllegalArgumentException | NullPointerException ex) {
                    mediatype = MEDIATYPE.UNKNOWN;
                }

                // Deal with it being a button
                if (Integer.parseInt(parts[1])>= 0) {
                    BUTTON_ARRAY[Integer.parseInt(parts[1])] = makeSoundFromSave(line);
                }

                if (mediatype == MEDIATYPE.YOUTUBE) {
                    YOUTUBE_ARRAY_LIST.add(
                            new YouTube(parts[1],
                            new TimeStamp(
                                    Integer.parseInt(parts[2]),
                                    Integer.parseInt(parts[3]))
                            )
                    );
                }
            }
        } catch (IOException ex) {
            Log.d("Error", "File io error");
        } finally{ // Closing the reader
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Log.d("Error", "Buffered reader closing error");
                }
            }
        }
    }

    // Returns true if there is no saved url for the given url
    private static boolean isNotSaved(String reference) {
        if (!loaded) { loadURLs(); }

        for (Sound sound : BUTTON_ARRAY) {
            if (reference.equals(sound.getReference())) {
                return false;
            }
        }

        for (Sound sound : YOUTUBE_ARRAY_LIST) {
            if (reference.equals(sound.getReference())) {
                return false;
            }
        }

        return true;
    }

    // Force reloads file urls. For testing if needed.
    public static void reloadURLs() {
        for (int i = 0; i < BUTTON_ARRAY.length; i++) {
            BUTTON_ARRAY[i] = null;
        }
        YOUTUBE_ARRAY_LIST.clear();
        loaded = false;
        loadURLs();
    }

    public static Sound[] getButtonSaves() {
        return BUTTON_ARRAY;
    }

    // Returns a list of all YouTube URLs
    public static ArrayList<YouTube> getYouTubeSaves() {
        if (!loaded) { loadURLs(); }
        return YOUTUBE_ARRAY_LIST;
    }

    // Saves a given YouTube activity
    public static void saveSound(Sound sound, int buttonIndex) {
        if (!loaded) { loadURLs(); }

        // TODO: separate button and youtube saving
        if (buttonIndex >= 0 && buttonIndex <= BUTTON_ARRAY.length) {
            BUTTON_ARRAY[buttonIndex] = sound;
        }

        FileOutputStream fos = null;

        try {
//            if (isNotSaved(sound.getReference())) { // If it's an unknown URL, append a new line
//                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILENAME)));
//                bw = new BufferedWriter(new FileWriter(FILENAME, true));
//                bw.write(makeSaveFromSound(sound));
//            } else { // If it's a known URL, re-write the whole file to update the new info
                deleteSaves();
                fos = new FileOutputStream(FILENAME);

                for (Sound _sound : BUTTON_ARRAY) {
                    fos.write(makeSaveFromSound(_sound).getBytes());
                }

//                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILENAME)));
//                bw = new BufferedWriter(new FileWriter(FILENAME, true));

//                for (Sound _sound : YOUTUBE_ARRAY_LIST) {
//                    if (sound.getMediaType() == MEDIATYPE.YOUTUBE && sound.getReference().equals(_sound.getReference())) { // Update the saved URL
//                        // noinspection SuspiciousMethodCalls
//                        YOUTUBE_ARRAY_LIST.set(YOUTUBE_ARRAY_LIST.indexOf(_sound), // We're putting something else in the index immediately so it doesn't matter.
//                                new YouTube(sound.getReference(), sound.getTimeStamp())
//                        );
//                    }
//
//                    bw.write(makeSaveFromSound(sound));
//                }
//            }
        } catch (IOException ex) {
            Log.d("Error", "We couldn't write the save file.");
        } finally{ // Closing the writer
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                Log.d("Error", "Couldn't close the file output stream.");
            }

            try {
                if(bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                Log.d("Error", "Couldn't close buffered writer");
            }
        }
    }

    // TODO: refine this
    public static void saveSound(Sound sound) {
        saveSound(sound, -1);
    }

    // Deletes all saved classes with the given url
    public static void deleteSoundFromString(String reference) {
        if (!loaded) { loadURLs(); }
        if (isNotSaved(reference)) { return; }

        try {
            deleteSaves();
//            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILENAME)));
//            bw = new BufferedWriter(new FileWriter(FILENAME, true));

            for (YouTube youTube : YOUTUBE_ARRAY_LIST) {
                if (reference.equals(youTube.getUrl())) { // Update the saved URL
                    YOUTUBE_ARRAY_LIST.remove(youTube);
                } else {
                    bw.write(makeSaveFromSound(youTube));
                }
            }
        } catch (IOException ex) {
            Log.d("Error", "We couldn't write the save file.");
        } finally{ // Closing the writer
            try {
                if(bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                Log.d("Error", "Couldn't close buffered writer");
            }
        }
    }

    // Passes a YouTubeConnect class on to be deleted
    public static void deleteSound(Sound sound) {
        deleteSoundFromString(sound.getReference());
    }

    static Sound getButtonMediaStore(int buttonIndex) {
        if (!loaded) { loadURLs(); }
        return BUTTON_ARRAY[buttonIndex];
    }

    public static int[] getButtonIDArray() {
        return BUTTONIDS;
    }

    // TODO: This is a temp solution. Make this refined (store YouTube in BUTTON_ARRAY)
    public static YouTube getButtonYoutTubeFromID(int id) {
        if (!loaded) { loadURLs(); }

        for (int i = 0; i < BUTTONIDS.length; i++) {
            if (id == BUTTONIDS[i]) {
                if (BUTTON_ARRAY[i] != null) {
                    return new YouTube(
                            BUTTON_ARRAY[i].getReference(),
                            BUTTON_ARRAY[i].getTimeStamp()
                    );
                }

                return null;
            }
        }

        return null;
    }
}