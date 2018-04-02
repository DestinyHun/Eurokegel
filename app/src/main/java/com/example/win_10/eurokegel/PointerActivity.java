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

import org.w3c.dom.Text;

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
        else if (Constants.GamePointLimit == 600)
            setContentView(R.layout.activity_pointer_relay);
        else
            setContentView(R.layout.activity_pointer_eurokegel);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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
                SpeechText("Ha piros golyót talált a játékos nyomja meg az igen gombot");
                MessageBox.MessageAsk = MessageBox.MessageAsks.REDBALLSHOT;
                MessageBox.CreateMessageBox(context, "Kérdés", "Ha piros golyót talált a játékos nyomja meg az igen gombot");
            }
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
