package com.example.asteriods;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ////// VARIABLES /////////
    Button game_switch;
    Button sobre_switch;
    Button preferencias_switch;
    Button score_switch;
    public static ScoreStorage scoreStorage = new ScoreStorageList();
    MediaPlayer mp;

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        //La cancion
        //Si en las preferencias esta encendida la musica se encendera la musica
        if (pref.getBoolean("musica", true)) {
            mp = MediaPlayer.create(this, R.raw.audio);
            mp.start();
        }

        TextView text = (TextView) findViewById(R.id.textView);
        Animation animacioTitulo = AnimationUtils.loadAnimation(this, R.anim.gir_amb_zoom);
        text.startAnimation(animacioTitulo);

        game_switch = findViewById(R.id.button3);

        Animation animacionGameBoton = AnimationUtils.loadAnimation(this, R.anim.apareixer);
        game_switch.startAnimation(animacionGameBoton);
        game_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameSwitch(null);
            }
        });

        sobre_switch = findViewById(R.id.button9);
        Animation animacionConfigureBoton = AnimationUtils.loadAnimation(this, R.anim.despl_dreta);
        sobre_switch.startAnimation(animacionConfigureBoton);
        sobre_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sobre_switch.startAnimation(animacioTitulo);
                sobreSwitch(null);
            }
        });

        preferencias_switch = findViewById(R.id.button8);
        Animation animacionAboutBoton = AnimationUtils.loadAnimation(this, R.anim.despl_esq);
        preferencias_switch.startAnimation(animacionAboutBoton);
        preferencias_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesSwitch(null);
            }
        });

        score_switch = findViewById(R.id.button10);
        Animation animacionScoreBoton = AnimationUtils.loadAnimation(this, R.anim.desp_baix);
        score_switch.startAnimation(animacionScoreBoton);
        score_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showScores(null);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    //El boton de jugar
    public void gameSwitch(View view) {
        Intent i = new Intent(this, GameActivity.class);
        startActivity(i);
    }

    //El boton de sobre
    public void sobreSwitch(View view) {
        Intent i = new Intent(this, SobreActivity.class);
        startActivity(i);
    }

    //El switch a la pagina preferencias
    public void preferencesSwitch(View view) {
        Intent i = new Intent(this, PreferencesActivity.class);
        startActivity(i);
    }

    //El switch a puntuacion
    public void showScores(View view) {
        Intent i = new Intent(this, Scores.class);
        startActivity(i);
    }

    //El menu "actionbar"
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.preferences) {
            //arrancar activitat preferències
            preferencesSwitch(null);
        }
        if (id == R.id.about) {
            //arrancar activitat sobre...
            sobreSwitch(null);
        }
        return super.onOptionsItemSelected(item);
    }

    //crear metodo onpause para que pare la musica
    public void onPause() {
        super.onPause();
        if (pref.getBoolean("musica", true)) {
            mp.pause();
        }
    }

    public void onResume() {
        super.onResume();
        if (pref.getBoolean("musica", true)) {
            mp.start();
        }
    }
}