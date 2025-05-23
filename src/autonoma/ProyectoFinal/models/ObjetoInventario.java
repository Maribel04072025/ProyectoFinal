/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase base abstracta para cualquier objeto que puede estar en el inventario.
 */
public abstract class ObjetoInventario extends Entidad {
    protected String nombre;
    protected boolean recogido;

    public ObjetoInventario(int x, int y, int ancho, int alto, String nombre) {
        super(x, y, ancho, alto);
        this.nombre = nombre;
        this.recogido = false;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaRecogido() {
        return recogido;
    }

    public void recoger() {
        recogido = true;
    }

    public abstract void aplicarEfecto(Jugador jugador);

    @Override
    public void actualizar() {
        // No se mueven por sí solos
    }

    @Override
    public abstract void dibujar(Graphics g);

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
