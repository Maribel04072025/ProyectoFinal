/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Representa una Esencia Mágica que el jugador puede recolectar.
 * 
 * Al ser utilizada, incrementa temporalmente la velocidad del jugador.
 * La mejora de velocidad dura 30 segundos y luego se revierte automáticamente.
 * 
 * Se representa visualmente mediante una imagen o un óvalo cian si la imagen no está disponible.
 * 
 * @author maribel ceballos 
 * @version 1.0
 * @since 2025-05-24
 */
public class EsenciaMagica extends ObjetoInventario {

    private Image imagen;

    /**
     * Crea una nueva instancia de Esencia Mágica en la posición especificada.
     * 
     * @param x posición horizontal
     * @param y posición vertical
     */
    public EsenciaMagica(int x, int y) {
        super(x, y, 32, 32, "Esencia Mágica");

        try {
            imagen = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/esencia_magica.png")).getImage();
        } catch (Exception e) {
            imagen = null;
        }
    }

    /**
     * Aplica el efecto de la esencia mágica al jugador, aumentando temporalmente su velocidad.
     * La velocidad aumentada se revierte automáticamente después de 30 segundos.
     * 
     * @param jugador el jugador que usa la esencia
     */
    @Override
    public void aplicarEfecto(Jugador jugador) {
        int velocidadOriginal = jugador.getVelocidad();
        jugador.setVelocidad(velocidadOriginal + 3);

        // Regresa a la velocidad original después de 30 segundos
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                jugador.setVelocidad(velocidadOriginal);
            }
        }, 30000);
    }

    /**
     * Dibuja la esencia mágica en pantalla.
     * 
     * @param g el objeto Graphics con el cual se dibuja
     */
    @Override
    public void dibujar(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, getX(), getY(), getAncho(), getAlto(), null);
        } else {
            g.setColor(Color.CYAN);
            g.fillOval(getX(), getY(), getAncho(), getAlto());
        }
    }

    /**
     * Devuelve los límites del área ocupada por la esencia para detectar colisiones.
     * 
     * @return Rectangle que representa el área ocupada
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getAncho(), getAlto());
    }
}