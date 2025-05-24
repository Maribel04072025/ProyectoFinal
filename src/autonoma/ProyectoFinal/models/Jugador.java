/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Jugador extends Entidad {

    private int velocidad;
    private int vida;
    private int vidaMaxima;
    private int puntaje;
    private String direccion = "derecha";
    private Inventario inventario;
    private Nivel nivel;

    private final int LIMITE_ANCHO = 900;
    private final int LIMITE_ALTO = 700;

    private Image imagenJugador;

    public Jugador(int x, int y, int ancho, int alto, int dificultad) {
        super(x, y, ancho, alto);
        this.inventario = new Inventario();
        this.puntaje = 0;

        switch (dificultad) {
            case 1 -> this.vida = this.vidaMaxima = 100;
            case 2 -> this.vida = this.vidaMaxima = 75;
            case 3 -> this.vida = this.vidaMaxima = 50;
        }

        this.velocidad = 5;

        // Cargar imagen
        try {
            imagenJugador = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/jugador.png")).getImage();
        } catch (Exception e) {
            System.err.println("⚠ Imagen de jugador no encontrada");
            imagenJugador = null;
        }
    }

    @Override
    public void actualizar() {}

    @Override
    public void dibujar(Graphics g) {
        if (imagenJugador != null) {
            g.drawImage(imagenJugador, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, ancho, alto);
        }

        // Barra de vida sobre el jugador
        g.setColor(Color.RED);
        g.fillRect(x, y - 10, (int)((double)ancho * vida / vidaMaxima), 5);
        g.setColor(Color.BLACK);
        g.drawRect(x, y - 10, ancho, 5);
    }

    public void mover(int dx, int dy) {
        x += dx;
        y += dy;

        if (x < 0) x = 0;
        if (x + ancho > LIMITE_ANCHO) x = LIMITE_ANCHO - ancho;
        if (y < 0) y = 0;
        if (y + alto > LIMITE_ALTO) y = LIMITE_ALTO - alto;
    }

    public void manejarTeclaPresionada(int tecla) {
        switch (tecla) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> {
                mover(0, -velocidad);
                direccion = "arriba";
            }
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                mover(0, velocidad);
                direccion = "abajo";
            }
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                mover(-velocidad, 0);
                direccion = "izquierda";
            }
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                mover(velocidad, 0);
                direccion = "derecha";
            }
            case KeyEvent.VK_1 -> inventario.usarObjeto("Poción Curativa", this);
            case KeyEvent.VK_2 -> inventario.usarObjeto("Semilla Rara", this);
            case KeyEvent.VK_3 -> inventario.usarObjeto("Esencia Mágica", this);
        }
    }

    public void recibirDanio(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public void curar(int cantidad) {
        vida += cantidad;
        if (vida > vidaMaxima) vida = vidaMaxima;
    }

    public void aumentarPuntaje(int puntos) {
        puntaje += puntos;
        if (puntaje < 0) puntaje = 0;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    // Getters y Setters
    public int getVida() { return vida; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getPuntaje() { return puntaje; }
    public Inventario getInventario() { return inventario; }
    public String getDireccion() { return direccion; }
    public Nivel getNivel() { return nivel; }
    public void setNivel(Nivel nivel) { this.nivel = nivel; }
    public int getVelocidad() { return velocidad; }
    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }
}
