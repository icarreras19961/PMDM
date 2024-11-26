package com.example.textoencirculo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
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
            Path cami = new Path();
            cami.addCircle(250, 250, 200, Path.Direction.CW);//El ultimo parametro cambia el sentido del texto
            canvas.drawColor(Color.WHITE);
            Paint pinzell = new Paint();
            pinzell.setColor(Color.BLUE);
            pinzell.setStrokeWidth(10);
            pinzell.setStyle(Paint.Style.STROKE);
            canvas.drawPath(cami, pinzell);
            pinzell.setStrokeWidth(1);
            pinzell.setStyle(Paint.Style.FILL);
            pinzell.setTextSize(40);
            pinzell.setTypeface(Typeface.SANS_SERIF);
            canvas.drawTextOnPath("Desenvolupament d'aplicacions per a mòbils amb Android", cami, 90, -20, pinzell);

        }
    }
}