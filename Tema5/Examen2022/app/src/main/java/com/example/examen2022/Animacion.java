package com.example.examen2022;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Animacion extends AppCompatActivity {
    Button buttonEmpAnim;
    Button buttonApagAnim;
    ImageView imageView;
    AnimationDrawable animacio_imagen;

    Button buttonVolverMain;


    Integer segundos;
    TextView textSegundos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.animacion);

        imageView = findViewById(R.id.imageView);
        buttonEmpAnim = findViewById(R.id.buttonEmpAnim);
        buttonApagAnim = findViewById(R.id.buttonApagAnim);
        animacio_imagen = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.animacio_imagen);

        imageView.setImageDrawable(animacio_imagen);
        buttonEmpAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animacio_imagen.start();
            }
        });
        buttonApagAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animacio_imagen.stop();
            }
        });

        buttonVolverMain = findViewById(R.id.buttonVolverMain);
        buttonVolverMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainSwitch(null);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(

                findViewById(R.id.main), (v, insets) ->

                {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });

    }
    public void mainSwitch(View view) {
        //El intent
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
