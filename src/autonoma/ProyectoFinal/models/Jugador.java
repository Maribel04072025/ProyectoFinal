/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 * Representa al jugador dentro del juego. Puede moverse, recibir daño,
 * curarse, usar objetos del inventario y ganar puntos.
 * 
 * Su comportamiento varía según la dificultad seleccionada al iniciar el juego.
 * También mantiene una referencia al nivel actual para interactuar con el entorno.
 * 
 * @author TuNombre
 * @version 20250524
 * @since 1.0
 * @see Inventario
 * @see Nivel
 * @see Entidad
 */
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

    /**
     * Constructor del jugador. Inicializa su estado en función de la dificultad.
     *
     * @param x         Posición horizontal inicial.
     * @param y         Posición vertical inicial.
     * @param ancho     Ancho del jugador.
     * @param alto      Alto del jugador.
     * @param dificultad Nivel de dificultad (1: fácil, 2: medio, 3: difícil).
     */
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

        try {
            imagenJugador = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/jugador.png")).getImage();
        } catch (Exception e) {
            System.err.println("⚠ Imagen de jugador no encontrada");
            imagenJugador = null;
        }
    }

    /**
     * Método sobrescrito para actualizar el estado del jugador (actualmente vacío).
     */
    @Override
    public void actualizar() {}

    /**
     * Dibuja al jugador en pantalla, incluyendo una barra de vida sobre su sprite.
     *
     * @param g Contexto gráfico usado para dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        if (imagenJugador != null) {
            g.drawImage(imagenJugador, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(x, y, ancho, alto);
        }

        // Barra de vida
        g.setColor(Color.RED);
        g.fillRect(x, y - 10, (int)((double)ancho * vida / vidaMaxima), 5);
        g.setColor(Color.BLACK);
        g.drawRect(x, y - 10, ancho, 5);
    }

    /**
     * Mueve al jugador dentro de los límites definidos del mapa.
     *
     * @param dx Cantidad de desplazamiento horizontal.
     * @param dy Cantidad de desplazamiento vertical.
     */
    public void mover(int dx, int dy) {
        x += dx;
        y += dy;

        if (x < 0) x = 0;
        if (x + ancho > LIMITE_ANCHO) x = LIMITE_ANCHO - ancho;
        if (y < 0) y = 0;
        if (y + alto > LIMITE_ALTO) y = LIMITE_ALTO - alto;
    }

    /**
     * Maneja la interacción del jugador con el teclado.
     *
     * @param tecla Código de tecla presionada.
     */
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

    /**
     * Reduce la vida del jugador cuando recibe daño.
     *
     * @param cantidad Cantidad de daño recibido.
     */
    public void recibirDanio(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    /**
     * Aumenta la vida del jugador sin exceder el máximo.
     *
     * @param cantidad Cantidad de vida a recuperar.
     */
    public void curar(int cantidad) {
        vida += cantidad;
        if (vida > vidaMaxima) vida = vidaMaxima;
    }

    /**
     * Aumenta el puntaje del jugador. No permite valores negativos.
     *
     * @param puntos Cantidad de puntos a sumar.
     */
    public void aumentarPuntaje(int puntos) {
        puntaje += puntos;
        if (puntaje < 0) puntaje = 0;
    }

    /**
     * Obtiene el área rectangular ocupada por el jugador, útil para colisiones.
     *
     * @return Un objeto Rectangle con los límites del jugador.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    // Getters y Setters

    /**
     * @return Vida actual del jugador.
     */
    public int getVida() { return vida; }

    /**
     * @return Vida máxima del jugador.
     */
    public int getVidaMaxima() { return vidaMaxima; }

    /**
     * @return Puntaje actual del jugador.
     */
    public int getPuntaje() { return puntaje; }

    /**
     * @return Inventario del jugador.
     */
    public Inventario getInventario() { return inventario; }

    /**
     * @return Dirección actual del movimiento del jugador.
     */
    public String getDireccion() { return direccion; }

    /**
     * @return Nivel actual del juego en el que se encuentra el jugador.
     */
    public Nivel getNivel() { return nivel; }

    /**
     * Asigna el nivel al que pertenece el jugador.
     *
     * @param nivel Nivel actual del juego.
     */
    public void setNivel(Nivel nivel) { this.nivel = nivel; }

    /**
     * @return Velocidad actual del jugador.
     */
    public int getVelocidad() { return velocidad; }

    /**
     * Asigna la velocidad del jugador.
     *
     * @param velocidad Nueva velocidad.
     */
    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }
}