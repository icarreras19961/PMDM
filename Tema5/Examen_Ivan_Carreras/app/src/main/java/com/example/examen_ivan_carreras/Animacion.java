package com.example.examen_ivan_carreras;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Animacion extends AppCompatActivity {
    AnimationDrawable laAnimacion;
    ImageView imageViewAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animacion);

        laAnimacion = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.animacion);
        imageViewAnim = findViewById(R.id.imageViewAnim);
        imageViewAnim.setImageDrawable(laAnimacion);
        laAnimacion.start();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}