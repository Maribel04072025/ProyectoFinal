/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase que representa al jugador.
 */
public class Jugador extends Entidad {

    private int velocidad;
    private int direccionX;
    private int direccionY;
    private int puntaje;
    private boolean activo;
    private int vida;
    private Inventario inventario;
    private Nivel nivel; // referencia al nivel actual

    public Jugador(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        this.velocidad = 7;
        this.direccionX = 0;
        this.direccionY = 0;
        this.puntaje = 0;
        this.activo = true;
        this.vida = 100;
        this.inventario = new Inventario();
    }

    public void mover(int dx, int dy) {
        this.direccionX = dx;
        this.direccionY = dy;
    }

    @Override
    public void actualizar() {
        if (!activo) return;

        x += direccionX * velocidad;
        y += direccionY * velocidad;

        // Limitar al área del juego
        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x + ancho > 800) x = 800 - ancho;
        if (y + alto > 600) y = 600 - alto;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, ancho, alto);
    }

    public void aumentarPuntaje(int puntos) {
        puntaje += puntos;
        if (puntaje < 0) puntaje = 0;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getVida() {
        return vida;
    }

    public void recibirDanio(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public void curar(int cantidad) {
        vida += cantidad;
        if (vida > 100) vida = 100;
    }

    public boolean estaActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void reiniciarPosicion(int nuevaX, int nuevaY) {
        x = nuevaX;
        y = nuevaY;
        direccionX = 0;
        direccionY = 0;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Nivel getNivel() {
        return nivel;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
