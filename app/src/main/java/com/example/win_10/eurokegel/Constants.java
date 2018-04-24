package com.example.win_10.eurokegel;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

class Constants {

    public enum GameTypes {
        SINGLE,
        PAIR,
        RELAY
    }

    static String PlayerOne = "";
    static String PlayerTwo = "";
    static int PlayerOnePoints = 0;
    static int PlayerTwoPoints = 0;
    static Boolean AddPoint = true;
    static int PointToAdd = 0;
    static int PointToAddPlayer = 0;
    static float DefaultTextSizeNormal = -1;
    static float DefaultTextSizeLightLarge = -1;
    static float DefaultTextSizeLarge = -1;
    static float DefaultTextSizeGreat = -1;
    static float DefaultTextSizeHuge = -1;
    static float DefaultTextSizeVeryHuge = -1;
    static int GamePointLimit = 120;
    static TextToSpeech Tts;              // TTS engine
    static boolean SpeechReady = false;
    static int playerOneSetWins = 0;
    static int playerTwoSetWins = 0;
    static String PointHistory = "<font color=#D5D5E4>22</font>";
    static GameTypes GameType;
    static Boolean RedBallShot = false;
    static int GameSetLimit = 2;
    static boolean SetEnds = false;
    static boolean GameEnds = false;
    static int ScreenWidth = 0;
    static int ScreenHeight = 0;

    public static ArrayList<String> AllPlayerSpinnerArrayEurokegel = new ArrayList<>(Arrays.asList(
            "", "Kurta László", "Csasztvan Zsolt", "Simcsik László", "Vári László",
            "Bíró Imre", "Hőgyes Benjámin", "Tomka Lajos", "Aradszki Mihály",
            "Tímár András", "Kocsor Sándor", "Szűcs Sándor", "Zsákai Tibor",
            "Kiss Attila", "Gyurkó László", "Turi Zoltán", "Kincses László",
            "Sáfián György ifj.", "Carcangiu Roberto", "Bencsik Tibor", "Salát Mátyás",
            "Dávid Gábor","Harmati László","Mészáros Tibor","Varga József","Filadelfi Mihály","Püski Sándor","Levente György",
            "Malya István","Pap Ferenc","Németh László","Kiss János","Cseman László","Halász Ferenc","Seres Péter","Komlódi József",
            "Kis Pál","Arató Zoltán","Horváth Lajos Békéscsaba","Apolónia György","Tatai Béla","Borbola István","Palyik Győző",
            "Horváth Lajos Gyoma","Berke Gyula","Csík András","Musztafa Oszman","Knyihár György","Horváth Zoltán","Gyeraj Krisztián",
            "Borbély Béla","Farkas Béla","Farkas László","Gardi Tamás","Hangya Imre","Hőgyes Attila","Hrabovszki Patrik",
            "Juhász Jácint","Kállai József","Károlyi Zoltán","Lipták Zoltán","Megyeri István","Papp Sándor","Szabados István",
            "Szabó László","Tóth Antal","Varga Attila","Varga Tivadar","Vaszkó Vince"
    ));

    public static ArrayList<String> AllPlayerSpinnerArrayMagyarbiliard = new ArrayList<>(Arrays.asList(
            "", "Zsigovics Gábor","Mészáros Tibor","Turi Zoltán","Tímár András","Zsákai Tibor","Sztojka Dezső",
            "Harmati László ","Sáfián György ifj.","Nagy Dénes csk.","Lázok István","Aradszki Mihály ","Pardi Roland",
            "Hőgyes Benjámin","Komáromi Ferenc","Kincses László","Püski Sándor","Jónás József","Dananaj István Id.",
            "Bíró Imre","Bencsik Tibor","Vári László","Varga  József","Szabó Sándor","Gali Attila",
            "Marsi Csaba","Pap Ferenc","Roberto Carcangiu","Csasztvan Zsolt","Salát Mátyás","Sztojka László ifj",
            "Bányik János","Dr. Szabó László","Sztojka László id","Túri József ","Kiss János","Varga Tivadar",
            "Komlósi Károly","Türkösi Coloman","Farkas László","Horváth Lajos","Komlódi József","Levente György",
            "Pribolyszki János cs.k","Bereczki Sándor","Vígh Mihály ","Knyihár György","Juhász Jácint","Pusztai Péter",
            "Csík András","Dávid Gábor","Malya István","Kurta László","Simcsik László","Kocsondi Tibor",
            "Hőgyes Árpád Attila","Tóth Zsolt","Kocsor Sándor","Kincses András","Kazár Pál ","Szűcs Sándor","Apollónia György",
            "Tóth Lajos","Csejtei János","Czikkely Lajos","Megyeri István","Jónás Zsolt","Bánki-Horváth Tibor",
            "Vigh Szabolcs","Békési Károly","Arató Zoltán","Kardos Ferenc","Pavlik NA","Vizes Sándor","Musztafa Oszman",
            "Hangya Imre","Tatai Béla","Klein Tamás","Papp Balázs","Filadelfi Mihály","Barna István","Dandé István",
            "Bélteki János","Csaba NA","Zsombok Attila","Gyebrovszki György ","Horváth Zoltán","Vida Zoltán","Horváth Lajos ",
            "Verebi Ferenc","Kállai József","Kruchio Sándor ","Csapó Sándor","Cseman László","Dajka Ferenc",
            "Farkasinszki Ignác","Szőke Lajos","Takács István","Simcsik Ferenc","Koppányi Viktor ","Csete Béla",
            "Halász Ferenc","Béhr Attila","Győri János ","Kovács Károly","Balázs Sándor ","Csizmadia Tibor",
            "Tonka Pál","Hencze Imre","Majernik Zoltán","Néveri László","Gyüre József","Zsilinszki Pál",
            "Pacskó Attila","Plavecz László","Szabó Márk","Kenéz Sándor","Vaszkó Vince","Farkas Béla","Borbély István",
            "Marcsó Károly","Tímár Albin","Gyeraj Krisztián ","Szabados István","Zsilinszki Mihály ","Türkösi Kálmán",
            "Sajben György ","Debreczeni Dávid","Károlyi Zoltán","Jakucs Ferenc","Héjja Bálint","Kálmán Pál",
            "Király István","Cservák László","Henc Lajos","Kis  Pál ","Varga János  ","Jancsó Ottó","Tóth Ferenc",
            "Lantos Péter","Ollai Árpád","Varga Béla","Bang Lajos","Kovács Róbert","Sípos János","Mezei János",
            "Kiss Attila","Tóth Antal","Szilágyi Dezső","Tamási Attila","Kiss-Kása Imre","Krisztof Pál","Kovács Gusztáv",
            "Uhrin István","Kergyó János","Gyurkó László","Nádra Gábor ","Ránki Ferenc","Arató Tibor","Takács Gábor",
            "Tóth Imre","Mike Mihály","Kerepeczki Zalán","Varga Mihály","Pardi Zoltán","Kocsis László","Poljak István",
            "Kovács László","Knyihár György","Kincses Andor","Látkóczki Csaba","Lévai András","Gulácsi Mihály ",
            "Coras Liviu","Kiss Tamás","Seres Péter","Hermann Pál","Laczó György id.","Demeter Sándor",
            "Agócs Mihály","Lipták Zóltán  ","James Lucas Lancaste","Boruzs Tamás","Tóth Gyula","Bálint Imre","Petrus Marcel ",
            "Laurinyecz György","Jakab János","Bende Lajos","Papp Zoltán","Varga Pál","Neibort Gréta ","Kovács Attila",
            "Kromes József","Major György","Miklós Sándor","Fekete József","Labát Viktor","Bujdosó Attila","Bujdosó László",
            "Palyik Győző","Komlósi Róbert","Csicsely Pál id.","Király János","Berke Gyula","Medvegy János",
            "Séllei Mihaly","Jónás Márkó","Mile János","Dajkó Gergő","Gera István","Bocskai László","Gyebnár Lajos",
            "Bólya János","Kovács Mihály ","Szekeres Lajos","Jámbor Árpád","Pántya János","Harter János","Gróza Péter ",
            "Kerekes Csaba","Varga Géza","Pap Sándor","Kincses Tamás","Kardos Tibor","Németh László","Katona Imre",
            "Sándor Lajos","Bárkányi János","Verebi László","Pálóczi Károly","Lázár Zsolt","Bíró Géza","Koszta József",
            "Szabó Sándor","Gergely Attila","Prentel Bálint","Pap Károly","Lipták Gábor ","Borbola István",
            "Marton János","Laurinyecz Szilveszter","Matuz Tamás","Törőcsik Richárd","Nagy Imre","Boros Csaba","Tóth Sándor",
            "Csicsely Pál","Tomka Zoltán","Kovács Róbert","Bedron Csaba","Hrabovszki Patrik ","Dénes Gábor","Németh Péter",
            "Kovács Gyula","Bartucz Mihály","Czikora József","Dénes Zsolt","Havrán Zoltán","Sepegül Dávid","Szabó Béla",
            "Tereny Mihály","Uj Zoltán","Molnár Attila","Boros Gábor","Mózes Ferenc","Tóth Jenő","Wéber Károly",
            "Magyari Nórbert ","Szabó Béla","Varga Attila ","Kristóf Gábor","Laczó György ifj.","Lovász Attila",
            "Malatyinszki András","Miszlai Dezső","Gyurkó Mihály","Bányai Gábor","Kolompáti Szabolcs","Madasiu Flaviusz",
            "Szász Zsolt","Csikós Zoltán","Schober Ottó","Vajda Soma","Szigetvári Béla","Horváth Zoltán","Kozmer László",
            "Ivanov Géza","Sós Imre ","Bálint Tibor","Hevesi Tibor","Kocsor Zoltán","Kontra Tünde","Korcsok Mihály",
            "Nagy Gábor","Szabó Sándor","Szűcs Attila","Víg Imre","Bakos Gábor ","Petri János","Szabó István","Baranya Gyula",
            "Baranya József","Molnár Gábor","Nagy Károly","Tóth Sándor","Vásári Krisztián","Vasvári Imre","Takács Gábor",
            "Varga Mihály","Nagy Attila", "Brusznyiczki Sándor", "Amatőr 1", "Amatőr 2","Amatőr 3","Kondacs Pál",
            "Klemann László", "Kutya","Juhos Zoltán", "Kovács József", "Petényi Roland"
    ));

    public static ArrayList<String> AllPlayerSpinnerArrayTeams = new ArrayList<>(Arrays.asList(
            "", "Sarkadi Pálma BK","Kiséri BK 1.","Békési Jóbarát BK","Gyomaendrődi Korona BK 4.","Magamért Telekgerendási BK","Jóbarát Pitvarosi BK",
            "Szeghalmi Safi BK 1.","Csabai BK 1.","Berényi BK","","Gyomaendrődi Korona BK 1.","Hódmezőmvásárhelyi Kinizsi BK","Berényi Szikora BK",
            "Belvárosi Cápák BK","Okányi Pelikán BK 1.","Gyomaendrődi Korona BK 2.","KLUB 120 BK","Dobozi Simák BK","Magamért VB BK 1.","Szeghalmi Safi BK 2.",
            "Sarkadi Andi BK ","Fehér Cápák BK","Tótkomlósi Bikák BK ","Csökmői BK","Nyerő Tigrisek BK","Békési Jóbarát Wolf Team BK","Gyomaendrődi Korona BK 3.",
            "Csabai BK Senior","Taroló Trió BK","Magamért Zerind BK","Sörpatika BK","Kiséri BK 2.","Pöröly Cápák BK","Magamért VB BK 2.","Tótkomlósi BK","Okányi Pelikán BK 2."


    ));

    public static void SetDefaultTextSizes(TextView tv, Resources resources)
    {
        float sourceTextSize = tv.getTextSize();
        DefaultTextSizeNormal = sourceTextSize / resources.getDisplayMetrics().density;

        DefaultTextSizeLightLarge = DefaultTextSizeNormal * 1.3f;
        DefaultTextSizeLarge = DefaultTextSizeNormal * 1.8f;
        DefaultTextSizeGreat = DefaultTextSizeNormal * 3f;
        DefaultTextSizeHuge = DefaultTextSizeNormal * 4;
        DefaultTextSizeVeryHuge = DefaultTextSizeNormal * 8;
    }

    public static void StartNewSet(Context context) {
        Constants.SetEnds = false;
        Constants.GameEnds = false;
        Constants.PlayerOnePoints = 0;
        Constants.PlayerTwoPoints = 0;
        Constants.PointHistory = "<font color=#D5D5E4>22</font>";
        ((GameActivity)context).onResume();
    }

    public static void StartNewGame(Context context) {
        Constants.SetEnds = false;
        Constants.GameEnds = false;
        Constants.playerOneSetWins = 0;
        Constants.playerTwoSetWins = 0;
        Constants.PlayerOnePoints = 0;
        Constants.PlayerTwoPoints = 0;
        Constants.PointHistory = "<font color=#D5D5E4>22</font>";
        ((GameActivity)context).BackPressed();
    }

    public static void InitSounds(Context context) {
        Tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                SpeechReady = true;
            }
        });
    }
}
