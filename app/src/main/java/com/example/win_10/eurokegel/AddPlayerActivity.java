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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AddPlayerActivity extends AppCompatActivity {

    final Context context = this;
    String teamOnePlayerOne = "";
    String teamOnePlayerTwo = "";
    String teamTwoPlayerTwo = "";
    String teamTwoPlayerOne = "";
    String teamOnePlayerOneFullName = "";
    String teamOnePlayerTwoFullName = "";
    String teamTwoPlayerTwoFullName = "";
    String teamTwoPlayerOneFullName = "";

//    private ArrayList<String> allPlayerSpinnerArray = new ArrayList<>(Arrays.asList(
//            "", "Kurta László", "Csasztvan Zsolt", "Simcsik László", "Vári László",
//            "Bíró Imre", "Hőgyes Benjámin", "Tomka Lajos", "Aradszki Mihály",
//            "Tímár András", "Kocsor Sándor", "Szűcs Sándor", "Zsákai Tibor",
//            "Kiss Attila", "Gyurkó László", "Turi Zoltán", "Kincses László",
//            "Ifj. Sáfián György", "Carcangiu Roberto", "Bencsik Tibor", "Salát Mátyás"
//    ));

    private ArrayList<String> allPlayerSpinnerArray = new ArrayList<>(Arrays.asList(
            "", "Berke Gyula", "Gyurkó László", "Gyurkó Mihály", "Palyik Győzike", "Németh László"
            ,"Filadelfi Mihály","Kiss János", "Sztojka Dezső", "Dandé István", "Békési Károly", "Komáromi Ferenc",
            "ifj. Sztojka László"
    ));

    private ArrayList<String> playedSetArray = new ArrayList<>(Arrays.asList(
            "2","3"
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Constants.GameType == Constants.GameTypes.PAIR)
            setContentView(R.layout.activity_add_player_pair);
        else
            setContentView(R.layout.activity_add_player);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ((Button) findViewById(R.id.beginGameButton)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeNormal);

        ((TextView) findViewById(R.id.playerOneName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLightLarge);
        ((TextView) findViewById(R.id.playerTwoName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLightLarge);
        ((TextView) findViewById(R.id.playedSetNumber)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLightLarge);

        //SELECT SETS SPINNER
        if (Constants.GameType == Constants.GameTypes.RELAY) {
            playedSetArray = new ArrayList<>(Arrays.asList(
                    "5"
            ));

            allPlayerSpinnerArray = new ArrayList<>(Arrays.asList(
                    "", "Doboz", "VB Söröző"
            ));

            ((TextView) findViewById(R.id.playerOneName)).setText("FEHÉR CSAPAT:");
            ((TextView) findViewById(R.id.playerTwoName)).setText("SÖTÉT CSAPAT:");
        }
        Spinner spinner = findViewById(R.id.playedSetNumberSpinner);
        Collections.sort(playedSetArray);
        SpinnerAdapter spinnerArrayAdapter = new SpinnerAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, playedSetArray.toArray(new String[0]));
        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Constants.GameSetLimit = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        if (Constants.GameType == Constants.GameTypes.PAIR) {
            //SELECT PLAYER ONE SPINNER
            spinner = findViewById(R.id.chooseTeamOnePlayerOneSpinner);
            Collections.sort(allPlayerSpinnerArray);
            spinnerArrayAdapter = new SpinnerAdapter(this,
                    android.R.layout.simple_spinner_dropdown_item, allPlayerSpinnerArray.toArray(new String[0]));
            spinner.setAdapter(spinnerArrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    teamOnePlayerOneFullName = adapterView.getItemAtPosition(i).toString();
                    if (teamOnePlayerOneFullName.length() > 0)
                        teamOnePlayerOne = teamOnePlayerOneFullName.split("\\s+")[0];
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            //SELECT PLAYER TWO SPINNER
            spinner = findViewById(R.id.chooseTeamOnePlayerTwoSpinner);
            spinnerArrayAdapter = new SpinnerAdapter(this,
                    android.R.layout.simple_spinner_dropdown_item, allPlayerSpinnerArray.toArray(new String[0]));
            spinner.setAdapter(spinnerArrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    teamOnePlayerTwoFullName = adapterView.getItemAtPosition(i).toString();
                    if (teamOnePlayerTwoFullName.length() > 0)
                        teamOnePlayerTwo = teamOnePlayerTwoFullName.split("\\s+")[0];
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            //SELECT PLAYER ONE SPINNER
            spinner = findViewById(R.id.chooseTeamTwoPlayerOneSpinner);
            Collections.sort(allPlayerSpinnerArray);
            spinnerArrayAdapter = new SpinnerAdapter(this,
                    android.R.layout.simple_spinner_dropdown_item, allPlayerSpinnerArray.toArray(new String[0]));
            spinner.setAdapter(spinnerArrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    teamTwoPlayerOneFullName = adapterView.getItemAtPosition(i).toString();
                    if (teamTwoPlayerOneFullName.length() > 0)
                        teamTwoPlayerOne = teamTwoPlayerOneFullName.split("\\s+")[0];
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            //SELECT PLAYER TWO SPINNER
            spinner = findViewById(R.id.chooseTeamTwoPlayerTwoSpinner);
            spinnerArrayAdapter = new SpinnerAdapter(this,
                    android.R.layout.simple_spinner_dropdown_item, allPlayerSpinnerArray.toArray(new String[0]));
            spinner.setAdapter(spinnerArrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    teamTwoPlayerTwoFullName = adapterView.getItemAtPosition(i).toString();
                    if (teamTwoPlayerTwoFullName.length() > 0)
                        teamTwoPlayerTwo = teamTwoPlayerTwoFullName.split("\\s+")[0];
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        } else {
            ((TextView) findViewById(R.id.playerOneName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLightLarge);
            ((TextView) findViewById(R.id.playerTwoName)).setTextSize(TypedValue.COMPLEX_UNIT_SP, Constants.DefaultTextSizeLightLarge);

            //SELECT PLAYER ONE SPINNER
            spinner = findViewById(R.id.choosePlayerOneSpinner);
            Collections.sort(allPlayerSpinnerArray);
            spinnerArrayAdapter = new SpinnerAdapter(this,
                    android.R.layout.simple_spinner_dropdown_item, allPlayerSpinnerArray.toArray(new String[0]));
            spinner.setAdapter(spinnerArrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Constants.PlayerOne = adapterView.getItemAtPosition(i).toString();
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });

            //SELECT PLAYER TWO SPINNER
            spinner = findViewById(R.id.choosePlayerTwoSpinner);
            spinnerArrayAdapter = new SpinnerAdapter(this,
                    android.R.layout.simple_spinner_dropdown_item, allPlayerSpinnerArray.toArray(new String[0]));
            spinner.setAdapter(spinnerArrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Constants.PlayerTwo = adapterView.getItemAtPosition(i).toString();
                }

                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
        }

        findViewById(R.id.beginGameButton).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (Constants.GameType == Constants.GameTypes.PAIR) {
                    if (teamOnePlayerOne.equals("")) {
                        Constants.Tts.speak("Add meg az első csapat első játékosának nevét", TextToSpeech.QUEUE_FLUSH, null);
                        return;
                    } else if (teamOnePlayerTwo.equals("")) {
                        Constants.Tts.speak("Add meg az első csapat második játékosának nevét", TextToSpeech.QUEUE_FLUSH, null);
                        return;
                    } else if (teamTwoPlayerOne.equals("")) {
                        Constants.Tts.speak("Add meg a második csapat első játékosának nevét", TextToSpeech.QUEUE_FLUSH, null);
                        return;
                    } else if (teamTwoPlayerTwo.equals("")) {
                        Constants.Tts.speak("Add meg a második csapat második játékosának nevét", TextToSpeech.QUEUE_FLUSH, null);
                        return;
                    }
                    else if (teamOnePlayerOneFullName.equals(teamOnePlayerTwoFullName) || teamOnePlayerOneFullName.equals(teamTwoPlayerOneFullName) ||
                            teamOnePlayerOneFullName.equals(teamTwoPlayerTwoFullName) || teamOnePlayerTwoFullName.equals(teamTwoPlayerOneFullName) ||
                            teamOnePlayerTwoFullName.equals(teamTwoPlayerTwoFullName) || teamTwoPlayerOneFullName.equals(teamTwoPlayerTwoFullName)) {
                        Constants.Tts.speak("Egy játékos neve csak egyszer adható meg", TextToSpeech.QUEUE_FLUSH, null);
                        return;
                    }
                    Constants.PlayerOne = teamOnePlayerOne + " - " + teamOnePlayerTwo;
                    Constants.PlayerTwo = teamTwoPlayerOne + " - " + teamTwoPlayerTwo;
                }
                else {
                    if (Constants.PlayerOne.equals("")) {
                        Constants.Tts.speak("Add meg az első játékos nevét", TextToSpeech.QUEUE_FLUSH, null);
                        return;
                    } else if (Constants.PlayerTwo.equals("")) {
                        Constants.Tts.speak("Add meg a második játékos nevét", TextToSpeech.QUEUE_FLUSH, null);
                        return;
                    }
                    else if (Constants.PlayerTwo.equals(Constants.PlayerOne)) {
                        Constants.Tts.speak("Egy játékos neve csak egyszer adható meg", TextToSpeech.QUEUE_FLUSH, null);
                        return;
                    }
                }

                Intent intent = new Intent(AddPlayerActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }
}
