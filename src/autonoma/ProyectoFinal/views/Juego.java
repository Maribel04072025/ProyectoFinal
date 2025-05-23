/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autonoma.ProyectoFinal.views;

import autonoma.ProyectoFinal.models.Nivel;
import autonoma.ProyectoFinal.models.Jugador;
import autonoma.ProyectoFinal.models.ArchivoPuntaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel principal del juego. Maneja entrada, lógica y gráficos.
 */
public class Juego extends JPanel {

    private Nivel nivel;
    private Timer timerJuego;
    private Timer timerEnemigos;
    private Timer timerAmistosas;
    private boolean juegoTerminado = false;

    public Juego() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        nivel = new Nivel(800, 600);
        setFocusable(true);
        requestFocusInWindow();

        // Teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Jugador jugador = nivel.getJugador();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> jugador.mover(0, -1);
                    case KeyEvent.VK_DOWN -> jugador.mover(0, 1);
                    case KeyEvent.VK_LEFT -> jugador.mover(-1, 0);
                    case KeyEvent.VK_RIGHT -> jugador.mover(1, 0);
                    case KeyEvent.VK_SPACE -> nivel.disparar();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Jugador jugador = nivel.getJugador();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> jugador.mover(0, 0);
                }
            }
        });

        iniciarTimers();
    }

    private void iniciarTimers() {
        // Timer del juego (actualización + repintado)
        timerJuego = new Timer(1000 / 60, e -> {
            if (!juegoTerminado) {
                nivel.actualizar();

                if (nivel.getJugador().getVida() <= 0) {
                    finalizarJuego();
                }

                repaint();
            }
        });
        timerJuego.start();

        // Enemigos cada 2s
        timerEnemigos = new Timer(2000, e -> {
            if (!juegoTerminado) {
                nivel.generarEnemigo();
            }
        });
        timerEnemigos.start();

        // Plantas amistosas cada 10s
        timerAmistosas = new Timer(10000, e -> {
            if (!juegoTerminado) {
                nivel.generarPlantaAmistosa();
            }
        });
        timerAmistosas.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        nivel.dibujar(g);

        // Mostrar puntaje
        g.setColor(Color.WHITE);
        g.drawString("Puntaje: " + nivel.getPuntaje(), 10, 20);

        // Dibujar barra de vida
        int vida = nivel.getJugador().getVida();
        int barraX = 10;
        int barraY = 40;
        int anchoMax = 200;
        int altoBarra = 15;

        // Fondo de barra
        g.setColor(Color.GRAY);
        g.fillRect(barraX, barraY, anchoMax, altoBarra);

        // Barra verde proporcional
        int anchoVida = (int) (anchoMax * (vida / 100.0));
        g.setColor(Color.GREEN);
        g.fillRect(barraX, barraY, anchoVida, altoBarra);

        // Borde de la barra
        g.setColor(Color.WHITE);
        g.drawRect(barraX, barraY, anchoMax, altoBarra);

        // Texto de vida
        g.setColor(Color.WHITE);
        g.drawString("Vida: " + vida, barraX + 70, barraY + 12);
    }

    /**
     * Lógica de finalización del juego: guarda puntaje y muestra mensaje.
     */
    public void finalizarJuego() {
        timerJuego.stop();
        timerEnemigos.stop();
        timerAmistosas.stop();
        juegoTerminado = true;

        ArchivoPuntaje.guardarPuntaje(nivel.getPuntaje());

        JOptionPane.showMessageDialog(this,
            "¡Juego terminado!\nPuntaje: " + nivel.getPuntaje(),
            "Fin del juego", JOptionPane.INFORMATION_MESSAGE);

        // Puedes volver al menú o cerrar:
        // System.exit(0); // si deseas cerrar
    }
}
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

