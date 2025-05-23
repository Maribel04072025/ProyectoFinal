/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

/**
 * Planta especial que otorga puntos o cura al jugador cuando colisiona con ella.
 */
public class PlantaAmistosa extends Entidad {

    private Image imagen;
    private boolean activa;

    public PlantaAmistosa(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        this.activa = true;

        // Cargar imagen si existe, sino se dibuja como óvalo azul
        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/PlantaAmistosa.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    @Override
    public void dibujar(Graphics g) {
        if (!activa) return;

        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.CYAN);
            g.fillOval(x, y, ancho, alto);
        }
    }

    @Override
    public void actualizar() {
        // Planta estática, no hace nada por sí misma
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