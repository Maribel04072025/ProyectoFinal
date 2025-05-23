/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * Planta que explota al ser tocada y desaparece.
 */
public class CactusExplosivo extends PlantaCorrupta {

    private Image imagen;
    private boolean explotado = false;

    public CactusExplosivo(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/CactusExplosivo.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    @Override
    public void dibujar(Graphics g) {
        if (explotado) return;

        if (imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else {
            g.setColor(new Color(0, 200, 0));
            g.fillRect(x, y, ancho, alto);
        }
    }

    public void explotar() {
        this.explotado = true;
        detener();
    }

    public boolean haExplotado() {
        return explotado;
    }
}
