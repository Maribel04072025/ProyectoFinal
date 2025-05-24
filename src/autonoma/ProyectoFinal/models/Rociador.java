/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Rociador extends Entidad {

    private int velocidadX = 0, velocidadY = 0;
    private boolean activo = true;

    public Rociador(int x, int y, int baseAncho, int baseAlto, String direccion) {
        super(x, y, baseAncho, baseAlto);

        int velocidad = 10;
        switch (direccion) {
            case "arriba" -> {
                velocidadY = -velocidad;
                this.ancho = 8;
                this.alto = 16;
            }
            case "abajo" -> {
                velocidadY = velocidad;
                this.ancho = 8;
                this.alto = 16;
            }
            case "izquierda" -> {
                velocidadX = -velocidad;
                this.ancho = 16;
                this.alto = 8;
            }
            case "derecha" -> {
                velocidadX = velocidad;
                this.ancho = 16;
                this.alto = 8;
            }
        }
    }

    @Override
    public void actualizar() {
        x += velocidadX;
        y += velocidadY;
        if (x < 0 || x > 1000 || y < 0 || y > 800) {
            activo = false;
        }
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
