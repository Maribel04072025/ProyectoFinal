/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.swing.*;
import java.awt.*;

public class SemillaRara extends ObjetoInventario {

    private Image imagen;

    public SemillaRara(int x, int y) {
        super(x, y, 32, 32, "Semilla Rara");

        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/semilla_rara.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        Nivel nivel = jugador.getNivel();
        if (nivel != null) {
            nivel.eliminarTodasLasPlantasCorruptas();
        }
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, getX(), getY(), getAncho(), getAlto(), null);
        } else {
            g.setColor(Color.MAGENTA);
            g.fillOval(getX(), getY(), getAncho(), getAlto());
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getAncho(), getAlto());
    }
}