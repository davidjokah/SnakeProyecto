package org.itiud.logica;

public class Ususario {

    private int puntaje;
    private String modo, nombre;
    private boolean x;
    private String contrincante;

    public Ususario(String usuario) {
        this.puntaje = 0;
        this.modo = "Vivo";
        this.nombre = usuario;
        this.x = false;
        this.contrincante = "";
    }

    public String getContrincante() {
        return contrincante;
    }

    public void setContrincante(String contrincante) {
        this.contrincante = contrincante;
    }

    public  Ususario(){

    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isX() {
        return x;
    }

    public void setX(boolean x) {
        this.x = x;
    }

    @Override
    public String toString(){
        return "Usuario{"+
                "puntaje:"+puntaje+
                ",nombreUsuario"+nombre+'\''+
                ",modo:"+modo+'\'' +
                ",preparado: "+x+'\''+
                ",contrincante:"+contrincante+'\''+'}';
    }
}
