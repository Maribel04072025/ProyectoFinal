/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import autonoma.ProyectoFinal.interfaces.Recolectable;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author marib
 */
public class PlantaAmistosa extends Entidad implements Recolectable {
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

    @Override
    public int getPuntos() {
        return 15;
    }

    public void curarJugador(Jugador jugador) {
        jugador.curar(20);
    }
}