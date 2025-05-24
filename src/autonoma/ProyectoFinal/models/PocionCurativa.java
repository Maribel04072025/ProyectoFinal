/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.swing.*;
import java.awt.*;

public class PocionCurativa extends ObjetoInventario {

    private Image imagen;

    public PocionCurativa(int x, int y) {
        super(x, y, 32, 32, "Poción Curativa");

        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/pocion_curativa.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.curar(30); // o la cantidad que prefieras
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, getX(), getY(), getAncho(), getAlto(), null);
        } else {
            g.setColor(Color.PINK);
            g.fillOval(getX(), getY(), getAncho(), getAlto());
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getAncho(), getAlto());
    }
}