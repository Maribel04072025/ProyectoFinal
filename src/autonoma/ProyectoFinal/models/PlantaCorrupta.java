
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.Random;
import javax.swing.ImageIcon;

public class PlantaCorrupta extends Entidad implements Runnable {

    protected int velocidad;
    protected boolean activa;
    protected Random random;
    protected Image imagenPlanta;
    protected int danioBase = 10;
    protected int dificultad;
    private int dx, dy;
    

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

    @Override
    public void run() {
        while (activa) {
            // Calcular nueva posición
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


    @Override
    public void actualizar() {
        // no se usa porque el movimiento lo maneja run()
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

    public void detener() {
        activa = false;
    }

    public boolean estaActiva() {
        return activa;
    }

    public void setDanioBase(int dificultad) {
        this.danioBase = 10 + dificultad * 2;
    }

    public int getDanio() {
        return danioBase;
    }

    public int getPenalizacionPuntaje() {
        return 5;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
