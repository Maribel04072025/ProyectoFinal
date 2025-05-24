/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Representa una planta corrupta de tipo "Hiedra Venenosa" en el juego.
 * 
 * Esta clase extiende de {@link PlantaCorrupta} y carga su imagen específica
 * desde los recursos del proyecto. La funcionalidad de dibujo es heredada.
 * 
 * @author Maribel Ceballos 
 * @version 20250524
 * @since 1.0
 * @see PlantaCorrupta
 */
public class HiedraVenenosa extends PlantaCorrupta {

    /**
     * Constructor que crea una instancia de Hiedra Venenosa con las propiedades iniciales.
     *
     * @param x Coordenada X inicial en la pantalla.
     * @param y Coordenada Y inicial en la pantalla.
     * @param ancho Ancho de la entidad.
     * @param alto Alto de la entidad.
     * @param dificultad Nivel de dificultad del juego.
     */
    public HiedraVenenosa(int x, int y, int ancho, int alto, int dificultad) {
        super(x, y, ancho, alto, dificultad);
        try {
            imagenPlanta = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/HiedraVenenosa.png")).getImage();
        } catch (Exception e) {
            imagenPlanta = null;
        }
    }

    /**
     * Dibuja la hiedra venenosa utilizando la lógica heredada de la clase padre.
     *
     * @param g Objeto {@code Graphics} utilizado para renderizar la entidad.
     * @see PlantaCorrupta#dibujar(Graphics)
     */
    @Override
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }
}