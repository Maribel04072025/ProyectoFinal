/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * Planta corrupta más rápida que se mueve agresivamente.
 */
public class FlorCarnivora extends PlantaCorrupta {

    private Image imagen;

    public FlorCarnivora(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/FlorCarnivora.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.MAGENTA);
            g.fillOval(x, y, ancho, alto);
        }
    }
}
