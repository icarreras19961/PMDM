package com.example.verctorasset;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Drawable laMevaImatge;

    private Drawable laMevaImatge2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ExempleView(this));
    }

    public class ExempleView extends View {
        public ExempleView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            //Dibuixar aquí
            laMevaImatge = ContextCompat.getDrawable(MainActivity.this,R.drawable
                    .baseline_kayaking_24);
            laMevaImatge.setBounds(30,30,400,400);
            laMevaImatge.draw(canvas);

            laMevaImatge2 = ContextCompat.getDrawable(MainActivity.this,R.drawable
                    .atomo);
            laMevaImatge2.setBounds(400,30,800,400);
            laMevaImatge2.draw(canvas);
        }
    }
}