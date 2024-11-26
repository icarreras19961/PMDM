package com.example.asteriods;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class AsteroidsView extends View {
    // //// ASTEROIDS //////
    private List<AsteroidsGraphic> asteroids; // Vector amb els Asteroides
    private int numAsteroids = 5; // Número inicial d'asteroides
    private int numFragments = 3; // Fragments en que es divideix
    /////// SPACESHIP //////
    private AsteroidsGraphic ship; // Gràfic de la nau
    private int angleShip; // Angle de gir de la nau
    private float accelShip; // Augment de velocitat
    private static final double SHIP_MAX_SPEED = 50;
    // Increment estàndar de gir i acceleració
    private static final int STEPSIZE_ROT_SHIP = 5;
    private static final float STEPSIZE_ACCEL_SHIP = 0.5f;
    // //// THREAD I TEMPS //////
    // Thread encarregat de processar el joc
    private GameThread thread = new GameThread();
    // Cada quan volem processar canvis (ms)
    private static int ANIM_INTERVAL = 50;
    // Quan es va realitzar el darrer procés
    private long prevUpdate = 0;

    ////// ON TOUCH METHOD ////////
    private float mX = 0, mY = 0;
    private boolean fire = false;

    public AsteroidsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Drawable drawableShip, drawableAsteroid, drawableMissile;
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (pref.getString("grafics", "1").equals("0")) {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            //asteroides vector
            Path pathAsteroid = new Path();
            pathAsteroid.moveTo((float) 0.3, (float) 0.0);
            pathAsteroid.lineTo((float) 0.6, (float) 0.0);
            pathAsteroid.lineTo((float) 0.6, (float) 0.3);
            pathAsteroid.lineTo((float) 0.8, (float) 0.2);
            pathAsteroid.lineTo((float) 1.0, (float) 0.4);
            pathAsteroid.lineTo((float) 0.8, (float) 0.6);
            pathAsteroid.lineTo((float) 0.9, (float) 0.9);
            pathAsteroid.lineTo((float) 0.8, (float) 1.0);
            pathAsteroid.lineTo((float) 0.4, (float) 1.0);
            pathAsteroid.lineTo((float) 0.0, (float) 0.6);
            pathAsteroid.lineTo((float) 0.0, (float) 0.2);
            pathAsteroid.lineTo((float) 0.3, (float) 0.0);
            ShapeDrawable dAsteroid = new ShapeDrawable(new PathShape(pathAsteroid, 1, 1));
            dAsteroid.getPaint().setColor(Color.WHITE);
            dAsteroid.getPaint().setStyle(Paint.Style.STROKE);
            dAsteroid.setIntrinsicWidth(50);
            dAsteroid.setIntrinsicHeight(50);
            drawableAsteroid = dAsteroid;
            setBackgroundColor(Color.BLACK);
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            //Nave vectorial
            Path pathShip = new Path();
            pathShip.lineTo((float) 0, (float) 0);
            pathShip.lineTo((float) 0, (float) 1);
            pathShip.lineTo((float) 1, (float) 0.5);
            ShapeDrawable dShip = new ShapeDrawable(new PathShape(pathShip, 1, 1));
            dShip.getPaint().setColor(Color.WHITE);
            dShip.getPaint().setStyle(Paint.Style.STROKE);
            dShip.setIntrinsicWidth(50);
            dShip.setIntrinsicHeight(50);
            drawableShip = dShip;

        } else {
            //Los asteroides imagen
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
            drawableAsteroid = context.getResources().getDrawable(R.drawable.asteroide1);
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
            //La nave imagen
            drawableShip = context.getResources().getDrawable(R.drawable.nave);

        }
        asteroids = new ArrayList<AsteroidsGraphic>();

        AsteroidsGraphic spaceShip = new AsteroidsGraphic(this, drawableShip);
        ship = spaceShip;


        for (int i = 0; i < numAsteroids; i++) {
            AsteroidsGraphic asteroid = new AsteroidsGraphic(this,
                    drawableAsteroid);
            asteroid.setIncY(Math.random() * 4 - 2);

            asteroid.setIncX(Math.random() * 4 - 2);
            asteroid.setRotAngle((int) (Math.random() * 360));
            asteroid.setRotSpeed((int) (Math.random() * 8 - 4));
            asteroids.add(asteroid);
        }

    }

    @Override
    protected void onSizeChanged(int width, int height, int prevWidth, int prevHeight) {
        super.onSizeChanged(width, height, prevWidth, prevHeight);
        // Un cop coneixem el nostre ample i alt.
        for (AsteroidsGraphic asteroid : asteroids) {
            do {
                asteroid.setCenX((int) (Math.random() * width));
                asteroid.setCenY((int) (Math.random() * height));
            } while (asteroid.distance(ship) < (width + height) / 5);
        }
        ship.setCenX(1200);
        ship.setCenY(500);
        //Para lanzar el "movimiento"
        prevUpdate = System.currentTimeMillis();
        thread.start();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (AsteroidsGraphic asteroid : asteroids) {
            asteroid.drawGraphic(canvas);
        }
        ship.drawGraphic(canvas);
    }

    protected synchronized void updateView() {
        long now = System.currentTimeMillis();
        // No fer res fins a final del període
        if (prevUpdate + ANIM_INTERVAL > now) {
            return;
        }
        // Per a una execució en temps real calculam retard
        double delay = (now - prevUpdate) / ANIM_INTERVAL;

        prevUpdate = now;
        // Actualitzam velocitat i direcció de la nau a partir de
        // ship.rotAngle, ship.rotSpeed, and accelShip
        ship.setRotAngle((int) (ship.getRotAngle() + ship.getRotSpeed()
                * delay));
        double nIncX = ship.getIncX() + accelShip * Math.cos(Math.toRadians(ship.getRotAngle())) * delay;
        double nIncY = ship.getIncY() + accelShip * Math.sin(Math.toRadians(ship.getRotAngle())) * delay;
        // Actualitzam si el mòdul de la velocitat no excedeix el màxim
        if (Math.hypot(nIncX, nIncY) <= SHIP_MAX_SPEED) {
            ship.setIncX(nIncX);
            ship.setIncY(nIncY);
        }
        // Actualitzam posicions X i Y
        ship.updatePos(delay);
        for (AsteroidsGraphic asteroid : asteroids) {
            asteroid.updatePos(delay);
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        // Processam la pulsació
        boolean processada = true;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                accelShip = +0;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                ship.setRotSpeed(-0);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                ship.setRotSpeed(0);
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:

            case KeyEvent.KEYCODE_ENTER:
                //fireMissile();
                break;
            default:
                // Si estem aquí, no hi ha pulsació que ens interessi
                processada = false;
                break;
        }

        return processada;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        // Processam la pulsació
        boolean processed = true;
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP:
                accelShip = +STEPSIZE_ACCEL_SHIP;
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                ship.setRotSpeed(-STEPSIZE_ROT_SHIP);
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                ship.setRotSpeed(STEPSIZE_ROT_SHIP);
                break;
            case KeyEvent.KEYCODE_DPAD_CENTER:

            case KeyEvent.KEYCODE_ENTER:
                //fireMissile();
                break;
            default:
                // Si estem aquí, no hi ha pulsació que ens interessi
                processed = false;
                break;
        }
        return processed;
    }

    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                fire = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mX);
                float dy = Math.abs(y - mY);
                if (dy < 6 && dx > 6) {
                    ship.setRotSpeed(Math.round((x - mX) / 2));
                    fire = false;
                } else if (dx < 6 && dy > 6) {
                    accelShip = Math.round((mY - y) / 25);
                    fire = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                ship.setRotSpeed(0);
                accelShip = 0;
                if (fire) {
                    //fireMissile();
                }
                break;
        }
        mX = x;
        mY = y;
        return true;
    }

    class GameThread extends Thread {
        @Override
        public void run() {
            while (true) {
                updateView();
            }
        }
    }
}
