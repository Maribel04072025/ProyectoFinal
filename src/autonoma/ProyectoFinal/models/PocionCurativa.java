/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.swing.*;
import java.awt.*;

/**
 * Representa una poción curativa que puede ser recogida y usada por el jugador.
 * 
 * La poción tiene una posición y tamaño en el mapa, además de una imagen para su representación gráfica.
 * Al ser usada, cura al jugador una cantidad fija de puntos de vida.
 * 
 * Extiende {@link ObjetoInventario} para ser parte del inventario y poder ser dibujada y recogida.
 * 
 * @author Juan Jacobo Cañas 
 * @version 1.0
 * @since 2025-05-24
 */
public class PocionCurativa extends ObjetoInventario {

    private Image imagen;

    /**
     * Crea una nueva poción curativa en una posición específica.
     * 
     * @param x Posición horizontal en el mapa.
     * @param y Posición vertical en el mapa.
     */
    public PocionCurativa(int x, int y) {
        super(x, y, 32, 32, "Poción Curativa");

        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/pocion_curativa.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    /**
     * Aplica el efecto curativo al jugador.
     * Incrementa la vida del jugador en 30 puntos.
     * 
     * @param jugador El jugador que recibirá la curación.
     */
    @Override
    public void aplicarEfecto(Jugador jugador) {
        jugador.curar(30);
    }

    /**
     * Dibuja la poción en el contexto gráfico dado.
     * Si la imagen está disponible, la dibuja; de lo contrario, dibuja un óvalo rosa.
     * 
     * @param g Contexto gráfico donde se dibuja la poción.
     */
    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, getX(), getY(), getAncho(), getAlto(), null);
        } else {
            g.setColor(Color.PINK);
            g.fillOval(getX(), getY(), getAncho(), getAlto());
        }
    }

    /**
     * Obtiene el área rectangular que ocupa la poción para detección de colisiones.
     * 
     * @return Objeto {@link Rectangle} con posición y tamaño de la poción.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getAncho(), getAlto());
    }
}