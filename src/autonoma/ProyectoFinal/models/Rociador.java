/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Representa un proyectil o disparo lanzado por el jugador.
 * El rociador se mueve en una dirección específica y se desactiva
 * al salir del área visible o al impactar con un enemigo.
 * 
 * El tamaño y la dirección del rociador dependen del parámetro de dirección dado.
 * 
 * Movimiento controlado por actualización de posición en cada ciclo.
 * 
 * @author maribel Ceballos 
 * @version 1.0
 * @since 2025-05-24
 */
public class Rociador extends Entidad {

    private int velocidadX = 0, velocidadY = 0;
    private boolean activo = true;

    /**
     * Constructor que crea un rociador en la posición dada con tamaño y velocidad según dirección.
     * 
     * @param x posición horizontal inicial
     * @param y posición vertical inicial
     * @param baseAncho ancho base inicial (modificado según dirección)
     * @param baseAlto alto base inicial (modificado según dirección)
     * @param direccion dirección del disparo ("arriba", "abajo", "izquierda", "derecha")
     */
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

    /**
     * Actualiza la posición del rociador según su velocidad.
     * Desactiva el rociador si sale del área visible (límites 0-1000 horizontal y 0-800 vertical).
     */
    @Override
    public void actualizar() {
        x += velocidadX;
        y += velocidadY;
        if (x < 0 || x > 1000 || y < 0 || y > 800) {
            activo = false;
        }
    }

    /**
     * Dibuja el rociador en pantalla usando un rectángulo de color cian.
     * 
     * @param g objeto Graphics para dibujar
     */
    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, ancho, alto);
    }

    /**
     * Indica si el rociador sigue activo (no ha salido de pantalla ni impactado).
     * 
     * @return true si activo, false si desactivado
     */
    public boolean estaActivo() {
        return activo;
    }

    /**
     * Desactiva el rociador para que deje de actualizarse y dibujarse.
     */
    public void desactivar() {
        activo = false;
    }

    /**
     * Obtiene los límites (bounding box) del rociador para detectar colisiones.
     * 
     * @return objeto Rectangle con posición y tamaño actual
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}