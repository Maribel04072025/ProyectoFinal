/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Clase que representa un rociador (proyectil) lanzado por el jugador.
 */
public class Rociador extends Entidad {
    private int velocidad;
    private boolean activo;
    private Image imagenRociador;

    /**
     * Constructor del rociador
     * @param x posición x inicial
     * @param y posición y inicial
     * @param ancho ancho del rociador
     * @param alto alto del rociador
     */
    public Rociador(int x, int y, int ancho, int alto) {
        super(x, y, ancho, alto);
        this.velocidad = 10;
        this.activo = true;

        // Puedes cambiar la imagen aquí si agregas un recurso gráfico
        try {
            imagenRociador = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/CactusExplosivo.png")).getImage();
        } catch (Exception e) {
            imagenRociador = null;
            System.err.println("No se pudo cargar la imagen del rociador: " + e.getMessage());
        }
    }

    /**
     * Dibuja el rociador (como imagen o rectángulo por defecto).
     */
    @Override
    public void dibujar(Graphics g) {
        if (imagenRociador != null) {
            g.drawImage(imagenRociador, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(x, y, ancho, alto);
        }
    }

    /**
     * Mueve el rociador hacia la derecha.
     * Lo marca como inactivo si sale del área visible.
     */
    @Override
    public void actualizar() {
        x += velocidad;
        if (x > 800) { // fuera del campo de batalla
            activo = false;
        }
    }

    /**
     * Verifica si el rociador sigue activo.
     */
    public boolean estaActivo() {
        return activo;
    }

    /**
     * Marca el rociador como inactivo (por colisión, por ejemplo).
     */
    public void desactivar() {
        this.activo = false;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
