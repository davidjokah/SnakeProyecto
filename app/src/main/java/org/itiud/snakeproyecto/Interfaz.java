package org.itiud.snakeproyecto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Interfaz extends View {
    private Bitmap bmfondo1, bmfondo2, bmSerp;
    private int h = 22, w= 12;
    private ArrayList<Fondo> arrayFondo = new ArrayList<>();
    public static int tamañoFond = 75*ParametrosC.SCREEN_WIDTH/1200;
    private Serp serpi;

    public Interfaz(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //Creación de los recuadros para el tablero del juego.
        bmfondo1 = BitmapFactory.decodeResource(this.getResources(), R.drawable.fondo1);
        bmfondo1 =Bitmap.createScaledBitmap(bmfondo1, tamañoFond,tamañoFond,true);
        bmfondo2 = BitmapFactory.decodeResource(this.getResources(), R.drawable.fondo2);
        bmfondo2 = Bitmap.createScaledBitmap(bmfondo2,tamañoFond,tamañoFond,true);
        bmSerp = BitmapFactory.decodeResource(this.getResources(),R.drawable.snakepartes);
        bmSerp = Bitmap.createScaledBitmap(bmSerp, 14*tamañoFond, tamañoFond, true);
        for(int i=0; i < h; i++){
            for (int j=0; j < w ; j++){
             if((i+j)%2==0){
                 arrayFondo.add(new Fondo(bmfondo1, j*tamañoFond + ParametrosC.SCREEN_WIDTH/2-(w/2)*tamañoFond,
                  i*tamañoFond+100*ParametrosC.SCREEN_HEIGHT/1920, tamañoFond, tamañoFond));
             }else{
                 arrayFondo.add(new Fondo(bmfondo2, j*tamañoFond + ParametrosC.SCREEN_WIDTH/2-(w/2)*tamañoFond,
                  i*tamañoFond+100*ParametrosC.SCREEN_HEIGHT/1920, tamañoFond, tamañoFond));
             }
            }
        }
        serpi = new Serp(bmSerp, arrayFondo.get(126).getX(), arrayFondo.get(126).getY(),4);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawColor(0xFF578A34);
    for (int i=0;i<arrayFondo.size();i++){
            canvas.drawBitmap(arrayFondo.get(i).getBm(), arrayFondo.get(i).getX(), arrayFondo.get(i).getY(), null);
        }
    serpi.pintarSerp(canvas);
    }
}
