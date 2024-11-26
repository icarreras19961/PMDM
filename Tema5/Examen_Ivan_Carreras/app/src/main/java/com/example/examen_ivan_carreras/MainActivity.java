package com.example.examen_ivan_carreras;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public String getNombreScore() {
        return nombreScore;
    }

    public void setNombreScore(String nombreScore) {
        this.nombreScore = nombreScore;
    }

    public String nombreScore = "";


    Button config, score, anim;
    public static PuntuacionStorage PuntuacionStorage = new PuntuacionStorageList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        config = findViewById(R.id.buttonConfig);
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configSwitch(null);
            }
        });

        score = findViewById(R.id.buttonScore);
        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreSwitch(null);
            }
        });

        anim = findViewById(R.id.buttonAnimacion);
        anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animSwitch(null);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void configSwitch(View view) {
        Intent i = new Intent(this, Configuracion2.class);
        startActivity(i);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.configuracion) {
            //arrancar activitat preferències
            configSwitch(null);
        }
        return super.onOptionsItemSelected(item);
    }


    public void scoreSwitch(View view) {
        //Las puntuaciones no me funcionan y me he visto en la obligacion de comentar el intent para que no explote la app justo le das
        //Intent i = new Intent(this, Puntuacion.class);
        //startActivity(i);
    }

    public void animSwitch(View view) {
        Intent i = new Intent(this, Animacion.class);
        startActivity(i);
    }
}