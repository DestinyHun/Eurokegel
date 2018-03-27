package com.example.win_10.eurokegel;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class Constants {
    static String PlayerOne = "";
    static String PlayerTwo = "";
    static String BeginnerPlayer = "";
    static int PlayerOnePoints = 0;
    static int PlayerTwoPoints = 0;
    static Boolean AddPoint = true;
    static int PointToAdd = 0;
    static int PointToAddPlayer = 0;
    static float DefaultTextSizeNormal = -1;
    static float DefaultTextSizeLarge = -1;
    static float DefaultTextSizeHuge = -1;
    static int GamePointLimit = 120;
    static TextToSpeech Tts;              // TTS engine
    static boolean SpeechReady = false;


    public static void SetDefaultTextSizes(TextView tv, Resources resources)
    {
        float sourceTextSize = tv.getTextSize();
        DefaultTextSizeNormal = sourceTextSize / resources.getDisplayMetrics().density;

        DefaultTextSizeLarge = DefaultTextSizeNormal * 1.8f;
        DefaultTextSizeHuge = DefaultTextSizeNormal * 6;
    }

    public static void InitSounds(Context context) {
        Tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                SpeechReady = true;
            }
        });
    }
}
