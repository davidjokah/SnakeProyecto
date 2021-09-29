package org.itiud.logica;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Comida {

    private Bitmap bitm;
    private int x,y;
    private Rect cr;

    public Comida(Bitmap bitm, int x, int y) {
        this.bitm = bitm;
        this.x = x;
        this.y = y;
    }

    public Bitmap getBitm() {
        return bitm;
    }

    public void setBitm(Bitmap bitm) {
        this.bitm = bitm;
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

    public Rect getCr() {
        return new Rect(this.x, this.y, this.x+ Interfaz.tamañoFond, this.y+ Interfaz.tamañoFond);
    }

    public void setCr(Rect cr) {
        this.cr = cr;
    }

    public void pintarPresa(Canvas c) {
        c.drawBitmap(bitm,x,y,null);
    }

    public void respawn(int nuevx, int nuevy) {
        this.x = nuevx;
        this.y = nuevy;

    }
}
