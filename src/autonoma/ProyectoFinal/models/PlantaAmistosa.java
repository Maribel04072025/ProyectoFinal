/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

/**
 * Representa una planta amigable dentro del juego que el jugador puede recolectar para obtener beneficios.
 * 
 * Esta planta no se mueve, permanece estática en el nivel y puede ser recolectada por el jugador.
 * Una vez recolectada, la planta deja de estar activa y ya no se dibuja en pantalla.
 * 
 * La planta intenta cargar una imagen para su representación gráfica, y si no está disponible,
 * se dibuja un óvalo amarillo como representación alternativa.
 * 
 * @author marib
 * @version 1.0
 * @since 2025-05-24
 */
public class PlantaAmistosa extends Entidad {

    private boolean activa;
    private Image imagen;

    /**
     * Crea una nueva planta amigable con posición y tamaño especificados.
     *
     * @param x      Posición horizontal en el nivel.
     * @param y      Posición vertical en el nivel.
     * @param ancho  Ancho para dibujar la planta.
     * @param alto   Alto para dibujar la planta.
     */
    public PlantaAmistosa(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        this.activa = true;

        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/PlantaAmistosa.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    /**
     * Actualiza el estado de la planta.
     * Esta planta no tiene movimiento ni comportamiento dinámico, por lo que este método está vacío.
     */
    @Override
    public void actualizar() {
        // No tiene movimiento, pero se requiere implementar
    }

    /**
     * Dibuja la planta en pantalla si está activa.
     * Intenta dibujar la imagen si está disponible, o un óvalo amarillo como fallback.
     *
     * @param g Contexto gráfico donde se dibuja la planta.
     */
    @Override
    public void dibujar(Graphics g) {
        if (activa && imagen != null) {
            g.drawImage(imagen, x, y, ancho, alto, null);
        } else if (activa) {
            g.setColor(java.awt.Color.YELLOW);
            g.fillOval(x, y, ancho, alto);
        }
    }

    /**
     * Indica si la planta sigue activa (no recolectada).
     *
     * @return {@code true} si la planta está activa, {@code false} si ha sido recolectada.
     */
    public boolean estaActiva() {
        return activa;
    }

    /**
     * Marca la planta como recolectada, desactivándola para que no sea visible ni usable.
     */
    public void recolectar() {
        activa = false;
    }

    /**
     * Obtiene el área rectangular que ocupa la planta para detección de colisiones.
     *
     * @return Un objeto {@link Rectangle} con la posición y tamaño de la planta.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
