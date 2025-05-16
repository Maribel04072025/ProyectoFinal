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

public class Jugador extends Entidad {

    public Jugador(int x, int y) {
        super(x, y, 40, 40);
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, ancho, alto);
    }

    @Override
    public void actualizar() {
        // Movimiento controlado por eventos de teclado (se programan en la ventana)
    }

    public void mover(int dx, int dy) {
        x += dx;
        y += dy;
    }
}
