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
        setContentView(R.layout.activity_pointer);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        for (int i = 1; i <= 22; i++) {
            int id = getResources().getIdentifier("pointWhiteButton" + Integer.toString(i), "id", context.getPackageName());
            ((Button) findViewById(id)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);

            id = getResources().getIdentifier("pointRedButton" + Integer.toString(i), "id", context.getPackageName());
            ((Button) findViewById(id)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);
        }
        ((TextView) findViewById(R.id.playerOneName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((TextView) findViewById(R.id.playerTwoName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((TextView) findViewById(R.id.playerOneName)).setText(Constants.PlayerOne.toUpperCase());
        ((TextView) findViewById(R.id.playerTwoName)).setText(Constants.PlayerTwo.toUpperCase());
    }

    public void PlayerOnePointButtons_OnClick(View view) {
        Constants.PointToAddPlayer = 1;
        Constants.PointToAdd = Integer.parseInt(((Button) view).getText().toString());

        super.onBackPressed();
    }

    public void PlayerTwoPointButtons_OnClick(View view) {
        Constants.PointToAddPlayer = 2;
        Constants.PointToAdd = Integer.parseInt(((Button) view).getText().toString());

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
