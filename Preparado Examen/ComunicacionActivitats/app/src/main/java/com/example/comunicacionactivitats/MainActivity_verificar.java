package com.example.comunicacionactivitats;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity_verificar extends AppCompatActivity {
    Button si, no;
    String nombre;
    TextView textViewNombre;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verificar);


        textViewNombre = findViewById(R.id.textViewNombre);
        nombre = getIntent().getExtras().getString("KeyNom");

        textViewNombre.setText("Bienvenido " + nombre + " aceptas las condiciones?");

        si = findViewById(R.id.button_si);
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("Decision","si");
                setResult(RESULT_OK, intent);
                finish();;
            }
        });

        no = findViewById(R.id.button_no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("Decision", "no");
               setResult(RESULT_OK,i);
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