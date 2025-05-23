/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

/**
 *
 * @author marib
 */


import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase base abstracta para todas las entidades del juego.
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

    public abstract void dibujar(Graphics g);
    public abstract void actualizar();

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    // Getters y setters
    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
}
