/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Cactus explosivo — hace daño medio y explota.
 */
public class CactusExplosivo extends PlantaCorrupta {

    private boolean explotado = false;

    public CactusExplosivo(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        try {
            imagenPlanta = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/CactusExplosivo.png")).getImage();
        } catch (Exception e) {
            imagenPlanta = null;
        }
    }

    public void explotar() {
        this.explotado = true;
        detener();
    }

    public boolean haExplotado() {
        return explotado;
    }

    @Override
    public int getDanio() {
        return 15;
    }

    @Override
    public int getPenalizacionPuntaje() {
        return 10;
    }

    @Override
    public void dibujar(Graphics g) {
        if (!explotado && imagenPlanta != null) {
            g.drawImage(imagenPlanta, x, y, ancho, alto, null);
        } else if (!explotado) {
            g.setColor(java.awt.Color.ORANGE);
            g.fillRect(x, y, ancho, alto);
        }
    }
}
