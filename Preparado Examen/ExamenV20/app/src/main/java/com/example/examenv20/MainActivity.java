package com.example.examenv20;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

    Button vector, animacion, mapa, actions;
    int nmapa, nvector;
    TextView textViewAnim, textViewVector, textViewMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        animacion = findViewById(R.id.buttonAnimacion);
        Animation aparecer = AnimationUtils.loadAnimation(this, R.anim.aparecer);
        animacion.startAnimation(aparecer);
        animacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animacionSwitch(null);
            }
        });

        textViewVector = findViewById(R.id.textViewVector);
        vector = findViewById(R.id.buttonVector);
        vector.startAnimation(aparecer);
        vector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nvector++;
                textViewVector.setText("N intentos: " + nvector);
                vectorSwitch(null);
            }
        });

        textViewMapa = findViewById(R.id.textViewMapa);
        mapa = findViewById(R.id.buttonMapa);
        mapa.startAnimation(aparecer);
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nmapa++;
                textViewMapa.setText("N intentos: " + nmapa);

                mapaSwitch(null);
            }
        });

        actions = findViewById(R.id.buttonActions);
        actions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actionsSwitch(null);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void animacionSwitch(View view) {
        Intent i = new Intent(this, animacion.class);
        startActivityForResult(i, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        textViewAnim = findViewById(R.id.textViewAnim);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            String res = data.getStringExtra("Segundos_anim");
            textViewAnim.setText("Segundos en la animacion: " + res);
        }
    }

    public void vectorSwitch(View view) {
        Intent i = new Intent(this, Vector.class);
        //Pongo el forResult para que luego al volver sigan apareciendo los numeros como estan sino es como si se recargara el proyecto y desaparecen
        startActivityForResult(i, 1234);
    }

    public void mapaSwitch(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:39.887642,4.254319"));
        startActivity(i);
    }

    public void actionsSwitch(View view) {
        Intent i = new Intent(this, Actions.class);
        startActivity(i);
    }

    public void preferencesSwitch(View view) {
        Intent i = new Intent(this, PrefecencesActivity.class);
        startActivity(i);
    }

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

        return super.onOptionsItemSelected(item);
    }

}