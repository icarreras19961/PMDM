package com.example.examen2022;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;

public class VectorView extends View {
    private VectorView pentagon;

    public VectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable drawPentagon;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        Path pathPentagon = new Path();
        pathPentagon.moveTo((float) 100, (float) 100);
        pathPentagon.lineTo((float) 0, (float) 0);
        pathPentagon.lineTo((float) 0, (float) 1);
        pathPentagon.lineTo((float) 1, (float) 0.5);
        ShapeDrawable dPentagon = new ShapeDrawable(new PathShape(pathPentagon, 1, 1));
        dPentagon.getPaint().setColor(Color.WHITE);
        dPentagon.getPaint().setStyle(Paint.Style.STROKE);
        dPentagon.setIntrinsicWidth(50);
        dPentagon.setIntrinsicHeight(50);
        drawPentagon = dPentagon;

        //VectorView pentagono = new VectorView(this, drawPentagon);
        //pentagon = pentagono;
    }
}
