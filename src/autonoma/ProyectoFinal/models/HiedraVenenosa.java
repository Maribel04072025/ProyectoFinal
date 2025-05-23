/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * Planta que daña o ralentiza al jugador al tocarlo.
 */
public class HiedraVenenosa extends PlantaCorrupta {

    private Image imagen;

    public HiedraVenenosa(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);

        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/HiedraVenenosa.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else {
            g.setColor(new Color(100, 255, 100));
            g.fillOval(x, y, ancho, alto);
        }
    }

    
}
