
package autonoma.ProyectoFinal.models;

import autonoma.ProyectoFinal.interfaces.Dañable;
import autonoma.ProyectoFinal.models.Entidad;
import java.awt.Color;
import java.awt.Graphics;


public class PlantaCorrupta extends Entidad implements Runnable, Dañable {
    private boolean activa = true;
    private int vida = 50;

    public PlantaCorrupta(int x, int y) {
        super(x, y, 40, 40);
        new Thread(this).start();
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, ancho, alto);
    }

    @Override
    public void actualizar() {
        // Movimiento controlado por run()
    }

    @Override
    public void run() {
        while (activa) {
            try {
                x += (Math.random() > 0.5 ? 5 : -5);
                y += (Math.random() > 0.5 ? 5 : -5);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void recibirDaño(int cantidad) {
        vida -= cantidad;
        if (vida <= 0) {
            activa = false;
            // podría haber lógica para eliminarla del juego
        }
    }

    public boolean estaMuerta() {
        return vida <= 0;
    }
}