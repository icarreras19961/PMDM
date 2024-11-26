package com.example.examen_ivan_carreras;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Puntuacion extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Aqui esta credo el arrayadater pero al darle a que se ejecute explota y se cierra la app
        setContentView(R.layout.activity_puntuacion);
        setListAdapter(new ArrayAdapter(this, android.R.layout.activity_list_item));
        MainActivity.PuntuacionStorage.getScoreList(10);

    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        Object o = getListAdapter().getItem(position);
        Toast.makeText(this, "Selecció: " + Integer.toString(position) + " - " + o.toString(), Toast.LENGTH_LONG).show();
    }
}