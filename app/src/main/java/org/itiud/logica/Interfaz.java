package org.itiud.logica;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.itiud.snakeproyecto.MainActivity;
import org.itiud.snakeproyecto.R;
import org.itiud.snakeproyecto.Segundo;

import java.util.ArrayList;
import java.util.Random;

public class Interfaz extends View {

    private Bitmap bmfondo1;
    private Bitmap bmfondo2;
    private Bitmap bmSerp;
    private Bitmap bmComida;
    private int puntaje, puntajemax;
    private final int h = 16;
    private final int w = 12;
    private final ArrayList<Fondo> arrayFondo = new ArrayList<>();
    public static int tamañoFond = 70 * ParametrosC.SCREEN_WIDTH / 1080;
    private boolean movimiento = false;
    private float movx, movy;
    private Serp serpi;
    private Runnable r;
    private Comida manzana;
    private Handler hd;
    private boolean vive = true;
    private TextView txtPunt, txtMaxPunt;

    public Interfaz(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        //Creación de los recuadros para el tablero del juego.
        bmfondo1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.fondo1);
        bmfondo1 = Bitmap.createScaledBitmap(bmfondo1, tamañoFond, tamañoFond, true);
        bmfondo2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.fondo2);
        bmfondo2 = Bitmap.createScaledBitmap(bmfondo2, tamañoFond, tamañoFond, true);
        bmSerp = BitmapFactory.decodeResource(this.getResources(), R.drawable.snakepartes);
        bmSerp = Bitmap.createScaledBitmap(bmSerp, 14 * tamañoFond, tamañoFond, true);
        bmComida = BitmapFactory.decodeResource(this.getResources(), R.drawable.comida);
        bmComida = Bitmap.createScaledBitmap(bmComida, tamañoFond, tamañoFond, true);

        this.txtPunt = findViewById(R.id.txt_puntaje);
        this.txtMaxPunt = findViewById(R.id.txt_mejor_puntaje);

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if ((i + j) % 2 == 0) {
                    arrayFondo.add(new Fondo(bmfondo1, j * tamañoFond + ParametrosC.SCREEN_WIDTH / 2 - (w / 2) * tamañoFond,
                            i * tamañoFond + 100 * ParametrosC.SCREEN_HEIGHT / 1920, tamañoFond, tamañoFond));
                } else {
                    arrayFondo.add(new Fondo(bmfondo2, j * tamañoFond + ParametrosC.SCREEN_WIDTH / 2 - (w / 2) * tamañoFond,
                            i * tamañoFond + 100 * ParametrosC.SCREEN_HEIGHT / 1920, tamañoFond, tamañoFond));
                }
            }
        }
        serpi = new Serp(bmSerp, arrayFondo.get(126).getX(), arrayFondo.get(126).getY(), 4);
        manzana = new Comida(bmComida, arrayFondo.get(randomPresa()[0]).getX(), arrayFondo.get(randomPresa()[1]).getY());
        hd = new Handler();
        r = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    public void sonidoCome() {
        MediaPlayer mp = MediaPlayer.create(this.getContext(), R.raw.comer);
        mp.start();
    }

    /*public void puntuacion(){
        if (serpi.getArrayserpiente().get(0).getCentro().intersect((manzana.getCr()))) {
            return true;
        }else{
            return false;
        }
    }*/

    public void cargarPuntuaciones() {
        SharedPreferences pr = getContext().getSharedPreferences("puntajes", getContext().MODE_PRIVATE);

        int puntaje = pr.getInt("puntaje", 0);
        int puntajemax = pr.getInt("puntajemax", 0);

        txtPunt.setText(String.valueOf(puntaje));
        txtMaxPunt.setText(String.valueOf(puntajemax));

    }

    public void guardarPuntuaciones() {
        SharedPreferences pr = getContext().getSharedPreferences("puntajes", getContext().MODE_PRIVATE);

        SharedPreferences.Editor editr = pr.edit();
        editr.putInt("puntaje", puntaje);
        editr.putInt("puntajemax", puntajemax);

        txtPunt.setText(String.valueOf(puntaje));
        txtMaxPunt.setText(String.valueOf(puntajemax));

        editr.commit();
    }

    public void verificarBordes(){
        if (serpi.getArrayserpiente().get(0).getX() < arrayFondo.get(0).getX()
                || serpi.getArrayserpiente().get(0).getY() < arrayFondo.get(0).getY()
                || serpi.getArrayserpiente().get(0).getY() + tamañoFond > arrayFondo.get(arrayFondo.size() - 1).getY() + tamañoFond
                || serpi.getArrayserpiente().get(0).getX() + tamañoFond > arrayFondo.get(arrayFondo.size() - 1).getX() + tamañoFond) {
            Toast.makeText(this.getContext(), "!No puedes salir del mapa!", Toast.LENGTH_SHORT).show();
            chocar();
        }
    }

    public void verificarComerse(){
        for (int i = 1; i < serpi.getArrayserpiente().size(); i++) {
            if (serpi.getArrayserpiente().get(0).getCentro().intersect(serpi.getArrayserpiente().get(i).getCentro())) {
                Toast.makeText(this.getContext(), "!No intentes comerte a tí mismo!", Toast.LENGTH_SHORT).show();
                chocar();
            }
        }

    }

    public void chocar() {
        vive = false;
        Intent intent = new Intent(this.getContext(), Segundo.class);
        ((MainActivity) getContext()).finish();
        getContext().startActivity(intent);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_MOVE: {
                if (movimiento == false) {
                    movy = event.getY();
                    movx = event.getX();
                    movimiento = true;
                } else {
                    if (movx - event.getX() > 100 * ParametrosC.SCREEN_WIDTH / 1080 && !serpi.isMovDer()) {
                        movx = event.getX();
                        movy = event.getY();
                        movimiento = true;
                        serpi.setMovIzq(true);
                    } else if (event.getX() - movx > 100 * ParametrosC.SCREEN_WIDTH / 1080 && !serpi.isMovIzq()) {
                        movx = event.getX();
                        movy = event.getY();
                        movimiento = true;
                        serpi.setMovDer(true);
                    } else if (movy - event.getY() > 100 * ParametrosC.SCREEN_WIDTH / 1080 && !serpi.isMovAb()) {
                        movx = event.getX();
                        movy = event.getY();
                        movimiento = true;
                        serpi.setMovArr(true);
                    } else if (event.getY() - movy > 100 * ParametrosC.SCREEN_WIDTH / 1080 && !serpi.isMovArr()) {
                        movx = event.getX();
                        movy = event.getY();
                        movimiento = true;
                        serpi.setMovAb(true);
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
                movx = 0;
                movy = 0;
                movimiento = false;
                break;
        }
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xFF578A34);
        for (int i = 0; i < arrayFondo.size(); i++) {
            canvas.drawBitmap(arrayFondo.get(i).getBm(), arrayFondo.get(i).getX(), arrayFondo.get(i).getY(), null);
        }
        if (vive) {
            serpi.refrescarMov();
            verificarBordes();
            verificarComerse();
        }
        serpi.pintarSerp(canvas);
        manzana.pintarPresa(canvas);
        if (serpi.getArrayserpiente().get(0).getCentro().intersect((manzana.getCr()))) {
            puntaje+=10;
            randomPresa();
            manzana.respawn(arrayFondo.get(randomPresa()[0]).getX(), arrayFondo.get(randomPresa()[1]).getY());
            sonidoCome();
            serpi.crecer();

        }
        hd.postDelayed(r, 100);
    }

    public int[] randomPresa() {
        int[] xy = new int[2];
        Random r = new Random();
        xy[0] = r.nextInt(arrayFondo.size() - 1);
        xy[1] = r.nextInt(arrayFondo.size() - 1);
        Rect rc = new Rect(arrayFondo.get(xy[0]).getX(), arrayFondo.get(xy[1]).getY(),
                arrayFondo.get(xy[0]).getX() + tamañoFond, arrayFondo.get(xy[1]).getY() + tamañoFond);
        boolean b = true;
        while (b) {
            b = false;
            for (int i = 0; i < serpi.getArrayserpiente().size(); i++) {
                if (rc.intersect(serpi.getArrayserpiente().get(i).getCentro())) {
                    b = true;
                    xy[0] = r.nextInt(arrayFondo.size() - 1);
                    xy[1] = r.nextInt(arrayFondo.size() - 1);
                    rc = new Rect(arrayFondo.get(xy[0]).getX(), arrayFondo.get(xy[1]).getY(),
                            arrayFondo.get(xy[0]).getX() + tamañoFond, arrayFondo.get(xy[1]).getY() + tamañoFond);
                }
            }
        }
        return xy;
    }
}
