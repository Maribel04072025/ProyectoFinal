/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

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
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }
}
