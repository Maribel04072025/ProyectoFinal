/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Color;
import java.awt.Graphics;

public class PocionCurativa extends ObjetoInventario {

    public PocionCurativa(int x, int y) {
        super(x, y, 25, 25, "Poción Curativa");
    }

    @Override
    public void dibujar(Graphics g) {
        if (!recogido) {
            g.setColor(Color.PINK);
            g.fillOval(x, y, ancho, alto);
        }
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.curar(30); // Restaura 30 de vida
    }
}