package com.example.examenv20;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Actions extends AppCompatActivity {
    Button web, dial, view, foto, salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actions);

        web = findViewById(R.id.buttonWeb);
        Animation baj_der = AnimationUtils.loadAnimation(this,R.anim.desp_baj_der);
        web.startAnimation(baj_der);
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.es"));
                startActivity(i);
            }
        });

        dial = findViewById(R.id.buttonDial);
        Animation arri_der = AnimationUtils.loadAnimation(this, R.anim.desp_arri_der);
        dial.startAnimation(arri_der);
        dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:679283291"));
                startActivity(i);
            }
        });

        view = findViewById(R.id.buttonView);
        Animation arri_iz = AnimationUtils.loadAnimation(this, R.anim.desp_arri_iz);
        view.startAnimation(arri_iz);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:39.88230352144266, 4.300963810372938"));
                startActivity(i);
            }
        });

        foto = findViewById(R.id.buttonFoto);
        Animation baj_iz = AnimationUtils.loadAnimation(this, R.anim.desp_baj_iz);
        foto.startAnimation(baj_iz);
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(i);
            }
        });

        salir = findViewById(R.id.buttonSalir);
        Animation gigante = AnimationUtils.loadAnimation(this,R.anim.aparece_gigante_rotador);
        salir.startAnimation(gigante);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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