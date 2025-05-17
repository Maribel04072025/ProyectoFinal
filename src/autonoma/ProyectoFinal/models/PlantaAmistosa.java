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

public class PlantaAmistosa extends Entidad {
    public PlantaAmistosa(int x, int y) {
        super(x, y, 30, 30);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(x, y, ancho, alto);
    }

    @Override
    public void actualizar() {}
}