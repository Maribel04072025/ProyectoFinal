/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Representa una planta corrupta de tipo "Cactus Explosivo".
 * 
 * Esta planta utiliza una imagen específica y hereda el comportamiento de 
 * {@link PlantaCorrupta}. Su funcionalidad principal es visualizarse
 * como un cactus explosivo dentro del entorno gráfico del juego.
 * 
 * @author TuNombre
 * @version 20250524
 * @since 1.0
 * @see PlantaCorrupta
 */
public class CactusExplosivo extends PlantaCorrupta {

    /**
     * Constructor del cactus explosivo.
     * 
     * Crea una nueva instancia de {@code CactusExplosivo} con la posición, tamaño
     * y nivel de dificultad especificados. Intenta cargar la imagen correspondiente 
     * desde los recursos del proyecto.
     * 
     * @param x Coordenada X de la planta.
     * @param y Coordenada Y de la planta.
     * @param ancho Ancho de la planta.
     * @param alto Alto de la planta.
     * @param dificultad Nivel de dificultad asociado a la planta.
     */
    public CactusExplosivo(int x, int y, int ancho, int alto, int dificultad) {
        super(x, y, ancho, alto, dificultad);
        try {
            imagenPlanta = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/CactusExplosivo.png")).getImage();
        } catch (Exception e) {
            imagenPlanta = null;
        }
    }

    /**
     * Dibuja el cactus explosivo utilizando el objeto {@code Graphics} proporcionado.
     * 
     * @param g Objeto gráfico utilizado para renderizar la planta.
     */
    @Override
    public void dibujar(Graphics g) {
        super.dibujar(g);
    }
}