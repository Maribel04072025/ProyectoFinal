/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase abstracta base para todas las entidades del juego.
 */
public abstract class Entidad {

    protected int x;
    protected int y;
    protected int ancho;
    protected int alto;

    public Entidad(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * Método abstracto para actualizar la lógica de la entidad.
     */
    public abstract void actualizar();

    /**
     * Método abstracto para dibujar la entidad.
     */
    public abstract void dibujar(Graphics g);

    /**
     * Devuelve el área de colisión de la entidad.
     */
    public abstract Rectangle getBounds();
}
