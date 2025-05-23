
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;

/**
 * Clase base para todas las plantas enemigas corruptas.
 * Tiene comportamiento de movimiento aleatorio en un hilo separado.
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
            imagenPlanta = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/default.png")).getImage();
        } catch (Exception e) {
            imagenPlanta = null;
        }
    }

    @Override
    public void run() {
        while (activa) {
            int dx = random.nextInt(3) - 1;
            int dy = random.nextInt(3) - 1;

            x += dx * velocidad;
            y += dy * velocidad;

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

    public void detener() {
        activa = false;
    }

    public boolean estaActiva() {
        return activa;
    }

    public void dibujar(Graphics g) {
        if (imagenPlanta != null) {
            g.drawImage(imagenPlanta, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.RED);
            g.fillOval(x, y, ancho, alto);
        }
    }

    public int getDanio() {
        return 10; // por defecto
    }

    public int getPenalizacionPuntaje() {
        return 0; // por defecto
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