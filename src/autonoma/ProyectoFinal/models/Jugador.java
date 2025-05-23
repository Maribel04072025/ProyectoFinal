/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Representa al jugador controlado por el usuario.
 * Hereda de Entidad.
 */
public class Jugador extends Entidad {
    private int velocidad;
    private int direccionX;
    private int direccionY;
    private int puntaje;
    private boolean activo;

    /**
     * Constructor del jugador
     * @param x posición x inicial
     * @param y posición y inicial
     * @param ancho ancho del jugador
     * @param alto alto del jugador
     */
    public Jugador(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        this.velocidad = 5;
        this.direccionX = 0;
        this.direccionY = 0;
        this.puntaje = 0;
        this.activo = true;
    }

    /**
     * Mueve al jugador en la dirección especificada
     * @param dx -1, 0 o 1 en X
     * @param dy -1, 0 o 1 en Y
     */
    public void mover(int dx, int dy) {
        this.direccionX = dx;
        this.direccionY = dy;
    }

    /**
     * Actualiza la posición del jugador.
     */
    @Override
    public void actualizar() {
        if (!activo) return;
        x += direccionX * velocidad;
        y += direccionY * velocidad;
        // Puedes agregar límites de pantalla si lo deseas
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x + ancho > 800) x = 800 - ancho;
        if (y + alto > 600) y = 600 - alto;
    }

    /**
     * Dibuja al jugador como un rectángulo verde
     * @param g contexto gráfico
     */
    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, ancho, alto);
    }

    public void aumentarPuntaje(int puntos) {
        this.puntaje += puntos;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean estaActivo() {
        return activo;
    }

    public void reiniciarPosicion(int nuevaX, int nuevaY) {
        this.x = nuevaX;
        this.y = nuevaY;
        this.direccionX = 0;
        this.direccionY = 0;
    }

    @Override
    public Rectangle getBounds() {
        return super.getBounds();
    }
}