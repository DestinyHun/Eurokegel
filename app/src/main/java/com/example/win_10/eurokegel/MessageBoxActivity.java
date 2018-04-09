package com.example.win_10.eurokegel;

import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MessageBoxActivity extends AppCompatActivity {

    public enum MessageTypes {
        NONE,
        PLYONEGIVEUP,
        PLYTWOGIVEUP,
        REDBALLSHOT,
        PLYONEWIN,
        PLYTWOWIN
    }

    static MessageBoxActivity.MessageTypes MessageType = MessageBoxActivity.MessageType.NONE;
    static Context actualContext;
    private boolean mResult;
    static boolean WaitForSpeech = true;
    static String TextToSpeechString = "";
    static String MessageText = "";
    Timer timer = new Timer();
    static String YesButtonText = "";
    static String NoButtonText = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_box);

        ((TextView)findViewById(R.id.MessageTextView)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);
        ((TextView)findViewById(R.id.Yes)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);
        ((TextView)findViewById(R.id.No)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);

        ((TextView)findViewById(R.id.Yes)).setText(YesButtonText);
        ((TextView)findViewById(R.id.No)).setText(NoButtonText);

        ((TextView)findViewById(R.id.MessageTextView)).setText(MessageText);

        timer = new Timer();
        timer.schedule(new RemindTask(), 1*100);
    }

    class RemindTask extends TimerTask {
        public void run() {
            SpeechText();
            timer.cancel(); //A timer szál megszüntetése
        }
    }

    public void Yes_OnClick(View view)
    {
        if (MessageType == MessageTypes.PLYONEGIVEUP) {
            if (Constants.GameType == Constants.GameTypes.RELAY)
                Constants.playerTwoSetWins = 5;
            else
                Constants.playerTwoSetWins += 1;
            Constants.PlayerTwoPoints = Constants.GamePointLimit;
            TextToSpeechString = Constants.PlayerOne + " feladta a szettet";
            SpeechText();
            Constants.SetEnds = true;
        }
        else if (MessageType == MessageTypes.PLYTWOGIVEUP) {
            if (Constants.GameType == Constants.GameTypes.RELAY)
                Constants.playerOneSetWins = 5;
            else
                Constants.playerOneSetWins += 1;
            Constants.PlayerOnePoints = Constants.GamePointLimit;
            TextToSpeechString = Constants.PlayerTwo + " feladta a szettet";
            SpeechText();
            Constants.SetEnds = true;
        }
        else if (MessageType == MessageTypes.REDBALLSHOT) {
            Constants.RedBallShot = true;
            PointerActivity.MustBack = true;
        }
        else if (MessageType == MessageTypes.PLYONEWIN) {
            if (Constants.PlayerTwoPoints == Constants.GamePointLimit)
                Constants.PlayerTwoPoints = Constants.GamePointLimit-1;
                Constants.playerOneSetWins += 1;
            Constants.SetEnds = true;
        }
        else if (MessageType == MessageTypes.PLYTWOWIN) {
            if (Constants.PlayerOnePoints == Constants.GamePointLimit)
                Constants.PlayerOnePoints = Constants.GamePointLimit-1;
            Constants.playerTwoSetWins += 1;
            Constants.SetEnds = true;
        }

        BackPressed();
    }

    public void No_OnClick(View view)
    {
        if (MessageType == MessageTypes.REDBALLSHOT)
            PointerActivity.MustBack = true;

        BackPressed();
    }

    private void SpeechText() {
        Constants.Tts.speak(TextToSpeechString, TextToSpeech.QUEUE_FLUSH, null);

        if (WaitForSpeech) {
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

    public void BackPressed()
    {
        MessageBoxActivity.MessageText =  "";
        MessageBoxActivity.TextToSpeechString = "";
        MessageBoxActivity.WaitForSpeech = true;
        MessageType = MessageTypes.NONE;

        super.onBackPressed();
    }
}
