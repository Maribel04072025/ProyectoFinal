/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Rociador extends Entidad {

    private int velocidad;
    private boolean activo;

    public Rociador(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        this.velocidad = 10;
        this.activo = true;
    }

    @Override
    public void actualizar() {
        x += velocidad;
        if (x > 800) {
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
        return super.getBounds();
    }
}
