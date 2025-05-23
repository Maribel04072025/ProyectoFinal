/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EsenciaMagica extends ObjetoInventario {

    public EsenciaMagica(int x, int y) {
        super(x, y, 25, 25, "Esencia Mágica");
    }

    @Override
    public void dibujar(Graphics g) {
        if (!recogido) {
            g.setColor(Color.CYAN);
            g.fillOval(x, y, ancho, alto);
        }
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        int velocidadOriginal = jugador.getVelocidad();
        jugador.setVelocidad(velocidadOriginal + 3);

        new Timer(30000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jugador.setVelocidad(velocidadOriginal);
                ((Timer) e.getSource()).stop();
            }
        }).start();
    }
}
