/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Hiedra venenosa — daña poco pero es frecuente.
 */
public class HiedraVenenosa extends PlantaCorrupta {

    public HiedraVenenosa(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        try {
            imagenPlanta = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/HiedraVenenosa.png")).getImage();
        } catch (Exception e) {
            imagenPlanta = null;
        }
    }

    @Override
    public int getDanio() {
        return 5; // daño leve
    }

    @Override
    public int getPenalizacionPuntaje() {
        return 5;
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagenPlanta != null) {
            g.drawImage(imagenPlanta, x, y, ancho, alto, null);
        } else {
            g.setColor(java.awt.Color.GREEN);
            g.fillOval(x, y, ancho, alto);
        }
    }
}