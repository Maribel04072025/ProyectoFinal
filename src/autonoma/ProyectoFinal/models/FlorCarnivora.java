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

public class FlorCarnivora extends PlantaCorrupta {
    public FlorCarnivora(int x, int y) {
        super(x, y);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x, y, ancho, alto);
    }
}
