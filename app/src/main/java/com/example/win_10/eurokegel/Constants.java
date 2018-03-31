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
    static float DefaultTextSizeLightLarge = -1;
    static float DefaultTextSizeLarge = -1;
    static float DefaultTextSizeGreat = -1;
    static float DefaultTextSizeHuge = -1;
    static int GamePointLimit = 120;
    static TextToSpeech Tts;              // TTS engine
    static boolean SpeechReady = false;
    static int playerOneSetWins = 0;
    static int playerTwoSetWins = 0;
    static String PointHistory = "<font color=#D5D5E4>22</font>";

    public static void SetDefaultTextSizes(TextView tv, Resources resources)
    {
        float sourceTextSize = tv.getTextSize();
        DefaultTextSizeNormal = sourceTextSize / resources.getDisplayMetrics().density;

        DefaultTextSizeLightLarge = DefaultTextSizeNormal * 1.3f;
        DefaultTextSizeLarge = DefaultTextSizeNormal * 1.8f;
        DefaultTextSizeGreat = DefaultTextSizeNormal * 3f;
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
