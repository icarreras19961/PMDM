package com.example.examen2022;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button botonAnimacion;


    Button botonVector;


    Button botonMapa;
    int mapa;
    TextView textMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        botonAnimacion = findViewById(R.id.buttonAnimacio);

        botonAnimacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animacionSwitch(null);
            }
        });

        botonVector = findViewById(R.id.buttonVector);
        botonVector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vectorSwitch(null);
            }
        });

        botonMapa = findViewById(R.id.buttonMapa);
        botonMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mapaSwitch(null);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void animacionSwitch(View view) {
        //el intent
        Intent i = new Intent(this, Animacion.class);
        startActivity(i);

    }

    public void vectorSwitch(View view) {
        //El intent
        Intent i = new Intent(this, VectorView.class);
        startActivity(i);
    }

    @SuppressLint("ResourceType")
    public void mapaSwitch(View view) {
        //ell intent
        mapa++;
        textMapa = findViewById(R.id.textViewMapa);
        textMapa.setText(" " + mapa);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:39.887642,4.254319"));
        startActivity(intent);
    }
}