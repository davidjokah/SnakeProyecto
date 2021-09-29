package org.itiud.logica;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;

public class Serp {

    private Bitmap bitm;
    private Bitmap cabeza_arriba;
    private Bitmap cabeza_abajo;
    private Bitmap cabeza_izq;
    private Bitmap cabeza_der;
    private Bitmap centro_der;
    private Bitmap centro_ver;
    private Bitmap centro_hor;
    private Bitmap centro_arrD;
    private Bitmap centro_arrI;
    private Bitmap centro_abD;
    private Bitmap centro_abI;
    private Bitmap cola_der;
    private Bitmap cola_izq;
    private Bitmap cola_arr;
    private Bitmap cola_abaj;

    private boolean movDer, movIzq, movAb, movArr;

    private int x, y, tamaño;
    private ArrayList<PartesSerp> arrayserpiente = new ArrayList<>();

    public Serp(Bitmap bitm, int x, int y, int tamaño) {
        this.bitm = bitm;
        this.x = x;
        this.y = y;
        this.tamaño = tamaño;

        //Creación de las partes de la serpiente según el tamaño del fondo y las dimensiones de la interfaz.
        centro_abI = Bitmap.createBitmap(bitm, 0, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        centro_abD = Bitmap.createBitmap(bitm, Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        centro_hor = Bitmap.createBitmap(bitm, 2 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        centro_arrI = Bitmap.createBitmap(bitm, 3 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        centro_arrD = Bitmap.createBitmap(bitm, 4 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        centro_ver = Bitmap.createBitmap(bitm, 5 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        cabeza_abajo = Bitmap.createBitmap(bitm, 6 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        cabeza_izq = Bitmap.createBitmap(bitm, 7 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        cabeza_der = Bitmap.createBitmap(bitm, 8 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        cabeza_arriba = Bitmap.createBitmap(bitm, 9 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        cola_arr = Bitmap.createBitmap(bitm, 10 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        cola_der = Bitmap.createBitmap(bitm, 11 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        cola_izq = Bitmap.createBitmap(bitm, 12 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);
        cola_abaj = Bitmap.createBitmap(bitm, 13 * Interfaz.tamañoFond, 0, Interfaz.tamañoFond, Interfaz.tamañoFond);

        //Creación de las posiciones de la serpiente utilizando las diferentes partes
        arrayserpiente.add(new PartesSerp(cabeza_der, x, y));
        for (int i = 1; i < tamaño - 1; i++) {
            arrayserpiente.add(new PartesSerp(centro_hor, arrayserpiente.get(i - 1).getX() - Interfaz.tamañoFond, y));
        }
        arrayserpiente.add(new PartesSerp(cola_der, arrayserpiente.get(tamaño - 2).getX() - Interfaz.tamañoFond, y));
        setMovArr(true);

    }

    public void refrescarMov() {
        for (int i = tamaño - 1; i > 0; i--) {
            arrayserpiente.get(i).setX(arrayserpiente.get(i - 1).getX());
            arrayserpiente.get(i).setY(arrayserpiente.get(i - 1).getY());
        }
        if (movDer) {
            arrayserpiente.get(0).setX((arrayserpiente.get(0).getX() + Interfaz.tamañoFond));
            arrayserpiente.get(0).setMp(cabeza_der);
        }
        if (movIzq) {
            arrayserpiente.get(0).setX((arrayserpiente.get(0).getX() - Interfaz.tamañoFond));
            arrayserpiente.get(0).setMp(cabeza_izq);
        }
        if (movArr) {
            arrayserpiente.get(0).setY((arrayserpiente.get(0).getY() - Interfaz.tamañoFond));
            arrayserpiente.get(0).setMp(cabeza_arriba);
        }
        if (movAb) {
            arrayserpiente.get(0).setY((arrayserpiente.get(0).getY() + Interfaz.tamañoFond));
            arrayserpiente.get(0).setMp(cabeza_abajo);
        }
        for (int i = 1; i < tamaño - 1; i++) {
            // Actualizando el movimiento de la serpiente cuando va abajo a la derecha o izquierda.
            if (arrayserpiente.get(i).getLadoizq().intersect(arrayserpiente.get(i + 1).getCentro())
                    && arrayserpiente.get(i).getAbajo().intersect(arrayserpiente.get(i - 1).getCentro())
                    || arrayserpiente.get(i).getLadoizq().intersect(arrayserpiente.get(i - 1).getCentro())
                    && arrayserpiente.get(i).getAbajo().intersect(arrayserpiente.get(i + 1).getCentro())) {
                arrayserpiente.get(i).setMp(centro_abI);
            } else if (arrayserpiente.get(i).getLadoder().intersect(arrayserpiente.get(i + 1).getCentro())
                    && arrayserpiente.get(i).getAbajo().intersect(arrayserpiente.get(i - 1).getCentro())
                    || arrayserpiente.get(i).getLadoder().intersect(arrayserpiente.get(i - 1).getCentro())
                    && arrayserpiente.get(i).getAbajo().intersect(arrayserpiente.get(i + 1).getCentro())) {
                arrayserpiente.get(i).setMp(centro_abD);
            }
            //Actualizando el movimiento cuando la serpiente va arriba a la derecha o izquierda.
            else if (arrayserpiente.get(i).getLadoizq().intersect(arrayserpiente.get(i + 1).getCentro())
                    && arrayserpiente.get(i).getArriba().intersect(arrayserpiente.get(i - 1).getCentro())
                    || arrayserpiente.get(i).getLadoizq().intersect(arrayserpiente.get(i - 1).getCentro())
                    && arrayserpiente.get(i).getArriba().intersect(arrayserpiente.get(i + 1).getCentro())) {
                arrayserpiente.get(i).setMp(centro_arrI);
            }else if (arrayserpiente.get(i).getLadoder().intersect(arrayserpiente.get(i + 1).getCentro())
                    && arrayserpiente.get(i).getArriba().intersect(arrayserpiente.get(i - 1).getCentro())
                    || arrayserpiente.get(i).getLadoder().intersect(arrayserpiente.get(i - 1).getCentro())
                    && arrayserpiente.get(i).getArriba().intersect(arrayserpiente.get(i + 1).getCentro())) {
                arrayserpiente.get(i).setMp(centro_arrD);
            }
            //Actualizando el movimiento cuando la serpiente va vertical y horizontalmente..
            else if (arrayserpiente.get(i).getArriba().intersect(arrayserpiente.get(i + 1).getCentro())
                    && arrayserpiente.get(i).getAbajo().intersect(arrayserpiente.get(i - 1).getCentro())
                    || arrayserpiente.get(i).getArriba().intersect(arrayserpiente.get(i - 1).getCentro())
                    && arrayserpiente.get(i).getAbajo().intersect(arrayserpiente.get(i + 1).getCentro())) {
                arrayserpiente.get(i).setMp(centro_ver);
            }else if (arrayserpiente.get(i).getLadoizq().intersect(arrayserpiente.get(i + 1).getCentro())
                    && arrayserpiente.get(i).getLadoder().intersect(arrayserpiente.get(i - 1).getCentro())
                    || arrayserpiente.get(i).getLadoizq().intersect(arrayserpiente.get(i - 1).getCentro())
                    && arrayserpiente.get(i).getLadoder().intersect(arrayserpiente.get(i + 1).getCentro())) {
                arrayserpiente.get(i).setMp(centro_hor);
            }
        }
        // Actualizando el movimiento cuando la serpiente se mueve en la parte final.
        if(arrayserpiente.get(tamaño-1).getLadoder().intersect((arrayserpiente.get(tamaño-2).getCentro()))){
            arrayserpiente.get(tamaño-1).setMp((cola_der));
        }else if(arrayserpiente.get(tamaño-1).getLadoizq().intersect((arrayserpiente.get(tamaño-2).getCentro()))){
            arrayserpiente.get(tamaño-1).setMp((cola_izq));
        }else if(arrayserpiente.get(tamaño-1).getArriba().intersect((arrayserpiente.get(tamaño-2).getCentro()))){
            arrayserpiente.get(tamaño-1).setMp((cola_arr));
        }else if(arrayserpiente.get(tamaño-1).getAbajo().intersect((arrayserpiente.get(tamaño-2).getCentro()))){
            arrayserpiente.get(tamaño-1).setMp((cola_abaj));
        }
    }

    public void pintarSerp(Canvas canvas) {
        for (int i = 0; i < tamaño; i++) {
            canvas.drawBitmap(arrayserpiente.get(i).getMp(), arrayserpiente.get(i).getX(), arrayserpiente.get(i).getY(), null);
        }
    }

    public Bitmap getBitm() {
        return bitm;
    }

    public void setBitm(Bitmap bitm) {
        this.bitm = bitm;
    }

    public Bitmap getCabeza_arriba() {
        return cabeza_arriba;
    }

    public void setCabeza_arriba(Bitmap cabeza_arriba) {
        this.cabeza_arriba = cabeza_arriba;
    }

    public Bitmap getCabeza_abajo() {
        return cabeza_abajo;
    }

    public void setCabeza_abajo(Bitmap cabeza_abajo) {
        this.cabeza_abajo = cabeza_abajo;
    }

    public Bitmap getCabeza_izq() {
        return cabeza_izq;
    }

    public void setCabeza_izq(Bitmap cabeza_izq) {
        this.cabeza_izq = cabeza_izq;
    }

    public Bitmap getCabeza_der() {
        return cabeza_der;
    }

    public void setCabeza_der(Bitmap cabeza_der) {
        this.cabeza_der = cabeza_der;
    }

    public Bitmap getCentro_der() {
        return centro_der;
    }

    public void setCentro_der(Bitmap centro_der) {
        this.centro_der = centro_der;
    }

    public Bitmap getCentro_hor() {
        return centro_hor;
    }

    public void setCentro_hor(Bitmap centro_hor) {
        this.centro_hor = centro_hor;
    }

    public Bitmap getCentro_ver() {
        return centro_ver;
    }

    public void setCentro_ver(Bitmap centro_ver) {
        this.centro_ver = centro_ver;
    }

    public Bitmap getCentro_arrD() {
        return centro_arrD;
    }

    public void setCentro_arrD(Bitmap centro_arrD) {
        this.centro_arrD = centro_arrD;
    }

    public Bitmap getCentro_arrI() {
        return centro_arrI;
    }

    public void setCentro_arrI(Bitmap centro_arrI) {
        this.centro_arrI = centro_arrI;
    }

    public Bitmap getCentro_abD() {
        return centro_abD;
    }

    public void setCentro_abD(Bitmap centro_abD) {
        this.centro_abD = centro_abD;
    }

    public Bitmap getCentro_abI() {
        return centro_abI;
    }

    public void setCentro_abI(Bitmap centro_abI) {
        this.centro_abI = centro_abI;
    }

    public Bitmap getCola_der() {
        return cola_der;
    }

    public void setCola_der(Bitmap cola_der) {
        this.cola_der = cola_der;
    }

    public Bitmap getCola_izq() {
        return cola_izq;
    }

    public void setCola_izq(Bitmap cola_izq) {
        this.cola_izq = cola_izq;
    }

    public Bitmap getCola_arr() {
        return cola_arr;
    }

    public void setCola_arr(Bitmap cola_arr) {
        this.cola_arr = cola_arr;
    }

    public Bitmap getCola_abaj() {
        return cola_abaj;
    }

    public void setCola_abaj(Bitmap cola_abaj) {
        this.cola_abaj = cola_abaj;
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

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public ArrayList<PartesSerp> getArrayserpiente() {
        return arrayserpiente;
    }

    public void setArrayserpiente(ArrayList<PartesSerp> arrayserpiente) {
        this.arrayserpiente = arrayserpiente;
    }

    public boolean isMovDer() {
        return movDer;
    }

    public void setMovDer(boolean movDer) {
        m();
        this.movDer = movDer;
    }

    public boolean isMovIzq() {
        return movIzq;
    }

    public void setMovIzq(boolean movIzq) {
        m();
        this.movIzq = movIzq;
    }

    public boolean isMovAb() {
        return movAb;
    }

    public void setMovAb(boolean movAb) {
        m();
        this.movAb = movAb;
    }

    public boolean isMovArr() {
        return movArr;
    }

    public void setMovArr(boolean movArr) {
        m();
        this.movArr = movArr;
    }

    public void m() {
        this.movIzq = false;
        this.movAb = false;
        this.movDer = false;
        this.movArr = false;
    }

    public void crecer() {
        PartesSerp partes = this.arrayserpiente.get(tamaño-1);
        this.tamaño++;
        if(partes.getMp() == cola_der){
            this.arrayserpiente.add(new PartesSerp(cola_der, partes.getX() - Interfaz.tamañoFond, partes.getY()));
        }else if(partes.getMp() == cola_izq){
            this.arrayserpiente.add(new PartesSerp(cola_izq, partes.getX() + Interfaz.tamañoFond, partes.getY()));
        }else if(partes.getMp() == cola_abaj){
            this.arrayserpiente.add(new PartesSerp(cola_abaj, partes.getX(), partes.getY() - Interfaz.tamañoFond));
        }else if(partes.getMp() == cola_arr){
            this.arrayserpiente.add(new PartesSerp(cola_arr, partes.getX(), partes.getY() + Interfaz.tamañoFond));
        }
    }
}