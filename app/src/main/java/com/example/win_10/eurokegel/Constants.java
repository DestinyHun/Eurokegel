package com.example.win_10.eurokegel;

import android.content.res.Resources;
import android.widget.TextView;

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

    public static void SetDefaultTextSizes(TextView tv, Resources resources)
    {
        float sourceTextSize = tv.getTextSize();
        DefaultTextSizeNormal = sourceTextSize / resources.getDisplayMetrics().density;

        DefaultTextSizeLarge = DefaultTextSizeNormal * 1.8f;
        DefaultTextSizeHuge = DefaultTextSizeNormal * 6;
    }
}
