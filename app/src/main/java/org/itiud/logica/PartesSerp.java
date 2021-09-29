package org.itiud.logica;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class PartesSerp {

    private Bitmap mp;
    private int x,y;
    private Rect centro, abajo, arriba, ladoder, ladoizq;

    public PartesSerp(Bitmap mp, int x, int y) {
        this.mp = mp;
        this.x = x;
        this.y = y;
    }

    public Bitmap getMp() {
        return mp;
    }

    public void setMp(Bitmap mp) {
        this.mp = mp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rect getCentro() {
        return new Rect(this.x,this.y, this.x+ Interfaz.tamañoFond,
                this.y+ Interfaz.tamañoFond);
    }

    public void setCentro(Rect centro) {
        this.centro = centro;
    }

    public Rect getAbajo() {
        return new Rect(this.x,this.y+ Interfaz.tamañoFond, this.x+ Interfaz.tamañoFond,
                this.y+ Interfaz.tamañoFond+10* ParametrosC.SCREEN_HEIGHT/1920);
    }

    public void setAbajo(Rect abajo) {
        this.abajo = abajo;
    }

    public Rect getArriba() {
        return new Rect(this.x,this.y-10*ParametrosC.SCREEN_HEIGHT/1920, this.x+ Interfaz.tamañoFond,
                this.y);
    }

    public void setArriba(Rect arriba) {
        this.arriba = arriba;
    }

    public Rect getLadoder() {
        return new Rect(this.x+ Interfaz.tamañoFond,this.y, this.x+ Interfaz.tamañoFond+10*ParametrosC.SCREEN_WIDTH/1080,
                this.y+ Interfaz.tamañoFond);
    }

    public void setLadoder(Rect ladoder) {
        this.ladoder = ladoder;
    }

    public Rect getLadoizq() {
        return new Rect(this.x-10*ParametrosC.SCREEN_WIDTH/1080,this.y, this.x,
                this.y+ Interfaz.tamañoFond);
    }

    public void setLadoizq(Rect ladoizq) {
        this.ladoizq = ladoizq;
    }
}
