package com.example.examenv20;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
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

public class animacion extends AppCompatActivity {

    Button empezar, parar, salir;
    ImageView ViewAnimacion;
    AnimationDrawable laAnimacion;

    long segundos;
    long tiempo = 0;
    TextView textViewSegundos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animacion);


        laAnimacion = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.animacion_imagen);

        ViewAnimacion = findViewById(R.id.imageAnim);
        ViewAnimacion.setImageDrawable(laAnimacion);


        empezar = findViewById(R.id.buttonEmpAnim);
        empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                laAnimacion.start();
                segundos = System.currentTimeMillis();
            }
        });

        textViewSegundos = findViewById(R.id.textViewSegundos);
        parar = findViewById(R.id.buttonPararAnim);
        parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                laAnimacion.stop();
                segundos = System.currentTimeMillis() - segundos;
                tiempo = segundos + tiempo;
                textViewSegundos.setText("Segundos: " + (tiempo / 1000));
            }
        });

        salir = findViewById(R.id.buttonSalir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("Segundos_anim", (tiempo / 1000) + " ");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

}