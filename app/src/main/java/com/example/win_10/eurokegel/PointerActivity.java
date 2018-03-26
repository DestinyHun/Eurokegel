package com.example.win_10.eurokegel;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PointerActivity extends AppCompatActivity {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointer);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        for(int i=1;i<=22;i++)
        {
            int id = getResources().getIdentifier("pointWhiteButton" + Integer.toString(i), "id", context.getPackageName());
            ((Button)findViewById(id)).setTextSize(TypedValue.COMPLEX_UNIT_SP,Constants.DefaultTextSizeLarge);

            id = getResources().getIdentifier("pointRedButton" + Integer.toString(i), "id", context.getPackageName());
            ((Button)findViewById(id)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLarge);
        }
        ((TextView)findViewById(R.id.playerOneName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((TextView)findViewById(R.id.playerTwoName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);
        ((TextView)findViewById(R.id.playerOneName)).setText(Constants.PlayerOne.toUpperCase());
        ((TextView)findViewById(R.id.playerTwoName)).setText(Constants.PlayerTwo.toUpperCase());
    }

    public void PlayerOnePointButtons_OnClick(View view)
    {
        this.SetPoints(1, Integer.parseInt(((Button)view).getText().toString()));

        super.onBackPressed();
    }

    public void PlayerTwoPointButtons_OnClick(View view)
    {
        this.SetPoints(2, Integer.parseInt(((Button)view).getText().toString()));

        super.onBackPressed();
    }

    public void SetPoints(int player, int pointsToSet)
    {
        int oldPoints = player == 1 ? Constants.PlayerOnePoints : Constants.PlayerTwoPoints;

        if (!Constants.AddPoint) {
            pointsToSet *= -1;
            Constants.AddPoint = true;
        }

        int newPoints = oldPoints + pointsToSet;

        if (newPoints > Constants.GamePointLimit)
            newPoints = Constants.GamePointLimit;
        else if (newPoints <0)
            newPoints = 0;

        if (player == 1)
            Constants.PlayerOnePoints = newPoints;
        else
            Constants.PlayerTwoPoints = newPoints;
    }
}
