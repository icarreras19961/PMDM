package com.example.comunicacionactivitats;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button verificar;
    EditText editTextNombre;
    String nom;
    TextView textViewResultado;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextNombre = findViewById(R.id.editTextNombre);
        textViewResultado = findViewById(R.id.textViewResultado);
        verificar = findViewById(R.id.button_verificar);
        verificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity_verificar(null);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void activity_verificar(View view) {
        Intent i = new Intent(this, MainActivity_verificar.class);
        nom = editTextNombre.getText().toString();
        i.putExtra("KeyNom", nom);
        startActivityForResult(i, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == 1234 && resultCode == RESULT_OK)) {
            String res = data.getStringExtra("Decision");
            textViewResultado.setText("Resultado: " + res);
        }

    }
}