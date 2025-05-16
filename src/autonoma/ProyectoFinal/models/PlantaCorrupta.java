/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

/**
 *
 * @author marib
 */
import java.awt.Color;
import java.awt.Graphics;

public class PlantaCorrupta extends Entidad implements Runnable {

    private boolean activa = true;

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
        // Movimiento automático controlado por run()
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
}
