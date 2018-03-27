package com.example.win_10.eurokegel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Object t = getResources();
        Constants.SetDefaultTextSizes(new TextView(this), getResources());
        ((Button)findViewById(R.id.newGameButton)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);

        findViewById(R.id.newGameButton).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, AddPlayerActivity.class);
                startActivity(intent);
            }
        });
        Constants.InitSounds(MainActivity.this);
    }

    private void ReadMe(String szöveg)
    {
        Constants.ActualMediaPlayers = Constants.writerSays.get(szöveg);
        Constants.PlayedSoundNumber = 0;
        PlaySounds();
    }

    private void PlaySounds() {

        MediaPlayer actualMediaPlayer = Constants.ActualMediaPlayers.get(Constants.PlayedSoundNumber);

        actualMediaPlayer.start();
        actualMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                Constants.PlayedSoundNumber++;
                if (Constants.PlayedSoundNumber < Constants.ActualMediaPlayers.size())
                    PlaySounds();
            }
        });
    }

    public void MagyarbiliardTextView_OnClick(View view)
    {
        Constants.GamePointLimit = 120;
        findViewById(R.id.magyarbiliardOkImageView).setVisibility(View.VISIBLE);
        findViewById(R.id.eurokegelOkImageView).setVisibility(View.INVISIBLE);

        ReadMe("9kellasotetnek");
    }

    public void EurokegelTextView_OnClick(View view)
    {
        Constants.GamePointLimit = 100;
        findViewById(R.id.magyarbiliardOkImageView).setVisibility(View.INVISIBLE);
        findViewById(R.id.eurokegelOkImageView).setVisibility(View.VISIBLE);
    }
}
