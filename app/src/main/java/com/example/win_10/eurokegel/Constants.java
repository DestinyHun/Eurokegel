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
import java.text.Collator;

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
    static int HomeVersion = 0;
    static Collator collator = Collator.getInstance(new Locale("hu"));

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
            "", "Aradszki Mihály","Barth Károly","Bencsik Tibor","Bíró Imre","Csasztvan Zsolt","Dananaj István id.",
            "Gali Attila","Harmati László","Hőgyes Benjámin","Hrabovszki András","Sáfián György ifj.","Jónás József",
            "Kincses László","Komáromi Ferenc","Lázok István","Marsi Csaba","Mészáros Tibor","Nagy Dénes","Pap Ferenc",
            "Pardi Roland","Püski Sándor","Carcangiu Roberto","Salát Mátyás","Szabó Sándor Safi","Sztojka Dezső",
            "Sztojka László","Tímár András","Turi Zoltán","Varga József","Vári László","Zsákai Tibor","Zsigovics Gábor",
            "Kiss Attila","Mike Mihály","Nagy Attila","Szűcs Sándor","Takács Gábor","Tomka Lajos","Károlyi Zoltán",
            "Komlódi József","Kurta László","Simcsik Ferenc","Tatai Béla","Kazár Pál","Lipták Gábor","Nádra Gábor",
            "Csík András","Csikós Zoltán","Gardi Tamás","Levente György","Pap Károly","Kenéz Sándor","Türkösi Coloman",
            "Boruzs Tamás","Vígh Mihály","Csejtei János","Kocsor Sándor","Musztafa Oszman","Plavecz László","Békési Károly",
            "Dandé István","Sztojka Csaba","Kiss János","Szabó István","Berke Gyula","Filadelfi Mihály","Gyurkó László",
            "Gyurkó Mihály","Németh László","Palyik Győző","Bánki-Horváth Tibor","Cservák László","Dajka Ferenc",
            "Kardos Ferenc","Mezei János","Sós Imre","Vígh Szabolcs","Agócs Mihály","Bányik János","Csete Béla",
            "Horváth Zoltán Klub120","Kozmer László.Seres Péter","Tóth Lajos","Varga Géza","Apollónia György",
            "Czikkely Lajos","Dávid Gábor","Hangya Imre","Horváth Zoltán Korona","Bálint Imre","Csapó Sándor",
            "Csizmadia Tibor","Harter János","Jakab János","Jakucs Ferenc","Kincses András","Komlósi Károly",
            "Kovács Károly","Marcsó Károly","Pusztai Péter","Bakos Gábor","Bólya János","Laczó György Id.",
            "Kerepeczki Zalán","Kovács Gusztáv","Laurinyecz György","Laurinyecz Szilveszter","Petri János",
            "Uhrin István","Varga Mihály","Bányai Gábor.Boros Csaba","Coras Liviu","Király János","Kolompáti Szabolcs",
            "Komlósi Róbert","Madasiu Flaviusz.Marton János","Pálóczi Károly","Prentel Bálint","Sándor Lajos","Szász Zsolt",
            "Bang Lajos","Barna István","Boruzs Gábor","Sáfián György Id.","Kincses Andor","Kovács Gyula","Szőke Lajos",
            "Vizes Sándor","Bárkányi János.Dénes Gábor","Fekete József","Kardos Tibor","Katona Imre","Lázár Zsolt",
            "Mile János","Németh Péter","Hrabovszki Patrik","Jámbor Árpád","Karlovszki András","Kerekes Csaba",
            "Koppányi Viktor","Kruchio Sándor","Petrus Marcel","Pribolyszki János","Turi József","Baranya Gyula",
            "Baranya József","Hermann Pál","Kovács Attila","Kovács Róbert Csökmő","Molnár Gábor","Nagy Károly",
            "Pántya János","Tóth Gyula","Tóth Imre","Tóth Sándor","Vasvári Imre","Vásári Krisztián","Demeter Sándor",
            "Gera István","Ivanov Géza","Kiss-Kása Imre","Pardi Zoltán","Tamási attila","Tóth Ferenc","Borbola István",
            "Bujdosó László","Laczó György Ifj.","Kiss Tamás","Knyihár György VB","Krisztof Pál","Labát Viktor",
            "Lovász Attila","Malatyinszki András","Miszlai Dezső","Balázs Sándor","Gulácsi Mihály","Győri János",
            "Horváth Lajos Tigrisek","Kállai József","Lipták Zoltán","Magyari Norbert","Szabó Béla","Varga Attila",
            "Bartucz Mihály","Jancsó Ottó","Kocsondi Tibor","Kovács László","Lévai András","Majernik Zoltán",
            "Néveri László","Sípos János","Takács István","Tonka Pál","Arató Tibor","Arató Zoltán","Bedron Csaba",
            "Gyüre József","Malya István","Poljak István","Szekeres Lajos","Lakatos Antal","Gróza Péter","Gyebrovszki György",
            "Gyeraj Krisztián","Kis Pál","Knyihár György Csabai","Miklós Sándor","Sajben György","Varga János",
            "Zsilinszki Mihály","Zsilinszki Pál","Szabó László","Farkas Béla","Farkas László","Juhász Jácint",
            "Megyeri István","Pap Sándor","Szabados István","Tóth Antal","Varga Tivadar","Vaszkó Vince","Farkasinszki Ignác",
            "Gyebnár Lajos","Hencz Lajos","Horváth Lajos Korona","Jónás zsolt","Körösparti János","Szigetvári Béla","Tímár Albin",
            "Borbély Béla","Bujdosó Attila","Hőgyes Attila","kincses Tamás","Molnár Attila","Nagy Imre","Szabó Sándor Pelikán",
            "Verebi Ferenc","Verebi László","Bocskai László","Cseman László","Halász Ferenc","Héjja Bálint","Kergyó János",
            "Kovács Mihály","Kovábs Róbert TBK","Bálint Tibor","Bereczki Sándor","Hevesi Tibor","Kocsor Zoltán","Kontra Tünde",
            "Korcsok Mihály","Nagy Gábor","Szabó Sándor Jóbarát","Szűcs Attila","Víg Imre","Kálmán Pál","Király István",
            "Lantos Péter","Ollai Árpád","Szilágyi Dezső","Tóth Sándor","Bukva Imre","Dajkó Gergő","Gergely Attila",
            "James Lucas Lancaster","Jónás Márkó","Látkóczki Csaba","Neibort Gréta","Schober Ottó","Szabó Márk",
            "Törőcsik Richárd","Vajda Soma","Béhr Attila","Bende Lajos","Borbély István","Boros Gábor","Kocsis László",
            "Mózes Ferenc","Papp Zoltán","Tóth Jenő","Varga Pál","Wéber Károly","Czikora József","Dénes Zsolt",
            "Dócza Zsigmond","Havrán Zoltán","Matuz Tamás","Sepegül Dávid","Szabó Béla","Szabó Sándor Pitvaros",
            "Tereny Mihály","Uj Zoltán","Bíró Géza","Csicsely Pál Id.","Csicsly Pál Ifj.","Koszta József","Medvegy János",
            "Ránki Ferenc","Séllei Mihály","Tomka Zoltán","Kolárovszki Gábor","Hencze Imre","Kristóf Gábor","Pacskó Attila",
            "Türkösi Kálmán","Varga Béla","Tóth Zsolt","Pavlik János","Bélteki János","Zsombok Attila","Klein Tamás",
            "Papp Balázs","Debreczeni Dávid","Kromes József","Major György","Vida Zoltán","Kaszás Sándor","Farkas Sándor",
            "Megyeri József","Pálinkás Mihály","Forró Balázs","Fekete Lajos","Zsigovics József","Bálint Tibor",
            "Brusznyiczki Sándor","Amatőr Versenyző 1","Amatőr Versenyző 2","Amatőr Versenyző 3","Kondacs Pál",
            "Klemann László", "Kutya Teve","Juhos Zoltán", "Kovács József", "Petényi Roland"
    ));

    public static ArrayList<String> AllPlayerSpinnerArrayMagyarbiliardHome = new ArrayList<>(Arrays.asList(
            "","Sanyi", "Zsolti", "Bandi","Palkó",
            "Laci", "Kutya","Zoli", "Józsi", "Roli", "Béla", "Peti", "Kicsi", "Soma"));

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
