package com.example.asteriods;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

class AsteroidsGraphic {
    private Drawable drawable; //Imatge que dibuixarem
    private double cenX, cenY; //Posició del centre de la imatge
    private double incX, incY; //Velocitat desplaçament
    private int rotAngle, rotSpeed;//Àngle i velocitat de rotació
    private int width, height; //Dimensions de la imatge
    private int collisionRadius; //Per determinar colisió
    //On dibuixam el gràfic (emprada a view.ivalidate)
    private View view;
    // Per determinar l'espai a borrar (view.ivalidate)
    public static final int MAX_VELOCITY = 20;

    public AsteroidsGraphic(View view, Drawable drawable) {
        this.view = view;
        this.drawable = drawable;
        width = drawable.getIntrinsicWidth();
        height = drawable.getIntrinsicHeight();
        collisionRadius = (height + width) / 4;
    }

    public void drawGraphic(Canvas canvas) {
        canvas.save();
        int x = (int) (cenX + width / 2);
        int y = (int) (cenY + height / 2);
        canvas.rotate((float) rotAngle, (float) x, (float) y);
        drawable.setBounds((int) cenX, (int) cenY,
                (int) cenX + width, (int) cenY + height);
        drawable.draw(canvas);
        canvas.restore();
        int rInval = (int) Math.hypot(width, height) / 2 + MAX_VELOCITY;
        view.invalidate(x - rInval, y - rInval, x + rInval, y + rInval);
    }

    public double getCenX() {
        return cenX;
    }

    public void setCenX(double cenX) {
        this.cenX = cenX;
    }

    public double getCenY() {
        return cenY;
    }

    public void setCenY(double cenY) {
        this.cenY = cenY;
    }

    public double getIncX() {
        return incX;
    }

    public void setIncX(double incX) {
        this.incX = incX;
    }

    public double getIncY() {
        return incY;
    }

    public void setIncY(double incY) {
        this.incY = incY;
    }

    public int getRotAngle() {
        return rotAngle;
    }

    public void setRotAngle(int rotAngle) {
        this.rotAngle = rotAngle;
    }

    public int getRotSpeed() {
        return rotSpeed;
    }

    public void setRotSpeed(int rotSpeed) {
        this.rotSpeed = rotSpeed;
    }

    public void updatePos(double factor) {

        cenX += incX * factor;
// Si sortim de la pantalla, corregim posició
        if (cenX < -width / 2) {
            cenX = view.getWidth() - width / 2;
        }
        if (cenX > view.getWidth() - width / 2) {
            cenX = -width / 2;
        }
        cenY += incY * factor;
        if (cenY < -height / 2) {
            cenY = view.getHeight() - height / 2;
        }
        if (cenY > view.getHeight() - height / 2) {
            cenY = -height / 2;
        }
        rotAngle += rotSpeed * factor; //Actualitzam angle
    }

    public double distance(AsteroidsGraphic g) {
        return Math.hypot(cenX - g.cenX, cenY - g.cenY);
    }

    public boolean checkCollision(AsteroidsGraphic g) {
        return (distance(g) < (collisionRadius + g.collisionRadius));
    }
}