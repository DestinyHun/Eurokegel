package com.example.win_10.eurokegel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity {

    Timer timer = new Timer();
    static boolean ButtonsEnabled = true;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Set player names
        ((TextView)findViewById(R.id.playerOneName)).setText(ConvertLastName(Constants.PlayerOne));
        ((TextView)findViewById(R.id.playerTwoName)).setText(ConvertLastName(Constants.PlayerTwo));
        ((TextView)findViewById(R.id.playerOneName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);
        ((TextView)findViewById(R.id.playerTwoName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);
        ((TextView)findViewById(R.id.SetStandTextView)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLightLarge);
        ((TextView)findViewById(R.id.differenceTextView)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeHuge);
        ((TextView)findViewById(R.id.playerOnePoint)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeVeryHuge);
        ((TextView)findViewById(R.id.playerTwoPoint)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeVeryHuge);
        ((TextView)findViewById(R.id.historyTextView)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);
        ((TextView)findViewById(R.id.historyTextView)).setMovementMethod(ScrollingMovementMethod.getInstance());
        ((Button)findViewById(R.id.addPointToPlayerOne)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeGreat);
        ((Button)findViewById(R.id.addPointToPlayerTwo)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeGreat);
        ((Button)findViewById(R.id.removePointFromPlayerOne)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeGreat);
        ((Button)findViewById(R.id.removePointFromPlayerTwo)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeGreat);
        ((Button)findViewById(R.id.giveUpPlayerOne)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((Button)findViewById(R.id.giveUpPlayerTwo)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);

        //Set Controls size

        this.SetControlSize(R.id.playerOneName,2,2,37,18);
        this.SetControlSize(R.id.SetStandTextView,41,2,18,18);
        this.SetControlSize(R.id.playerTwoName,61,2,37,18);

        this.SetControlSize(R.id.playerOnePoint,2,23,37,36);
        this.SetControlSize(R.id.differenceTextView,41,23,18,36);
        this.SetControlSize(R.id.playerTwoPoint,61,23,37,36);

        this.SetControlSize(R.id.addPointToPlayerOne,2,62,17,36);
        this.SetControlSize(R.id.removePointFromPlayerOne,22,62,17,17);
        this.SetControlSize(R.id.giveUpPlayerOne,22,81,17,17);
        this.SetControlSize(R.id.historyTextView,41,62,18,36);
        this.SetControlSize(R.id.removePointFromPlayerTwo,61,62,17,17);
        this.SetControlSize(R.id.giveUpPlayerTwo,61,81,17,17);
        this.SetControlSize(R.id.addPointToPlayerTwo,81,62,17,36);
    }

    public void SetControlSize(int id, float width, float height, float row, float column) {

        TextView workingTV = ((TextView) findViewById(id));

        AbsoluteLayout.LayoutParams params = ((AbsoluteLayout.LayoutParams) workingTV.getLayoutParams());
        params.width = Math.round(Constants.ScreenWidth * (row/100));
        params.height = Math.round(Constants.ScreenHeight * (column/100));
        params.x = Math.round(Constants.ScreenWidth*(width/100)) ;
        params.y = Math.round(Constants.ScreenHeight*(height/100));
        workingTV.setLayoutParams(params);
    }

    public String ConvertLastName(String name)
    {
        String convertedName = name.toUpperCase().split(" ")[0] + " " + name.toUpperCase().split(" ")[1].charAt(0);
        if ("aáeéiíoóüűöőúuAÁEÉIÍOÓUÚÜŰÖŐ".indexOf(name.toUpperCase().split(" ")[1].charAt(0)) < 0
             && "aáeéiíoóüűöőúuAÁEÉIÍOÓUÚÜŰÖŐ".indexOf(name.toUpperCase().split(" ")[1].charAt(1)) < 0) {
            convertedName += name.toUpperCase().split(" ")[1].charAt(1);
        }
        convertedName += ".";
        return convertedName;
    }


    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        ButtonsEnabled = false;
        ((TextView)findViewById(R.id.SetStandTextView)).setText(
                String.format(Locale.ENGLISH, "%d",Constants.playerOneSetWins) + " : " + String.format(Locale.ENGLISH, "%d",Constants.playerTwoSetWins));

        if (Constants.PointToAdd > 0 && Constants.PointToAddPlayer > 0)
        {
            int plyPointToSet;
            if (Constants.PointToAddPlayer == 1) {
                plyPointToSet = Constants.PlayerOnePoints + Constants.PointToAdd * (Constants.AddPoint ? 1 : -1);
                if (plyPointToSet < 0)
                    plyPointToSet = 0;
                if (plyPointToSet >= Constants.GamePointLimit) {
                    plyPointToSet = Constants.GamePointLimit;
                    if (Constants.RedBallShot && (Constants.GamePointLimit == 120 || Constants.GamePointLimit == 600))
                        plyPointToSet -= 1;
                }
                ((TextView)findViewById(R.id.playerOnePoint)).setText(String.format(Locale.ENGLISH, "%d",plyPointToSet));
                Constants.PointHistory = "<font color=#ffffff>" +
                        (Constants.AddPoint ? "" : "-") +
                        (String.format(Locale.ENGLISH, "%d",Constants.PointToAdd)) + "</font> <br/>" + Constants.PointHistory;

                if (plyPointToSet == Constants.PlayerTwoPoints) {
                    ((TextView) findViewById(R.id.differenceTextView)).setText(String.format(Locale.ENGLISH, "%d", 0));
                    ((TextView) findViewById(R.id.differenceTextView)).setBackgroundResource(R.drawable.black_border_darkgrey);
                }
                else if (plyPointToSet > Constants.PlayerTwoPoints) {
                    ((TextView) findViewById(R.id.differenceTextView)).setText(String.format(Locale.ENGLISH, "%d", plyPointToSet-Constants.PlayerTwoPoints));
                    ((TextView) findViewById(R.id.differenceTextView)).setBackgroundResource(R.drawable.black_border_white);
                }
                else{
                    ((TextView) findViewById(R.id.differenceTextView)).setText(String.format(Locale.ENGLISH, "%d", Constants.PlayerTwoPoints - plyPointToSet));
                    ((TextView) findViewById(R.id.differenceTextView)).setBackgroundResource(R.drawable.black_border_yellow);
                }

            }
            else {
                plyPointToSet = Constants.PlayerTwoPoints + Constants.PointToAdd * (Constants.AddPoint ? 1 : -1);
                if (plyPointToSet < 0)
                    plyPointToSet = 0;
                if (plyPointToSet >= Constants.GamePointLimit) {
                    plyPointToSet = Constants.GamePointLimit;
                    if (Constants.RedBallShot && (Constants.GamePointLimit == 120 || Constants.GamePointLimit == 600))
                        plyPointToSet -= 1;
                }
                ((TextView)findViewById(R.id.playerTwoPoint)).setText(String.format(Locale.ENGLISH, "%d",plyPointToSet));
                Constants.PointHistory = "<font color=#ffa500>" +
                        (Constants.AddPoint ? "" : "-") +
                        (String.format(Locale.ENGLISH, "%d",Constants.PointToAdd)) + "</font> <br/>" + Constants.PointHistory;

                if (Constants.PlayerOnePoints == plyPointToSet) {
                    ((TextView) findViewById(R.id.differenceTextView)).setText(String.format(Locale.ENGLISH, "%d", 0));
                    ((TextView) findViewById(R.id.differenceTextView)).setBackgroundResource(R.drawable.black_border_darkgrey);
                }
                else if (Constants.PlayerOnePoints > plyPointToSet) {
                    ((TextView) findViewById(R.id.differenceTextView)).setText(String.format(Locale.ENGLISH, "%d", Constants.PlayerOnePoints - plyPointToSet));
                    ((TextView) findViewById(R.id.differenceTextView)).setBackgroundResource(R.drawable.black_border_white);
                }
                else{
                    ((TextView) findViewById(R.id.differenceTextView)).setText(String.format(Locale.ENGLISH, "%d", plyPointToSet - Constants.PlayerOnePoints ));
                    ((TextView) findViewById(R.id.differenceTextView)).setBackgroundResource(R.drawable.black_border_yellow);
                }
            }

            timer = new Timer();
            timer.schedule(new RemindTask(), 1*100);
        }
        else
        {
            ButtonsEnabled = true;
            ((TextView)findViewById(R.id.playerOnePoint)).setText(String.format(Locale.ENGLISH, "%d",Constants.PlayerOnePoints));
            ((TextView)findViewById(R.id.playerTwoPoint)).setText(String.format(Locale.ENGLISH, "%d",Constants.PlayerTwoPoints));
        }

        ((TextView)findViewById(R.id.historyTextView)).setText(Html.fromHtml(Constants.PointHistory));

        if (Constants.playerOneSetWins == Constants.GameSetLimit)
            SetNewGame("fehér");
        else if (Constants.playerTwoSetWins == Constants.GameSetLimit )
            SetNewGame("sárga");
        else if (Constants.SetEnds)
            SetNewSet();
    }

    void SetNewGame(String winnerPlayer)
    {
        SpeechText("A " + winnerPlayer + " " + Constants.playerOneSetWins + " " + Constants.playerTwoSetWins + " arányban megnyerte a meccset");
        Constants.GameEnds = true;
        ((Button)findViewById(R.id.addPointToPlayerOne)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.addPointToPlayerTwo)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.removePointFromPlayerOne)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.removePointFromPlayerTwo)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.giveUpPlayerOne)).setText("ÚJ PARTI");
        ((Button)findViewById(R.id.giveUpPlayerTwo)).setText("ÚJ PARTI");
    }

    void SetNewSet()
    {
        ((Button)findViewById(R.id.addPointToPlayerOne)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.addPointToPlayerTwo)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.removePointFromPlayerOne)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.removePointFromPlayerTwo)).setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.giveUpPlayerOne)).setText("ÚJ SZETT");
        ((Button)findViewById(R.id.giveUpPlayerTwo)).setText("ÚJ SZETT");
    }

    class RemindTask extends TimerTask {
        public void run() {
            SetPoints(Constants.PointToAddPlayer, Constants.PointToAdd);

            Constants.PointToAdd = 0;
            Constants.PointToAddPlayer = 0;
            ButtonsEnabled = true;
            timer.cancel(); //A timer szál megszüntetése
        }
    }

    public void AddPointToPlayerOne_OnClick(View view)
    {
        if (!ButtonsEnabled)
            return;
        Constants.AddPoint = true;
        Constants.RedBallShot = false;
        Constants.PointToAddPlayer = 1;
        Intent intent = new Intent(GameActivity.this, PointerActivity.class);
        startActivity(intent);
    }

    public void AddPointToPlayerTwo_OnClick(View view)
    {
        if (!ButtonsEnabled)
            return;
        Constants.AddPoint = true;
        Constants.RedBallShot = false;
        Constants.PointToAddPlayer = 2;
        Intent intent = new Intent(GameActivity.this, PointerActivity.class);
        startActivity(intent);
    }

    public void RemovePointFromPlayerOne_OnClick(View view)
    {
        if (!ButtonsEnabled)
            return;
        SpeechText("Törlés", false, false);
        Constants.AddPoint = false;
        Constants.RedBallShot = false;
        Constants.PointToAddPlayer = 1;
        Intent intent = new Intent(GameActivity.this, PointerActivity.class);
        startActivity(intent);
    }

    public void RemovePointFromPlayerTwo_OnClick(View view)
    {
        if (!ButtonsEnabled)
            return;
        SpeechText("Törlés", false, false);
        Constants.AddPoint = false;
        Constants.RedBallShot = false;
        Constants.PointToAddPlayer = 2;
        Intent intent = new Intent(GameActivity.this, PointerActivity.class);
        startActivity(intent);
    }

    public void GiveUpPlayerOne_OnClick(View view)
    {
        if (Constants.GameEnds) {
            ((TextView) findViewById(R.id.differenceTextView)).setText(String.format(Locale.ENGLISH, "%d", 0));
            Constants.StartNewGame(context);
            return;
        }

        if (Constants.SetEnds) {
            ((TextView) findViewById(R.id.differenceTextView)).setText(String.format(Locale.ENGLISH, "%d", 0));
            Constants.StartNewSet(context);
            ((Button)findViewById(R.id.addPointToPlayerOne)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.addPointToPlayerTwo)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.removePointFromPlayerOne)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.removePointFromPlayerTwo)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.giveUpPlayerOne)).setText("FELADÁS");
            ((Button)findViewById(R.id.giveUpPlayerTwo)).setText("FELADÁS");
            return;
        }

        if (!ButtonsEnabled)
            return;

        MessageBoxActivity.YesButtonText = "FELADÁS";
        MessageBoxActivity.NoButtonText = "MÉGSE";
        MessageBoxActivity.MessageType = MessageBoxActivity.MessageTypes.PLYONEGIVEUP;
        MessageBoxActivity.MessageText =  "Amennyiben a 'FELADÁS' lehetőséget választja " + Constants.PlayerOne + " feladja a szettet!";
        MessageBoxActivity.TextToSpeechString = "Amennyiben a feladás lehetőséget választja " + Constants.PlayerOne + " feladja a szettet";
        MessageBoxActivity.WaitForSpeech = false;
        Intent intent = new Intent(GameActivity.this, MessageBoxActivity.class);
        startActivity(intent);
    }

    public void GiveUpPlayerTwo_OnClick(View view)
    {
        if (Constants.GameEnds) {
            ((TextView) findViewById(R.id.differenceTextView)).setText(String.format(Locale.ENGLISH, "%d", 0));
            Constants.StartNewGame(context);
            return;
        }

        if (Constants.SetEnds) {
            ((TextView) findViewById(R.id.differenceTextView)).setText(String.format(Locale.ENGLISH, "%d", 0));
            Constants.StartNewSet(context);
            ((Button)findViewById(R.id.addPointToPlayerOne)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.addPointToPlayerTwo)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.removePointFromPlayerOne)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.removePointFromPlayerTwo)).setVisibility(View.VISIBLE);
            ((Button)findViewById(R.id.giveUpPlayerOne)).setText("FELADÁS");
            ((Button)findViewById(R.id.giveUpPlayerTwo)).setText("FELADÁS");
            return;
        }

        if (!ButtonsEnabled)
            return;

        MessageBoxActivity.YesButtonText = "FELADÁS";
        MessageBoxActivity.NoButtonText = "MÉGSE";
        MessageBoxActivity.MessageType = MessageBoxActivity.MessageTypes.PLYTWOGIVEUP;
        MessageBoxActivity.MessageText =  "Amennyiben a 'FELADÁS' lehetőséget választja " + Constants.PlayerTwo + " feladja a szettet!";
        MessageBoxActivity.TextToSpeechString = "Amennyiben a feladás lehetőséget választja " + Constants.PlayerTwo + " feladja a szettet";
        MessageBoxActivity.WaitForSpeech = false;
        Intent intent = new Intent(GameActivity.this, MessageBoxActivity.class);
        startActivity(intent);
    }

    private void SetPoints(int player, int pointsToSet) {
        boolean correction = !Constants.AddPoint;
        boolean newLeader = (player == 1  && !correction && Constants.PlayerOnePoints < Constants.PlayerTwoPoints && Constants.PlayerOnePoints + pointsToSet > Constants.PlayerTwoPoints)
                || (player == 2 && !correction && Constants.PlayerOnePoints > Constants.PlayerTwoPoints && Constants.PlayerOnePoints < Constants.PlayerTwoPoints + pointsToSet)
                || (player == 1 && correction && Constants.PlayerOnePoints > Constants.PlayerTwoPoints && Constants.PlayerOnePoints - pointsToSet < Constants.PlayerTwoPoints)
                || (player == 2 && correction && Constants.PlayerOnePoints < Constants.PlayerTwoPoints && Constants.PlayerOnePoints > Constants.PlayerTwoPoints - pointsToSet);

        if (player == 1) {
            SpeechText(String.format(Locale.ENGLISH, "%d", pointsToSet) + " fehér" + (correction ? " törölve" : ""));

            Constants.PlayerOnePoints += pointsToSet * (correction ? -1 : 1);
            if (Constants.PlayerOnePoints < 0)
                Constants.PlayerOnePoints = 0;
            if (Constants.PlayerOnePoints >= Constants.GamePointLimit) {
                Constants.PlayerOnePoints = Constants.GamePointLimit;
                if (Constants.RedBallShot && (Constants.GamePointLimit == 120 || Constants.GamePointLimit == 600)) {
                    Constants.PlayerOnePoints -= 1;
                    SpeechText("Mivel piros golyó találatával lett meg a 120 pont, ezért", true, false);
                }
            }

            if (newLeader && Constants.PlayerOnePoints - Constants.PlayerTwoPoints != 0) {
                if (correction)
                    SpeechText(String.format(Locale.ENGLISH, "%d", (Constants.PlayerTwoPoints - Constants.PlayerOnePoints )) + " ponttal új első a sárga.");
                else
                    SpeechText(String.format(Locale.ENGLISH, "%d", (Constants.PlayerOnePoints - Constants.PlayerTwoPoints)) + " ponttal új első a fehér.");
            }

            if (Constants.PlayerOnePoints == Constants.PlayerTwoPoints)
                SpeechText("Egyenlő az állás.");

            if (Constants.GameType == Constants.GameTypes.RELAY)
            {
                if (Constants.PlayerOnePoints - pointsToSet < 120 && Constants.PlayerOnePoints >= 120) {
                    SpeechText("A fehér csapat elérte a 120 pontot, a második játékos következik játszani");
                    Constants.playerOneSetWins += 1;
                }
                if (Constants.PlayerOnePoints - pointsToSet < 240 && Constants.PlayerOnePoints  >= 240){
                    SpeechText("A fehér csapat elérte a 240 pontot, a harmadik játékos következik játszani");
                    Constants.playerOneSetWins += 1;
                }
                if (Constants.PlayerOnePoints - pointsToSet < 360 && Constants.PlayerOnePoints  >= 360){
                    SpeechText("A fehér csapat elérte a 360 pontot, a negyedik játékos következik játszani");
                    Constants.playerOneSetWins += 1;
                }
                if (Constants.PlayerOnePoints - pointsToSet < 480 && Constants.PlayerOnePoints  >= 480){
                    SpeechText("A fehér csapat elérte a 480 pontot, az ötödik játékos következik játszani");
                    Constants.playerOneSetWins += 1;
                }
            }

            if (Constants.PlayerOnePoints == Constants.GamePointLimit) {
                SpeechText("A fehér elérte a " +
                        String.format(Locale.ENGLISH, "%d", Constants.GamePointLimit)
                        + " pontot és megnyerte a szettet.");

                ((GameActivity)context).runOnUiThread(new Runnable() {
                    public void run() {

                        MessageBoxActivity.YesButtonText = "SZETT VÉGE";
                        MessageBoxActivity.NoButtonText = "EREDMÉNY JAVÍTÁSA";
                        MessageBoxActivity.MessageType = MessageBoxActivity.MessageTypes.PLYONEWIN;
                        MessageBoxActivity.MessageText =  "Amennyiben az eredmény helyes, nyomja meg a 'SZETT VÉGE' gombot!";
                        MessageBoxActivity.TextToSpeechString = "Amennyiben az eredmény helyes, nyomja meg a szett vége gombot";
                        MessageBoxActivity.WaitForSpeech = true;
                        Intent intent = new Intent(GameActivity.this, MessageBoxActivity.class);
                        startActivity(intent);
                    }
                });
            }
            else if (Constants.PlayerOnePoints > Constants.GamePointLimit-20)
                SpeechText("Még " + String.format(Locale.ENGLISH, "%d", (Constants.GamePointLimit - Constants.PlayerOnePoints)) + " pont kell a fehérnek.");
        }

        if (player == 2) {
            SpeechText(String.format(Locale.ENGLISH, "%d", pointsToSet) + " sárga" + (correction ? " törölve" : ""));

            Constants.PlayerTwoPoints += pointsToSet * (correction ? -1 : 1);
            if (Constants.PlayerTwoPoints < 0)
                Constants.PlayerTwoPoints = 0;
            if (Constants.PlayerTwoPoints >= Constants.GamePointLimit) {
                Constants.PlayerTwoPoints = Constants.GamePointLimit;
                if (Constants.RedBallShot && (Constants.GamePointLimit == 120 || Constants.GamePointLimit == 600)) {
                    Constants.PlayerTwoPoints -= 1;
                    SpeechText("Mivel piros golyó találatával lett meg a 120 pont, ezért", true, false);
                }
            }

            if (newLeader && Constants.PlayerTwoPoints - Constants.PlayerOnePoints != 0)
            {
                if (correction)
                    SpeechText(String.format(Locale.ENGLISH, "%d", (Constants.PlayerOnePoints-Constants.PlayerTwoPoints)) + " ponttal új első a fehér.");
                else
                    SpeechText(String.format(Locale.ENGLISH, "%d", (Constants.PlayerTwoPoints - Constants.PlayerOnePoints)) + " ponttal új első a sárga.");
            }

            if (Constants.PlayerOnePoints == Constants.PlayerTwoPoints)
                SpeechText("Egyenlő az állás.");

            if (Constants.GameType == Constants.GameTypes.RELAY)
            {
                if (Constants.PlayerTwoPoints - pointsToSet < 120 && Constants.PlayerTwoPoints  >= 120){
                    SpeechText("A sárga csapat elérte a 120 pontot, a második játékos következik játszani");
                    Constants.playerTwoSetWins += 1;
                }
                if (Constants.PlayerTwoPoints - pointsToSet < 240 && Constants.PlayerTwoPoints  >= 240){
                    SpeechText("A sárga csapat elérte a 240 pontot, a harmadik játékos következik játszani");
                    Constants.playerTwoSetWins += 1;
                }
                if (Constants.PlayerTwoPoints - pointsToSet < 360 && Constants.PlayerTwoPoints  >= 360){
                    SpeechText("A sárga csapat elérte a 360 pontot, a negyedik játékos következik játszani");
                    Constants.playerTwoSetWins += 1;
                }
                if (Constants.PlayerTwoPoints - pointsToSet < 480 && Constants.PlayerTwoPoints  >= 480){
                    SpeechText("A sárga csapat elérte a 480 pontot, az ötödik játékos következik játszani");
                    Constants.playerTwoSetWins += 1;
                }
            }

            if (Constants.PlayerTwoPoints == Constants.GamePointLimit) {

                SpeechText("A sárga elérte a " +
                        String.format(Locale.ENGLISH, "%d", Constants.GamePointLimit)
                        + " pontot és megnyerte a szettet.");

                ((GameActivity)context).runOnUiThread(new Runnable() {
                    public void run() {

                        MessageBoxActivity.YesButtonText = "SZETT VÉGE";
                        MessageBoxActivity.NoButtonText = "EREDMÉNY JAVÍTÁSA";
                        MessageBoxActivity.MessageType = MessageBoxActivity.MessageTypes.PLYTWOWIN;
                        MessageBoxActivity.MessageText =  "Amennyiben az eredmény helyes, nyomja meg a 'SZETT VÉGE' gombot!";
                        MessageBoxActivity.TextToSpeechString = "Amennyiben az eredmény helyes, nyomja meg a szett vége gombot";
                        MessageBoxActivity.WaitForSpeech = true;
                        Intent intent = new Intent(GameActivity.this, MessageBoxActivity.class);
                        startActivity(intent);
                    }
                });
            }
            else if (Constants.PlayerTwoPoints > Constants.GamePointLimit - 20)
                SpeechText("Még " + String.format(Locale.ENGLISH, "%d", (Constants.GamePointLimit - Constants.PlayerTwoPoints)) + " pont kell a sárgának.");
        }

        if (Constants.GameType == Constants.GameTypes.RELAY) {
            ((GameActivity)context).runOnUiThread(new Runnable() {
                public void run() {

                    ((TextView)findViewById(R.id.SetStandTextView)).setText(
                            String.format(Locale.ENGLISH, "%d",Constants.playerOneSetWins) + " : " + String.format(Locale.ENGLISH, "%d",Constants.playerTwoSetWins));
                }
            });
        }
        ButtonsEnabled = true;
    }

    private void SpeechText(String Text) {
        SpeechText(Text,true, true);
    }

    private void SpeechText(String Text, boolean waitForSpeech, boolean pauseAfter) {
        Constants.Tts.speak(Text, TextToSpeech.QUEUE_FLUSH, null);

        if (waitForSpeech) {
            while (Constants.Tts.isSpeaking()) {
                // Freezes the application.
            }
            if (pauseAfter) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (ButtonsEnabled)
            super.onBackPressed();
    }

    public void BackPressed()
    {
        super.onBackPressed();
    }
}
