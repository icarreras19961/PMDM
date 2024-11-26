package com.example.asteriods;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Scores extends ListActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        setListAdapter(new MyAdapter(this, MainActivity.scoreStorage.getScoreList(10)));
    }

    @Override
    protected void onListItemClick(ListView listView,
                                   View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        Object o = getListAdapter().getItem(position);
        Toast.makeText(this, "Selecció: " + Integer.toString(position)
                + " - " + o.toString(), Toast.LENGTH_LONG).show();
    }
}