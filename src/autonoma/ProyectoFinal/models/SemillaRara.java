/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.swing.*;
import java.awt.*;

/**
 * Representa una semilla rara que el jugador puede recolectar.
 * 
 * Al aplicar su efecto, elimina todas las plantas corruptas activas
 * del nivel actual, otorgando un efecto de limpieza en pantalla.
 * 
 * Se dibuja con una imagen específica o un círculo magenta si la imagen no está disponible.
 * 
 * @author Juan Jacobo Cañas 
 * @version 1.0
 * @since 2025-05-24
 */
public class SemillaRara extends ObjetoInventario {

    private Image imagen;

    /**
     * Constructor que inicializa la semilla rara en una posición específica.
     * 
     * @param x posición horizontal inicial
     * @param y posición vertical inicial
     */
    public SemillaRara(int x, int y) {
        super(x, y, 32, 32, "Semilla Rara");

        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/semilla_rara.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    /**
     * Aplica el efecto de la semilla rara al jugador.
     * En este caso, elimina todas las plantas corruptas del nivel actual.
     * 
     * @param jugador el jugador que usa el objeto
     */
    @Override
    public void aplicarEfecto(Jugador jugador) {
        Nivel nivel = jugador.getNivel();
        if (nivel != null) {
            nivel.eliminarTodasLasPlantasCorruptas();
        }
    }

    /**
     * Dibuja la semilla rara en pantalla.
     * 
     * @param g objeto Graphics para dibujar la imagen o forma alternativa
     */
    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, getX(), getY(), getAncho(), getAlto(), null);
        } else {
            g.setColor(Color.MAGENTA);
            g.fillOval(getX(), getY(), getAncho(), getAlto());
        }
    }

    /**
     * Devuelve el área rectangular ocupada por la semilla rara para detección de colisiones.
     * 
     * @return Rectangle con posición y tamaño actuales
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getAncho(), getAlto());
    }
}