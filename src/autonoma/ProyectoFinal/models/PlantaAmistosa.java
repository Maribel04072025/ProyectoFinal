/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class PlantaAmistosa extends Entidad {

    private boolean activa;
    private Image imagen;

    public PlantaAmistosa(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        this.activa = true;

        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/PlantaAmistosa.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    @Override
    public void actualizar() {
        // No tiene movimiento, pero se requiere implementar
    }

    @Override
    public void dibujar(Graphics g) {
        if (activa && imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else if (activa) {
            g.setColor(java.awt.Color.YELLOW);
            g.fillOval(x, y, ancho, alto);
        }
    }

    public boolean estaActiva() {
        return activa;
    }

    public void recolectar() {
        activa = false;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}

