package com.example.win_10.eurokegel;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //Set player names
        ((TextView)findViewById(R.id.playerOneName)).setText(Constants.PlayerOne.toUpperCase());
        ((TextView)findViewById(R.id.playerTwoName)).setText(Constants.PlayerTwo.toUpperCase());
        ((TextView)findViewById(R.id.playerOneName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.text_size));
        ((TextView)findViewById(R.id.playerTwoName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.text_size));
        ((TextView)findViewById(R.id.playerOnePoint)).setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.point_size));
        ((TextView)findViewById(R.id.playerPointSeparator)).setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.point_size));
        ((TextView)findViewById(R.id.playerTwoPoint)).setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.point_size));
        ((Button)findViewById(R.id.addPoint)).setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.button_text_size));
        ((Button)findViewById(R.id.removePoint)).setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.button_text_size));
        ((Button)findViewById(R.id.history)).setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.button_text_size));
        ((Button)findViewById(R.id.newSet)).setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getDimension(R.dimen.button_text_size));
    }

    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        ((TextView)findViewById(R.id.playerOnePoint)).setText(String.format(Locale.ENGLISH, "%d",Constants.PlayerOnePoints));
        ((TextView)findViewById(R.id.playerTwoPoint)).setText(String.format(Locale.ENGLISH, "%d",Constants.PlayerTwoPoints));
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
}
