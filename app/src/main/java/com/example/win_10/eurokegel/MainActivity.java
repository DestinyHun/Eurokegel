package com.example.win_10.eurokegel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Object t = getResources();
        Constants.SetDefaultTextSizes(new TextView(this), getResources());
        ((Button)findViewById(R.id.newGameButtonSingle)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((Button)findViewById(R.id.newGameButtonPair)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((Button)findViewById(R.id.newGameButtonRelay)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);

        Constants.InitSounds(MainActivity.this);

    }

    public void MagyarbiliardTextView_OnClick(View view)
    {
        Constants.GamePointLimit = 120;
        findViewById(R.id.magyarbiliardOkImageView).setVisibility(View.VISIBLE);
        findViewById(R.id.eurokegelOkImageView).setVisibility(View.INVISIBLE);
        findViewById(R.id.newGameButtonRelay).setVisibility(View.VISIBLE);

        ((Button)findViewById(R.id.newGameButtonSingle)).setText("Magyarbiliárd egyéni játék");
        ((Button)findViewById(R.id.newGameButtonPair)).setText("Magyarbiliárd páros játék");

        Constants.Tts.speak("A magyarbiliárd játék típus kiválasztva", TextToSpeech.QUEUE_FLUSH, null);
    }

    public void EurokegelTextView_OnClick(View view)
    {
        Constants.GamePointLimit = 100;
        findViewById(R.id.magyarbiliardOkImageView).setVisibility(View.INVISIBLE);
        findViewById(R.id.eurokegelOkImageView).setVisibility(View.VISIBLE);
        findViewById(R.id.newGameButtonRelay).setVisibility(View.INVISIBLE);

        ((Button)findViewById(R.id.newGameButtonSingle)).setText("Eurokegel egyéni játék");
        ((Button)findViewById(R.id.newGameButtonPair)).setText("Eurokegel páros játék");

        Constants.Tts.speak("Az eurokégel játék típus kiválasztva", TextToSpeech.QUEUE_FLUSH, null);
    }

    public void NewGameSingle_OnClick(View view)
    {
        if (!IsValidDate())
            return;

        Constants.GameType = Constants.GameTypes.SINGLE;
        Intent intent = new Intent(MainActivity.this, AddPlayerActivity.class);
        startActivity(intent);
    }

    public void NewGamePair_OnClick(View view)
    {
        if (!IsValidDate())
            return;

        Constants.GameType = Constants.GameTypes.PAIR;
        Intent intent = new Intent(MainActivity.this, AddPlayerActivity.class);
        startActivity(intent);
    }

    public void NewGameRelay_OnClick(View view)
    {
        if (!IsValidDate())
            return;

        Constants.GamePointLimit = 600;
        Constants.GameType = Constants.GameTypes.RELAY;
        Intent intent = new Intent(MainActivity.this, AddPlayerActivity.class);
        startActivity(intent);
    }

    public boolean IsValidDate() {
        Date currentDate = Calendar.getInstance().getTime();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2018);
        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.DAY_OF_MONTH, 28);
        Date validDate = cal.getTime();

        return validDate.after(currentDate);
    }
}
