package com.example.win_10.eurokegel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Set player names
        ((TextView)findViewById(R.id.playerOneName)).setText(Constants.PlayerOne.toUpperCase());
        ((TextView)findViewById(R.id.playerTwoName)).setText(Constants.PlayerTwo.toUpperCase());
        ((TextView)findViewById(R.id.playerOneName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((TextView)findViewById(R.id.playerTwoName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((TextView)findViewById(R.id.playerOnePoint)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeHuge);
        ((TextView)findViewById(R.id.playerPointSeparator)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeHuge);
        ((TextView)findViewById(R.id.playerTwoPoint)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeHuge);
        ((Button)findViewById(R.id.addPoint)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((Button)findViewById(R.id.removePoint)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((Button)findViewById(R.id.history)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((Button)findViewById(R.id.newSet)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
    }

    public void onResume()
    {  // After a pause OR at startup
        super.onResume();

        if (Constants.PointToAdd > 0 && Constants.PointToAddPlayer > 0)
        {
            int plyPointToSet;
            if (Constants.PointToAddPlayer == 1) {
                plyPointToSet = Constants.PlayerOnePoints + Constants.PointToAdd * (Constants.AddPoint ? 1 : -1);
                if (plyPointToSet < 0)
                    plyPointToSet = 0;
                if (plyPointToSet > Constants.GamePointLimit)
                    plyPointToSet = Constants.GamePointLimit;
                ((TextView)findViewById(R.id.playerOnePoint)).setText(String.format(Locale.ENGLISH, "%d",plyPointToSet));
            }
            else {
                plyPointToSet = Constants.PlayerTwoPoints + Constants.PointToAdd * (Constants.AddPoint ? 1 : -1);
                if (plyPointToSet < 0)
                    plyPointToSet = 0;
                if (plyPointToSet > Constants.GamePointLimit)
                    plyPointToSet = Constants.GamePointLimit;
                ((TextView)findViewById(R.id.playerTwoPoint)).setText(String.format(Locale.ENGLISH, "%d",plyPointToSet));
            }

            timer = new Timer();
            timer.schedule(new RemindTask(), 1*100);
        }
        else
        {
            ((TextView)findViewById(R.id.playerOnePoint)).setText(String.format(Locale.ENGLISH, "%d",Constants.PlayerOnePoints));
            ((TextView)findViewById(R.id.playerTwoPoint)).setText(String.format(Locale.ENGLISH, "%d",Constants.PlayerTwoPoints));
        }
    }

    class RemindTask extends TimerTask {
        public void run() {
            SetPoints(Constants.PointToAddPlayer, Constants.PointToAdd);

            Constants.PointToAdd = 0;
            Constants.PointToAddPlayer = 0;
            timer.cancel(); //A timer szál megszüntetése
        }
    }

    public void AddPoint_OnClick(View view)
    {
        Constants.AddPoint = true;
        Intent intent = new Intent(GameActivity.this, PointerActivity.class);
        startActivity(intent);
    }

    public void RemovePoint_OnClick(View view)
    {
        Constants.AddPoint = false;
        Intent intent = new Intent(GameActivity.this, PointerActivity.class);
        startActivity(intent);
    }

    public void History_OnClick(View view)
    {
    }

    public void NewSet_OnClick(View view)
    {
    }

    private void SetPoints(int player, int pointsToSet) {
        boolean correction = !Constants.AddPoint;
        boolean newLeader = (player == 1 && Constants.PlayerOnePoints < Constants.PlayerTwoPoints && Constants.PlayerOnePoints + pointsToSet > Constants.PlayerTwoPoints)
                || (player == 2 && Constants.PlayerOnePoints > Constants.PlayerTwoPoints && Constants.PlayerOnePoints < Constants.PlayerTwoPoints + pointsToSet)
                || (player == 1 && correction && Constants.PlayerOnePoints > Constants.PlayerTwoPoints && Constants.PlayerOnePoints - pointsToSet < Constants.PlayerTwoPoints)
                || (player == 2 && correction && Constants.PlayerOnePoints < Constants.PlayerTwoPoints && Constants.PlayerOnePoints > Constants.PlayerTwoPoints - pointsToSet);

        if (player == 1) {
            Constants.PlayerOnePoints += pointsToSet * (correction ? -1 : 1);
            if (Constants.PlayerOnePoints < 0)
                Constants.PlayerOnePoints = 0;
            if (Constants.PlayerOnePoints > Constants.GamePointLimit)
                Constants.PlayerOnePoints = Constants.GamePointLimit;

            SpeechText(String.format(Locale.ENGLISH, "%d", pointsToSet) + " fehér" + (correction ? " törölve" : ""));

            if (newLeader && Constants.PlayerOnePoints - Constants.PlayerTwoPoints != 0) {
                if (correction)
                    SpeechText(String.format(Locale.ENGLISH, "%d", (Constants.PlayerTwoPoints - Constants.PlayerOnePoints )) + "el új első a sötét.");
                else
                    SpeechText(String.format(Locale.ENGLISH, "%d", (Constants.PlayerOnePoints - Constants.PlayerTwoPoints)) + "el új első a fehér.");
            }

            if (Constants.PlayerOnePoints == Constants.PlayerTwoPoints)
                SpeechText("Egyenlő az állás.");

            if (pointsToSet == 4 && Constants.PlayerOnePoints == 120 && Constants.GamePointLimit == 120)
                SpeechText("A fehér elérte a 120 pontot, de valószínűleg piros golyóval, így nincs vége a partinak.");
            else if (Constants.PlayerOnePoints == Constants.GamePointLimit)
                SpeechText("A fehér elérte a " +
                        String.format(Locale.ENGLISH, "%d", Constants.GamePointLimit)
                        + " pontot és megnyerte a szettet.");
            else if (Constants.PlayerOnePoints > Constants.GamePointLimit-20)
                SpeechText("Még " + String.format(Locale.ENGLISH, "%d", (Constants.GamePointLimit - Constants.PlayerOnePoints)) + " kell a fehérnek.");
        }

        if (player == 2) {
            Constants.PlayerTwoPoints += pointsToSet * (correction ? -1 : 1);
            if (Constants.PlayerTwoPoints < 0)
                Constants.PlayerTwoPoints = 0;
            if (Constants.PlayerTwoPoints > Constants.GamePointLimit)
                Constants.PlayerTwoPoints = Constants.GamePointLimit;

            SpeechText(String.format(Locale.ENGLISH, "%d", pointsToSet) + " sötét" + (correction ? " törölve" : ""));

            if (newLeader && Constants.PlayerTwoPoints - Constants.PlayerOnePoints != 0)
            {
                if (correction)
                    SpeechText(String.format(Locale.ENGLISH, "%d", (Constants.PlayerOnePoints-Constants.PlayerTwoPoints)) + "el új első a fehér.");
                else
                    SpeechText(String.format(Locale.ENGLISH, "%d", (Constants.PlayerTwoPoints - Constants.PlayerOnePoints)) + "el új első a sötét.");
            }

            if (Constants.PlayerOnePoints == Constants.PlayerTwoPoints)
                SpeechText("Egyenlő az állás.");

            if (pointsToSet == 4 && Constants.PlayerTwoPoints == 120 && Constants.GamePointLimit == 120)
                SpeechText("A sötét elérte a 120 pontot, de valószínűleg piros golyóval, így nincs vége a partinak.");
            else if (Constants.PlayerTwoPoints == Constants.GamePointLimit)
                SpeechText("A sötét elérte a " +
                        String.format(Locale.ENGLISH, "%d", Constants.GamePointLimit)
                        + " pontot és megnyerte a szettet.");
            else if (Constants.PlayerTwoPoints > Constants.GamePointLimit-20)
                SpeechText("Még " + String.format(Locale.ENGLISH, "%d", (Constants.GamePointLimit - Constants.PlayerTwoPoints)) + " kell a sötétnek.");
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
