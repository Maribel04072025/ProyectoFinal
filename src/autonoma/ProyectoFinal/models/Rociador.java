/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Rociador extends Entidad {

    private int x, y, ancho, alto;
    private int velocidadX = 0, velocidadY = 0;
    private boolean activo = true;

    public Rociador(int x, int y, int ancho, int alto, String direccion) {
        super(x, y, ancho, alto);
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;

        int velocidad = 10;
        switch (direccion) {
            case "arriba":
                velocidadY = -velocidad;
                break;
            case "abajo":
                velocidadY = velocidad;
                break;
            case "izquierda":
                velocidadX = -velocidad;
                break;
            case "derecha":
                velocidadX = velocidad;
                break;
        }
    }

    @Override
    public void actualizar() {
        x += velocidadX;
        y += velocidadY;
        // Podés desactivar si sale de pantalla
        if (x < 0 || x > 1000 || y < 0 || y > 800) activo = false;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, ancho, alto);
    }

    public boolean estaActivo() {
        return activo;
    }

    public void desactivar() {
        activo = false;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}

