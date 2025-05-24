/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Representa una planta corrupta de tipo "Flor Carnívora" en el juego.
 * 
 * Esta clase extiende de {@link PlantaCorrupta} y carga su imagen específica desde
 * los recursos del proyecto. Utiliza el método de dibujo definido en la clase base.
 * 
 * @author TuNombre
 * @version 20250524
 * @since 1.0
 * @see PlantaCorrupta
 */
public class FlorCarnivora extends PlantaCorrupta {

    /**
     * Constructor que crea una Flor Carnívora con sus propiedades iniciales.
     *
     * @param x Coordenada X de la posición inicial.
     * @param y Coordenada Y de la posición inicial.
     * @param ancho Ancho de la flor.
     * @param alto Alto de la flor.
     * @param dificultad Nivel de dificultad del juego (puede influir en el comportamiento).
     */
    public FlorCarnivora(int x, int y, int ancho, int alto, int dificultad) {
        super(x, y, ancho, alto, dificultad);
        try {
            imagenPlanta = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/FlorCarnivora.png")).getImage();
        } catch (Exception e) {
            imagenPlanta = null;
        }
    }

    /**
     * Dibuja la flor carnívora en la pantalla utilizando el método de la superclase.
     *
     * @param g Objeto {@code Graphics} usado para dibujar en el lienzo.
     * @see PlantaCorrupta#dibujar(Graphics)
     */
    @Override
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }
}
