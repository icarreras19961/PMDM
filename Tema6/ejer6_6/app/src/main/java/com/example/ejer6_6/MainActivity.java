package com.example.ejer6_6;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText textoMensaje;
    Button botonCopia;
    TextView textoResultado;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonCopia = findViewById(R.id.buttonCopia);
        botonCopia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyTask task = new MyTask();
                task.execute();
            }
        });

    }

    class MyTask extends AsyncTask<Integer, Integer, Integer> {
        private ProgressDialog progres;
        private int nRandom;
        @Override
        protected void onPreExecute() {
            progres = new ProgressDialog(MainActivity.this);
            progres.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progres.setMessage("Copiando Seguramente");
            progres.setCancelable(true);
            progres.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    MyTask.this.cancel(true);
                }
            });
            progres.setMax(100);
            progres.setProgress(0);
            progres.show();
        }

        @Override
        protected Integer doInBackground(Integer... n) {
            for (int i = 0; i < 100 && !isCancelled(); i++) {
                SystemClock.sleep(10);
                publishProgress(i);
            }

            nRandom = (int) Math.floor(Math.random() * 10000 + 1);
            return nRandom;
        }

        @Override protected void onProgressUpdate(Integer... perc) {
            progres.setProgress(perc[0]);

        }
        @Override
        protected void onPostExecute(Integer integer) {
            progres.dismiss();

            String nombre;
            textoMensaje = findViewById(R.id.inputNombre);
            nombre = textoMensaje.getText().toString();
            textoResultado = findViewById(R.id.textViewResultado);

            textoResultado.setText("Copia de seguretat finalitzada. \nSha copia el fitxer "+nombre+" amb una mida de "+nRandom);
        }
        @Override protected void onCancelled() {
            textoResultado = findViewById(R.id.textViewResultado);
            textoResultado.setText("Cancel·lat\nNo s'ha guardat el fitxer");
        }

    }
}