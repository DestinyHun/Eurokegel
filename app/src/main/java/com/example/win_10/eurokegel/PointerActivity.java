package com.example.win_10.eurokegel;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PointerActivity extends AppCompatActivity {

    final Context context = this;
    static boolean MustBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Constants.GamePointLimit == 120)
            setContentView(R.layout.activity_pointer_magyarbiliard);
        else if (Constants.GamePointLimit == 600)
            setContentView(R.layout.activity_pointer_relay);
        else
            setContentView(R.layout.activity_pointer_eurokegel);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        if (Constants.GamePointLimit == 120)
            for (int i = 1; i <= 22; i++)
                SetTextViewProperties(i);
        else if (Constants.GamePointLimit == 100)
            for (int i = 2; i <= 16; i=i+2)
                SetTextViewProperties(i);
        else
            for (int i = 1; i <= 38; i++)
                SetTextViewProperties(i);
    }

    public void onResume() {  // After a pause OR at startup
        super.onResume();
        if (MustBack) {
            MustBack = false;
            BackPressed();
        }
    }

    public void SetTextViewProperties(int number) {
        int id = getResources().getIdentifier("pointTextView" + Integer.toString(number), "id", context.getPackageName());
        ((TextView) findViewById(id)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);
        if (Constants.PointToAddPlayer == 1)
            ((TextView) findViewById(id)).setBackgroundResource(R.drawable.black_border_white);
        else
            ((TextView) findViewById(id)).setBackgroundResource(R.drawable.black_border_red);
    }

    public void PointTextViews_OnClick(View view) {
        Constants.PointToAdd = Integer.parseInt(((TextView) view).getText().toString());

        if (Constants.PointToAdd == 4 && Constants.AddPoint && Constants.GamePointLimit > 100) {
            if ((Constants.PointToAddPlayer == 1 && Constants.GamePointLimit - Constants.PlayerOnePoints <= 4)
                || (Constants.PointToAddPlayer == 2 && Constants.GamePointLimit - Constants.PlayerTwoPoints <= 4)) {

                MessageBoxActivity.YesButtonText = "PIROS GOLYÓ";
                MessageBoxActivity.NoButtonText = "KÉT KETTES FA";
                MessageBoxActivity.MessageType = MessageBoxActivity.MessageTypes.REDBALLSHOT;
                MessageBoxActivity.MessageText =  "Ha piros golyót talált a játékos nyomja meg a 'PIROS GOLYÓ' gombot!";
                MessageBoxActivity.TextToSpeechString = "Ha piros golyót talált a játékos nyomja meg a 'PIROS GOLYÓ' gombot";
                MessageBoxActivity.WaitForSpeech = false;
                Intent intent = new Intent(PointerActivity.this, MessageBoxActivity.class);
                startActivity(intent);
            }
            BackPressed();
        }
        else
            BackPressed();
    }

    public void BackPressed()
    {
        GameActivity.ButtonsEnabled = false;
        super.onBackPressed();
    }

    private void SpeechText(String Text) {
        SpeechText(Text,true);
    }

    private void SpeechText(String Text, boolean waitForSpeech) {
        Constants.Tts.speak(Text, TextToSpeech.QUEUE_FLUSH, null);

        if (waitForSpeech) {
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
}
