package com.example.win_10.eurokegel;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
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
    static float DefaultTextSizeNormal = -1;
    static float DefaultTextSizeLarge = -1;
    static float DefaultTextSizeHuge = -1;
    static int GamePointLimit = 120;
    static Map<String, ArrayList<MediaPlayer>> writerSays = new HashMap<String, ArrayList<MediaPlayer>>();
    static Map<String, MediaPlayer> standardSpeaks = new HashMap<String, MediaPlayer>();
    static int PlayedSoundNumber = 0;
    static ArrayList<MediaPlayer> ActualMediaPlayers;

    public static void SetDefaultTextSizes(TextView tv, Resources resources)
    {
        float sourceTextSize = tv.getTextSize();
        DefaultTextSizeNormal = sourceTextSize / resources.getDisplayMetrics().density;

        DefaultTextSizeLarge = DefaultTextSizeNormal * 1.8f;
        DefaultTextSizeHuge = DefaultTextSizeNormal * 6;
    }

    public static void InitSounds(Context context)
    {
        standardSpeaks.put("kella",MediaPlayer.create(context,getSoundIdentifier( "kella", "raw", context.getPackageName(), context)));
        standardSpeaks.put("sotetnek",MediaPlayer.create(context,getSoundIdentifier( "sotetnek", "raw", context.getPackageName(), context)));
        standardSpeaks.put("fehernek",MediaPlayer.create(context,getSoundIdentifier( "fehernek",  "raw", context.getPackageName(), context)));
        standardSpeaks.put("sotet",MediaPlayer.create(context,getSoundIdentifier( "sotet",  "raw", context.getPackageName(), context)));
        standardSpeaks.put("feher",MediaPlayer.create(context,getSoundIdentifier( "feher",  "raw", context.getPackageName(), context)));
        standardSpeaks.put("ujelso",MediaPlayer.create(context,getSoundIdentifier( "ujelso",  "raw", context.getPackageName(), context)));
        standardSpeaks.put("nyerta",MediaPlayer.create(context,getSoundIdentifier( "nyerta",  "raw", context.getPackageName(), context)));
        standardSpeaks.put("vezeta",MediaPlayer.create(context,getSoundIdentifier( "vezeta",  "raw", context.getPackageName(), context)));
        standardSpeaks.put("meg",MediaPlayer.create(context,getSoundIdentifier( "meg", "raw",  context.getPackageName(), context)));

        for (int i = 0; i < 13; i++) {
            if (i < 10) {
                standardSpeaks.put(String.format(Locale.ENGLISH, "%d", i), MediaPlayer.create(context, getSoundIdentifier(String.format(Locale.ENGLISH, "%d", i), "raw", context.getPackageName(), context)));
                standardSpeaks.put(String.format(Locale.ENGLISH, "%d", i) + "el", MediaPlayer.create(context, getSoundIdentifier(String.format(Locale.ENGLISH, "%d", i) + "el", "raw", context.getPackageName(), context)));
            }
            if (i > 0) {
                standardSpeaks.put(String.format(Locale.ENGLISH, "%d", i) + "0", MediaPlayer.create(context, getSoundIdentifier(String.format(Locale.ENGLISH, "%d", i) + "0", "raw", context.getPackageName(), context)));
                standardSpeaks.put(String.format(Locale.ENGLISH, "%d", i) + "0d", MediaPlayer.create(context, getSoundIdentifier(String.format(Locale.ENGLISH, "%d", i) + "0d", "raw", context.getPackageName(), context)));
                standardSpeaks.put(String.format(Locale.ENGLISH, "%d", i) + "0el", MediaPlayer.create(context, getSoundIdentifier(String.format(Locale.ENGLISH, "%d", i) + "0el", "raw", context.getPackageName(), context)));
            }
        }

        for (int i = 122;i > -1;i--)
        {
            String numberString = String.format(Locale.ENGLISH, "%d",i);
            char[] numberCharArray = numberString.toCharArray();

            //Még kella sotettnek
            if (numberCharArray.length == 1)
                writerSays.put(numberString + "kella" + "sotetnek",new ArrayList<>(Arrays.asList(
                        Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( numberString  + ""),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("sotetnek"))));
                else if (numberCharArray.length == 2 && numberCharArray[1] == '0')
             writerSays.put(numberString + "kella" + "sotetnek", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( "" + numberCharArray[0] + "0d" + ""),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("sotetnek"))));
                else if (numberCharArray.length == 2)
             writerSays.put(numberString + "kella" + "sotetnek", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( "" + numberCharArray[0] + "0" + ""),
            GetMediaPlayerByName( numberCharArray[1]  + ""),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("sotetnek"))));
                else if (numberCharArray.length == 3 && numberCharArray[1] == '0' && numberCharArray[2] == '0')
             writerSays.put(numberString + "kella" + "sotetnek", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0d" + ""),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("sotetnek"))));
                else if (numberCharArray.length == 3 && numberCharArray[2] == '0')
             writerSays.put(numberString + "kella" + "sotetnek", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0d" + ""),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("sotetnek"))));
                else if (numberCharArray.length == 3)
             writerSays.put(numberString + "kella" + "sotetnek", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + ""),
            GetMediaPlayerByName( "" + numberCharArray[2] + ""),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("sotetnek"))));

            //meg kella fehernek
            if (numberCharArray.length == 1)
                 writerSays.put(numberString + "kella" + "fehernek", new ArrayList<>(Arrays.asList(
                        Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( numberString  + ""),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("fehernek"))));
                else if (numberCharArray.length == 2 && numberCharArray[1] == '0')
             writerSays.put(numberString + "kella" + "fehernek", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( "" + numberCharArray[0] + "0" + "d"),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("fehernek"))));
                else if (numberCharArray.length == 2)
             writerSays.put(numberString + "kella" + "fehernek", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( "" + numberCharArray[0] + "0" + ""),
            GetMediaPlayerByName( numberCharArray[1]  + ""),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("fehernek"))));
                else if (numberCharArray.length == 3 && numberCharArray[1] == '0' && numberCharArray[1] == '0')
             writerSays.put(numberString + "kella" + "fehernek", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0d" + ""),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("fehernek"))));
                else if (numberCharArray.length == 3 && numberCharArray[2] == '0')
             writerSays.put(numberString + "kella" + "fehernek", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0d" + ""),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("fehernek"))));
                else if (numberCharArray.length == 3)
             writerSays.put(numberString + "kella" + "fehernek", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("meg"),
            GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + ""),
            GetMediaPlayerByName( "" + numberCharArray[2] + ""),
            Constants.standardSpeaks.get("kella"),
            Constants.standardSpeaks.get("fehernek"))));

            //Vezet a sotet
            if ( numberCharArray.length==1)
                 writerSays.put(numberString+"vezet"+"sotet", new ArrayList<>(Arrays.asList(
                        GetMediaPlayerByName( numberString + "el" + ""),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 2 && numberCharArray[1] == '0')
             writerSays.put(numberString + "vezet" + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "0" + "el"),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 2)
             writerSays.put(numberString + "vezet" + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "0" + ""),
            GetMediaPlayerByName( "" + numberCharArray[1] + "el" + ""),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 3 && numberCharArray[1] == '0' && numberCharArray[2] == '0')
             writerSays.put(numberString + "vezet" + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + "el"),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 3 && numberCharArray[2] == '0')
             writerSays.put(numberString + "vezet" + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + "el"),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 3)
             writerSays.put(numberString + "vezet" + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + ""),
            GetMediaPlayerByName( "" + numberCharArray[2] + "el" + ""),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("sotet"))));

            //Vezet a világos
            if (numberCharArray.length == 1)
                 writerSays.put(numberString + "vezet" + "feher", new ArrayList<>(Arrays.asList(
                        GetMediaPlayerByName( numberString + "el" + ""),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 2 && numberCharArray[1] == '0')
             writerSays.put(numberString + "vezet" + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "0" + "el"),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 2)
             writerSays.put(numberString + "vezet" + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "0" + ""),
            GetMediaPlayerByName( "" + numberCharArray[1] + "el" + ""),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 3 && numberCharArray[1] == '0' && numberCharArray[2] == '1')
             writerSays.put(numberString + "vezet" + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + "el"),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 3 && numberCharArray[2] == '0')
             writerSays.put(numberString + "vezet" + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + "el"),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 3)
             writerSays.put(numberString + "vezet" + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + ""),
            GetMediaPlayerByName( "" + numberCharArray[2] + "el" + ""),
            Constants.standardSpeaks.get("vezeta"),
            Constants.standardSpeaks.get("feher"))));

            //új első a sotet
            if (numberCharArray.length == 1)
                 writerSays.put(numberString + "ujelso" + "sotet", new ArrayList<>(Arrays.asList(
                        GetMediaPlayerByName( numberString + "el" + ""),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 2 && numberCharArray[1] == '0')
             writerSays.put(numberString + "ujelso" + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( numberString + "el" + ""),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 2)
             writerSays.put(numberString + "ujelso" + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "0" + ""),
            GetMediaPlayerByName( "" + numberCharArray[1] + "el" + ""),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 3 && numberCharArray[1] == '0' && numberCharArray[2] == '0')
             writerSays.put(numberString + "ujelso" + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "100el"),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 3 && numberCharArray[2] == '0')
             writerSays.put(numberString + "ujelso" + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0el"),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 3)
             writerSays.put(numberString + "ujelso" + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + ""),
            GetMediaPlayerByName( "" + numberCharArray[2] + "el" + ""),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("sotet"))));

            //Új első a világos
            if (numberCharArray.length == 1)
                 writerSays.put(numberString + "ujelso" + "feher", new ArrayList<>(Arrays.asList(
                        GetMediaPlayerByName( numberString + "el" + ""),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 2 && numberCharArray[1] == '0')
             writerSays.put(numberString + "ujelso" + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "0" + "el"),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 2)
             writerSays.put(numberString + "ujelso" + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "0" + ""),
            GetMediaPlayerByName( "" + numberCharArray[1] + "el" + ""),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 3 && numberCharArray[1] == '0' && numberCharArray[2] == '0')
             writerSays.put(numberString + "ujelso" + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "100el"),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 3 && numberCharArray[2] == '0')
             writerSays.put(numberString + "ujelso" + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + "el"),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 3)
             writerSays.put(numberString + "ujelso" + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + ""),
            GetMediaPlayerByName( "" + numberCharArray[2] + "el" + ""),
            Constants.standardSpeaks.get("ujelso"),
            Constants.standardSpeaks.get("feher"))));

            //Eredmény sotet
            if (numberCharArray.length == 1)
                 writerSays.put(numberString  + "sotet", new ArrayList<>(Arrays.asList(
                        GetMediaPlayerByName( numberString  + ""),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 2 && numberCharArray[1] == '0')
             writerSays.put(numberString  + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "0d" + ""),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 2)
             writerSays.put(numberString + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "0" + ""),
            GetMediaPlayerByName( numberCharArray[1]  + ""),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 3 && numberCharArray[1] == '0' && numberCharArray[2] == '0')
             writerSays.put(numberString + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0d" + ""),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 3 && numberCharArray[2] == '0')
             writerSays.put(numberString + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0d" + ""),
            Constants.standardSpeaks.get("sotet"))));
                else if (numberCharArray.length == 3)
             writerSays.put(numberString + "sotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + ""),
            GetMediaPlayerByName( "" + numberCharArray[2] + ""),
            Constants.standardSpeaks.get("sotet"))));

            //eredmény világos
            if (numberCharArray.length == 1)
                 writerSays.put(numberString  + "feher", new ArrayList<>(Arrays.asList(
                        GetMediaPlayerByName( numberString  + ""),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 2 && numberCharArray[1] == '0')
             writerSays.put(numberString  + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "0d" + ""),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 2)
             writerSays.put(numberString + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "0" + ""),
            GetMediaPlayerByName( numberCharArray[1]  + ""),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 3 && numberCharArray[1] == '0' && numberCharArray[2] == '0')
             writerSays.put(numberString  + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0d" + ""),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 3 && numberCharArray[2] == '0')
             writerSays.put(numberString + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0d" + ""),
            Constants.standardSpeaks.get("feher"))));
                else if (numberCharArray.length == 3)
             writerSays.put(numberString + "feher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "" + numberCharArray[0] + "" + numberCharArray[1] + "0" + ""),
            GetMediaPlayerByName( numberCharArray[2]  + ""),
            Constants.standardSpeaks.get("feher"))));
            }
            //teve löket
             writerSays.put("teve1", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "teveloket1"))));

            //teve löket
             writerSays.put("teve2", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "teveloket2"))));

            //teve löket
             writerSays.put("teve3", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "teveloket3"))));

            //ivos
             writerSays.put("ivos", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "ivos"))));

            //ujjatek
             writerSays.put("ujjatek", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "ujjatek"))));

            //Nyert a feher
             writerSays.put("nyertafeher", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("nyerta"),
            Constants.standardSpeaks.get("feher"))));

            //Nyert a feher
             writerSays.put("nyertasotet", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("nyerta"),
            Constants.standardSpeaks.get("sotet"))));

             writerSays.put("feher", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("feher"))));

             writerSays.put("sotet", new ArrayList<>(Arrays.asList(
                    Constants.standardSpeaks.get("sotet"))));

             writerSays.put("töröl", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "torolve"))));

             writerSays.put("120pontfeher", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "120pontfeher"))));

             writerSays.put("120pontsotet", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName( "120pontsotet"))));
             writerSays.put("sss", new ArrayList<>(Arrays.asList(
                    GetMediaPlayerByName("sss"))));
        }
        
        private static int getSoundIdentifier(String name, String type, String packageName, Context context) {
            return context.getResources().getIdentifier("hang_" + name, type, packageName);
        }

    private static MediaPlayer GetMediaPlayerByName(String name) {
        return Constants.standardSpeaks.get(name);
    }
}
