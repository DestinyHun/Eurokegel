package com.example.win_10.eurokegel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AddPlayerActivity extends AppCompatActivity {

    final Context context = this;

    private ArrayList<String> allPlayerSpinnerArray = new ArrayList<>(Arrays.asList(
            "", "Kurta László", "Csasztvan Zsolt", "Simcsik László", "Vári László",
            "Bíró Imre", "Hőgyes Benjámin", "Tomka Lajos", "Aradszki Mihály",
            "Tímár András", "Kocsor Sándor", "Szűcs Sándor", "Zsákai Tibor",
            "Kiss Attila", "Gyurkó László", "Turi Zoltán", "Kincses László",
            "Ifj. Sáfián György", "Carcangiu Roberto", "Bencsik Tibor", "Salát Mátyás"
    ));

    private ArrayList<String> actualPlayersSpinnerArray = new ArrayList<>(Arrays.asList(
            new String[]{""}
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ((Button)findViewById(R.id.beginGameButton)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);

        //SELECT PLAYER ONE SPINNER
        Spinner spinner = findViewById(R.id.choosePlayerOneSpinner);
        Collections.sort(allPlayerSpinnerArray);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, allPlayerSpinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                actualPlayersSpinnerArray = new ArrayList<>(Arrays.asList(
                        "", adapterView.getItemAtPosition(i).toString(), ((Spinner) findViewById(R.id.choosePlayerTwoSpinner)).getSelectedItem().toString()
                ));
                SetSelectedPlayersSpinner();
                Constants.PlayerOne = adapterView.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //SELECT PLAYER TWO SPINNER
        spinner = findViewById(R.id.choosePlayerTwoSpinner);
        spinnerArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, allPlayerSpinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);

        this.SetSelectedPlayersSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                actualPlayersSpinnerArray = new ArrayList<>(Arrays.asList(
                        "", adapterView.getItemAtPosition(i).toString(), ((Spinner) findViewById(R.id.choosePlayerOneSpinner)).getSelectedItem().toString()
                ));
                SetSelectedPlayersSpinner();
                Constants.PlayerTwo = adapterView.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //SELECT BEGINNER PLAYER SPINNER
        spinner = findViewById(R.id.chooseBeginnerPlayerSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Constants.BeginnerPlayer = adapterView.getItemAtPosition(i).toString();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        findViewById(R.id.beginGameButton).setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {

//                Constants.PlayerOne = "Aradszki Mihály";
//                Constants.PlayerTwo = "Bencsik Tibor";
//                Constants.BeginnerPlayer = "Bencsik Tibor";


                Resources res = getResources();
                if (Constants.PlayerOne.equals("")) {
                    Constants.Tts.speak("Továbblépés előtt add meg az első játékos nevét", TextToSpeech.QUEUE_FLUSH, null);
                    return;
                }
                else if (Constants.PlayerTwo.equals("")) {
                    Constants.Tts.speak("Továbblépés előtt add meg a második játékos nevét", TextToSpeech.QUEUE_FLUSH, null);
                    return;
                }
                else if (Constants.BeginnerPlayer.equals("")) {
                    Constants.Tts.speak("Továbblépés előtt add meg annak a játékosnak nevét, aki kezdeni fogja a játékot", TextToSpeech.QUEUE_FLUSH, null);
                    return;
                }
                else if (Constants.PlayerOne.equals(Constants.PlayerTwo)) {
                    Constants.Tts.speak("Az két játékos neve nem lehet azonos", TextToSpeech.QUEUE_FLUSH, null);
                    return;
                }

                if (!Constants.PlayerOne.equals(Constants.BeginnerPlayer))
                {
                    String tempName = Constants.PlayerOne;
                    Constants.PlayerOne = Constants.PlayerTwo;
                    Constants.PlayerTwo = tempName;
                }

                Intent intent = new Intent(AddPlayerActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }

    private void SetSelectedPlayersSpinner() {
        Spinner spinner = findViewById(R.id.chooseBeginnerPlayerSpinner);
        Collections.sort(actualPlayersSpinnerArray);
        ArrayAdapter<String>spinnerArrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, actualPlayersSpinnerArray);
        spinner.setAdapter(spinnerArrayAdapter);
    }

}
