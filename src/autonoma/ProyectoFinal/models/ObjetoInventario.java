/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class ObjetoInventario {

    protected int x, y, ancho, alto;
    protected String nombre;

    public ObjetoInventario(int x, int y, int ancho, int alto, String nombre) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract void aplicarEfecto(Jugador jugador);

    // Puede ser sobrescrito por objetos que tengan imagen
    public void dibujar(Graphics g) {
        g.fillRect(x, y, ancho, alto);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    // Getters útiles
    public int getX() { return x; }
    public int getY() { return y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
}
