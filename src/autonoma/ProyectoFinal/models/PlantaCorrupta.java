
package autonoma.ProyectoFinal.models;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Color;

/**
 * Representa una planta enemiga corrompida que se mueve automáticamente.
 * Hereda de Entidad e implementa Runnable para comportamiento en hilos.
 */
public class PlantaCorrupta extends Entidad implements Runnable {

    private int velocidad;
    private boolean activa;
    private Random random;
    private Image imagenPlanta;

    /**
     * Constructor de PlantaCorrupta
     * @param x posición x inicial
     * @param y posición y inicial
     * @param ancho ancho del sprite
     * @param alto alto del sprite
     */
    public PlantaCorrupta(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        this.velocidad = 2;
        this.activa = true;
        this.random = new Random();

        // Cargar imagen de la planta corrupta (opcional, puedes personalizar)
        try {
            imagenPlanta = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/FlorCarnivora.png")).getImage();
        } catch (Exception e) {
            imagenPlanta = null;
            System.err.println("No se pudo cargar la imagen de la planta corrupta: " + e.getMessage());
        }
    }

    /**
     * Dibuja la planta corrupta, usando una imagen si está disponible.
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
     * Movimiento automático aleatorio de la planta, ejecutado en un hilo.
     */
    @Override
    public void run() {
        while (activa) {
            int dx = random.nextInt(3) - 1;
            int dy = random.nextInt(3) - 1;

            x += dx * velocidad;
            y += dy * velocidad;

            // Límite del área de juego
            if (x < 0) x = 0;
            if (y < 0) y = 0;
            if (x + ancho > 800) x = 800 - ancho;
            if (y + alto > 600) y = 600 - alto;

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Detiene el hilo de movimiento.
     */
    public void detener() {
        this.activa = false;
    }

    /**
     * Verifica si la planta está activa.
     */
    public boolean estaActiva() {
        return activa;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    @Override
    public void actualizar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}