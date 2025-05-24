
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * Representa una planta corrupta enemiga que se mueve dentro del nivel y puede dañar al jugador.
 * 
 * Esta planta se mueve automáticamente rebotando dentro de los límites del área de juego (800x600).
 * Su velocidad depende de la dificultad establecida al crearla.
 * Puede ser detenida para eliminarla del juego.
 * 
 * Implementa la interfaz {@link Runnable} para manejar su movimiento en un hilo independiente.
 * 
 * @author marib
 * @version 1.0
 * @since 2025-05-24
 */
public class PlantaCorrupta extends Entidad implements Runnable {

    protected int velocidad;
    protected boolean activa;
    protected Random random;
    protected Image imagenPlanta;
    protected int danioBase = 10;
    protected int dificultad;
    private int dx, dy;

    /**
     * Crea una planta corrupta con posición, tamaño y dificultad especificados.
     * La dificultad determina la velocidad y daño base de la planta.
     *
     * @param x          Posición horizontal inicial.
     * @param y          Posición vertical inicial.
     * @param ancho      Ancho para dibujar la planta.
     * @param alto       Alto para dibujar la planta.
     * @param dificultad Nivel de dificultad (1-3).
     */
    public PlantaCorrupta(int x, int y, int ancho, int alto, int dificultad) {
        super(x, y, ancho, alto);
        this.activa = true;
        this.random = new Random();
        this.dificultad = dificultad;

        switch (dificultad) {
            case 1 -> this.velocidad = 0;
            case 2 -> this.velocidad = 5;
            case 3 -> this.velocidad = 10;
            default -> this.velocidad = 2;
        }

        // Dirección aleatoria inicial (-1 o 1)
        this.dx = random.nextBoolean() ? 1 : -1;
        this.dy = random.nextBoolean() ? 1 : -1;

        try {
            imagenPlanta = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/PlantaCorrupta.png")).getImage();
        } catch (Exception e) {
            imagenPlanta = null;
        }
    }

    /**
     * Ejecuta el ciclo de movimiento de la planta.
     * Se mueve rebotando dentro de los límites 800x600.
     * El ciclo se ejecuta mientras la planta esté activa.
     */
    @Override
    public void run() {
        while (activa) {
            int nuevoX = x + dx * velocidad;
            int nuevoY = y + dy * velocidad;

            // Rebotar en bordes horizontales
            if (nuevoX < 0 || nuevoX + ancho > 800) {
                dx *= -1;
                nuevoX = x + dx * velocidad;
            }

            // Rebotar en bordes verticales
            if (nuevoY < 0 || nuevoY + alto > 600) {
                dy *= -1;
                nuevoY = y + dy * velocidad;
            }

            x = nuevoX;
            y = nuevoY;

            try {
                Thread.sleep(100); // Actualiza cada 100 ms
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * Actualiza la planta.
     * No se utiliza porque el movimiento se maneja en el método {@link #run()}.
     */
    @Override
    public void actualizar() {
        // no se usa porque el movimiento lo maneja run()
    }

    /**
     * Dibuja la planta en pantalla.
     * Si la imagen está disponible, la dibuja; de lo contrario, dibuja un óvalo rojo.
     *
     * @param g Contexto gráfico para dibujar.
     */
    @Override
    public void dibujar(Graphics g) {
        if (imagenPlanta != null) {
            g.drawImage(imagenPlanta, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.RED);
            g.fillOval(x, y, ancho, alto);
        }
    }

    /**
     * Detiene la planta, desactivándola para eliminarla del juego.
     */
    public void detener() {
        activa = false;
    }

    /**
     * Indica si la planta está activa.
     *
     * @return {@code true} si la planta está activa, {@code false} si ha sido detenida.
     */
    public boolean estaActiva() {
        return activa;
    }

    /**
     * Establece el daño base de la planta en función de la dificultad.
     *
     * @param dificultad Nivel de dificultad.
     */
    public void setDanioBase(int dificultad) {
        this.danioBase = 10 + dificultad * 2;
    }

    /**
     * Obtiene el daño base que causa la planta al jugador.
     *
     * @return Valor del daño base.
     */
    public int getDanio() {
        return danioBase;
    }

    /**
     * Obtiene la penalización en puntaje que el jugador recibe al ser dañado por esta planta.
     *
     * @return Penalización de puntaje (valor fijo 5).
     */
    public int getPenalizacionPuntaje() {
        return 5;
    }

    /**
     * Obtiene el área rectangular que ocupa la planta para detección de colisiones.
     *
     * @return Objeto {@link Rectangle} con posición y tamaño de la planta.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}