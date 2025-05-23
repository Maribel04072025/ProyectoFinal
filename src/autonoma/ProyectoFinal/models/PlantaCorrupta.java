
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;

/**
 * Planta enemiga base. Se mueve aleatoriamente en un hilo.
 * Implementa Runnable y sobrescribe método abstracto actualizar().
 */
public class PlantaCorrupta extends Entidad implements Runnable {

    protected int velocidad;
    protected boolean activa;
    protected Random random;
    protected Image imagenPlanta;

    public PlantaCorrupta(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        this.velocidad = 2;
        this.activa = true;
        this.random = new Random();

        try {
            imagenPlanta = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/PlantaCorrupta.png")).getImage();
        } catch (Exception e) {
            imagenPlanta = null;
        }
    }

    /**
     * Movimiento aleatorio automático de la planta en un hilo.
     */
    @Override
    public void run() {
        while (activa) {
            int dx = random.nextInt(3) - 1; // -1, 0, 1
            int dy = random.nextInt(3) - 1;
            x += dx * velocidad;
            y += dy * velocidad;

            // Limites
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
     * Método requerido por la clase abstracta Entidad.
     * En este caso no se usa porque se maneja con hilos.
     */
    @Override
    public void actualizar() {
        // Este método está vacío porque se usa run() para el movimiento.
    }

    public void detener() {
        activa = false;
    }

    public boolean estaActiva() {
        return activa;
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagenPlanta != null) {
            g.drawImage(imagenPlanta, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.RED);
            g.fillOval(x, y, ancho, alto);
        }
    }

    public int getDanio() {
        return 10;
    }

    public int getPenalizacionPuntaje() {
        return 0;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}