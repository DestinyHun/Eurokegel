package com.example.win_10.eurokegel;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PointerActivity extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Constants.GamePointLimit == 120)
            setContentView(R.layout.activity_pointer_magyarbiliard);
        else
            setContentView(R.layout.activity_pointer_eurokegel);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (Constants.GamePointLimit == 120) {
            for (int i = 1; i <= 22; i++) {
                int id = getResources().getIdentifier("pointButton" + Integer.toString(i), "id", context.getPackageName());
                ((Button) findViewById(id)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);
                if (Constants.PointToAddPlayer == 1)
                    ((Button) findViewById(id)).setBackgroundResource(R.drawable.black_border_white);
                else
                    ((Button) findViewById(id)).setBackgroundResource(R.drawable.black_border_red);
            }
        }
        else {
            for (int i = 1; i <= 8; i++) {
                int id = getResources().getIdentifier("pointButton" + Integer.toString(i*2), "id", context.getPackageName());
                ((Button) findViewById(id)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);
                if (Constants.PointToAddPlayer == 1)
                    ((Button) findViewById(id)).setBackgroundResource(R.drawable.black_border_white);
                else
                    ((Button) findViewById(id)).setBackgroundResource(R.drawable.black_border_red);
            }
        }
    }

    public void PointButtons_OnClick(View view) {
        Constants.PointToAdd = Integer.parseInt(((Button) view).getText().toString());
        GameActivity.ButtonsEnabled = false;
        super.onBackPressed();
    }

    public void AddPoint(int player, int pointsToSet) {
        int oldPoints = player == 1 ? Constants.PlayerOnePoints : Constants.PlayerTwoPoints;

        if (!Constants.AddPoint) {
            pointsToSet *= -1;
            Constants.AddPoint = true;
        }

        int newPoints = oldPoints + pointsToSet;

        if (newPoints > Constants.GamePointLimit)
            newPoints = Constants.GamePointLimit;
        else if (newPoints < 0)
            newPoints = 0;

        if (player == 1)
            Constants.PlayerOnePoints = newPoints;
        else
            Constants.PlayerTwoPoints = newPoints;
    }

    private void SetPoints(int player, int pointsToSet) {
        boolean newLeader = (player == 1 && Constants.PlayerOnePoints < Constants.PlayerTwoPoints && Constants.PlayerOnePoints + pointsToSet > Constants.PlayerTwoPoints)
                || (player == 2 && Constants.PlayerOnePoints > Constants.PlayerTwoPoints && Constants.PlayerOnePoints < Constants.PlayerTwoPoints + pointsToSet);
        boolean correction = !Constants.AddPoint;

        if (player == 1) {
            Constants.PlayerOnePoints += pointsToSet * (correction ? -1 : 1);
            if (Constants.PlayerOnePoints < 0)
                Constants.PlayerOnePoints = 0;
            if (Constants.PlayerOnePoints > Constants.GamePointLimit)
                Constants.PlayerOnePoints = Constants.GamePointLimit;

            SpeechText(String.format(Locale.ENGLISH, "%d", pointsToSet) + " fehér" + (correction ? " törölve" : ""));

            if (newLeader && Constants.PlayerOnePoints - Constants.PlayerTwoPoints != 0)
                SpeechText(String.format(Locale.ENGLISH, "%d", (Constants.PlayerOnePoints - Constants.PlayerTwoPoints)) + "el új első a fehér.");

            if (Constants.PlayerOnePoints == Constants.PlayerTwoPoints)
                SpeechText("Egyenlő az állás.");

            if (pointsToSet == 4 && Constants.PlayerOnePoints == 120)
                SpeechText("A fehér elérte a 120 pontot, de valószínűleg piros golyóval, így nincs vége a partinak.");
            else if (Constants.PlayerOnePoints == 120)
                SpeechText("A fehér elérte a 120 pontot és megnyerte a szettet.");
            else if (Constants.PlayerOnePoints > 100)
                SpeechText("Még " + String.format(Locale.ENGLISH, "%d", (120 - Constants.PlayerOnePoints)) + " kell a fehérnek.");
        }

        if (player == 2) {
            Constants.PlayerTwoPoints += pointsToSet * (correction ? -1 : 1);
            if (Constants.PlayerTwoPoints < 0)
                Constants.PlayerTwoPoints = 0;
            if (Constants.PlayerTwoPoints > Constants.GamePointLimit)
                Constants.PlayerTwoPoints = Constants.GamePointLimit;

            SpeechText(String.format(Locale.ENGLISH, "%d", pointsToSet) + " sötét" + (correction ? " törölve" : ""));

            if (newLeader && Constants.PlayerTwoPoints - Constants.PlayerOnePoints != 0)
                SpeechText(String.format(Locale.ENGLISH, "%d", (Constants.PlayerTwoPoints - Constants.PlayerOnePoints)) + "el új első a sötét.");

            if (Constants.PlayerOnePoints == Constants.PlayerTwoPoints)
                SpeechText("Egyenlő az állás.");

            if (pointsToSet == 4 && Constants.PlayerTwoPoints == 120)
                SpeechText("A sötét elérte a 120 pontot, de valószínűleg piros golyóval, így nincs vége a partinak.");
            else if (Constants.PlayerTwoPoints == 120)
                SpeechText("A sötét elérte a 120 pontot és megnyerte a szettet.");
            else if (Constants.PlayerTwoPoints > 100)
                SpeechText("Még " + String.format(Locale.ENGLISH, "%d", (120 - Constants.PlayerTwoPoints)) + " kell a sötétnek.");
        }
    }

    private void SpeechText(String Text) {
        Constants.Tts.speak(Text, TextToSpeech.QUEUE_FLUSH, null);
        while (Constants.Tts.isSpeaking()) {
            // Freezes the application.
        }
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
