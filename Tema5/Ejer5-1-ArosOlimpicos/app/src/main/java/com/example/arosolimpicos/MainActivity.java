package com.example.arosolimpicos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
            //Aro azul
            Paint pinzell = new Paint();
            pinzell.setColor(Color.BLUE);
            pinzell.setStrokeWidth(20);
            pinzell.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(350, 800, 100, pinzell);

            Paint pinzell1 = new Paint();
            pinzell.setColor(Color.BLACK);
            pinzell.setStrokeWidth(20);
            pinzell.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(550, 800, 100, pinzell);

            Paint pinzell2 = new Paint();
            pinzell.setColor(Color.RED);
            pinzell.setStrokeWidth(20);
            pinzell.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(750, 800, 100, pinzell);

            Paint pinzell3 = new Paint();
            pinzell.setColor(Color.YELLOW);
            pinzell.setStrokeWidth(20);
            pinzell.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(450, 900, 100, pinzell);

            Paint pinzell4 = new Paint();
            pinzell.setColor(Color.GREEN);
            pinzell.setStrokeWidth(20);
            pinzell.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(650, 900, 100, pinzell);

        }
    }
}