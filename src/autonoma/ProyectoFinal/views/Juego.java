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

public class Juego extends JPanel {

    private Nivel nivel;
    private Timer timerJuego;
    private Timer timerEnemigos;
    private boolean juegoTerminado = false;

    public Juego() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        nivel = new Nivel(800, 600);
        setFocusable(true);
        requestFocusInWindow();

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
        timerJuego = new Timer(1000 / 60, e -> {
            if (!juegoTerminado) {
                nivel.actualizar();
                repaint();
            }
        });
        timerJuego.start();

        timerEnemigos = new Timer(2000, e -> {
            if (!juegoTerminado) {
                nivel.generarEnemigo();
            }
        });
        timerEnemigos.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        nivel.dibujar(g);
        g.setColor(Color.WHITE);
        g.drawString("Puntaje: " + nivel.getPuntaje(), 10, 20);
    }

    public void finalizarJuego() {
        timerJuego.stop();
        timerEnemigos.stop();
        juegoTerminado = true;
        ArchivoPuntaje.guardarPuntaje(nivel.getPuntaje());
        JOptionPane.showMessageDialog(this, "Juego terminado. Puntaje: " + nivel.getPuntaje());
    }
}

    @SuppressWarnings("unchecked")
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

