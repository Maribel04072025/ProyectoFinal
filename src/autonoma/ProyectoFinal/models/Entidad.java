/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase abstracta para representar entidades con posición, tamaño
 * y comportamientos comunes como dibujar, actualizar y colisiones.
 */
public abstract class Entidad {
    protected int x, y;
    protected int ancho, alto;

    public Entidad(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * Dibuja la entidad en pantalla.
     */
    public abstract void dibujar(Graphics g);

    /**
     * Actualiza el estado de la entidad.
     */
    public abstract void actualizar();

    /**
     * Devuelve el rectángulo delimitador para detectar colisiones.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    // Getters y setters útiles para otras clases

    public int getX() { return x; }

    public int getY() { return y; }

    public int getAncho() { return ancho; }

    public int getAlto() { return alto; }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public void setAncho(int ancho) { this.ancho = ancho; }

    public void setAlto(int alto) { this.alto = alto; }
}
