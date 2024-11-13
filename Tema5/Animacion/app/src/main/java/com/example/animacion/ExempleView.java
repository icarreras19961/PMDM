package com.example.animacion;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

public class ExempleView extends View {
    private ShapeDrawable laMevaImatge;
    public ExempleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        laMevaImatge = new ShapeDrawable(new OvalShape());
        laMevaImatge.getPaint().setColor(0xff0000ff);
    }
    @Override protected void onSizeChanged(int ample, int alt,
                                           int ample_anterior, int
                                                   alt_anterior){
        laMevaImatge.setBounds(0, 0, ample, alt);
    }
    @Override protected void onDraw(Canvas canvas) {
        laMevaImatge.draw(canvas);

    }
}
