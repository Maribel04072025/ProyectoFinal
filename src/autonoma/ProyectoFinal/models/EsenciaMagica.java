/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class EsenciaMagica extends ObjetoInventario {

    private Image imagen;

    public EsenciaMagica(int x, int y) {
        super(x, y, 32, 32, "Esencia Mágica");

        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/esencia_magica.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        int velocidadOriginal = jugador.getVelocidad();
        jugador.setVelocidad(velocidadOriginal + 3);

        // Regresar velocidad en 30 segundos
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                jugador.setVelocidad(velocidadOriginal);
            }
        }, 30000);
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, getX(), getY(), getAncho(), getAlto(), null);
        } else {
            g.setColor(Color.CYAN);
            g.fillOval(getX(), getY(), getAncho(), getAlto());
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getAncho(), getAlto());
    }
}