/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase que representa al jugador del juego.
 */
public class Jugador extends Entidad {

    private int velocidad;
    private int direccionX;
    private int direccionY;
    private int puntaje;
    private boolean activo;
    private int vida;
    private int vidaMaxima;
    private Inventario inventario;
    private Nivel nivel;
    private String direccionActual = "derecha";
    private int direccionDisparoX = 1; 
    private int direccionDisparoY = 0;

    /**
     * Constructor del jugador con dificultad.
     * @param x posición X inicial
     * @param y posición Y inicial
     * @param ancho ancho del jugador
     * @param alto alto del jugador
     * @param dificultad 1: fácil, 2: media, 3: difícil
     */
    public Jugador(int x, int y, int ancho, int alto, int dificultad) {
        super(x, y, ancho, alto);
        this.velocidad = 5;
        this.direccionX = 0;
        this.direccionY = 0;
        this.puntaje = 0;
        this.activo = true;

        switch (dificultad) {
            case 1 -> this.vida = this.vidaMaxima = 100;
            case 2 -> this.vida = this.vidaMaxima = 60;
            case 3 -> this.vida = this.vidaMaxima = 40;
            default -> this.vida = this.vidaMaxima = 100;
        }

        this.inventario = new Inventario();
    }

    public void mover(int dx, int dy) {
        direccionX = dx;
        direccionY = dy;

        if (dx > 0) direccionActual = "derecha";
        else if (dx < 0) direccionActual = "izquierda";
        else if (dy > 0) direccionActual = "abajo";
        if (dy < 0) direccionActual = "arriba";
    }

    @Override
    public void actualizar() {
        if (!activo) return;

        x += direccionX * velocidad;
        y += direccionY * velocidad;

        // Limitar dentro del área
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
    
    public String getDireccionActual() {
        return direccionActual;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public int getVida() {
        return vida;
    }

    public int getVidaMaxima() {
        return vidaMaxima;
    }

    public void recibirDanio(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public void curar(int cantidad) {
        vida += cantidad;
        if (vida > vidaMaxima) {
            vida = vidaMaxima;
        }
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
    
    public void setDireccionX(int dx) {
        direccionX = dx;
    }

    public void setDireccionY(int dy) {
        direccionY = dy;
    }  
    
    public int getDireccionDisparoX() {
        return direccionDisparoX;
    }

    public int getDireccionDisparoY() {
        return direccionDisparoY;
    } 

    public void setDireccionDisparoX(int dx) {
        direccionDisparoX = dx;
    }

    public void setDireccionDisparoY(int dy) {
        direccionDisparoY = dy;
    }
}

