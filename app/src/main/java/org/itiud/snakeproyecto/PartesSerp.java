package org.itiud.snakeproyecto;

import android.graphics.Bitmap;
import android.graphics.Rect;

public class PartesSerp {

    private Bitmap mp;
    private int x,y;
    private Rect rectCont, rectAb, rectAr, rectDer, rectIzq;

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

    public Rect getRectCont() {
        return new Rect(this.x,this.y, this.x+Interfaz.tamañoFond,
                this.y+Interfaz.tamañoFond);
    }

    public void setRectCont(Rect rectCont) {
        this.rectCont = rectCont;
    }

    public Rect getRectAb() {
        return new Rect(this.x,this.y+Interfaz.tamañoFond, this.x+Interfaz.tamañoFond,
                this.y+Interfaz.tamañoFond+10*ParametrosC.SCREEN_HEIGHT/1920);
    }

    public void setRectAb(Rect rectAb) {
        this.rectAb = rectAb;
    }

    public Rect getRectAr() {
        return new Rect(this.x,this.y-10*ParametrosC.SCREEN_HEIGHT/1920, this.x+Interfaz.tamañoFond,
                this.y);
    }

    public void setRectAr(Rect rectAr) {
        this.rectAr = rectAr;
    }

    public Rect getRectDer() {
        return new Rect(this.x+Interfaz.tamañoFond,this.y, this.x+Interfaz.tamañoFond+10*ParametrosC.SCREEN_WIDTH/1080,
                this.y+Interfaz.tamañoFond);
    }

    public void setRectDer(Rect rectDer) {
        this.rectDer = rectDer;
    }

    public Rect getRectIzq() {
        return new Rect(this.x-10*ParametrosC.SCREEN_WIDTH/1080,this.y, this.x,
                this.y+Interfaz.tamañoFond);
    }

    public void setRectIzq(Rect rectIzq) {
        this.rectIzq = rectIzq;
    }
}
