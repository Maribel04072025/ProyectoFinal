/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autonoma.ProyectoFinal.views;

import autonoma.ProyectoFinal.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Panel principal del juego.
 */
public class Juego extends JPanel {

    private Nivel nivel;
    private Timer timerJuego;
    private Timer timerEnemigos;
    private Timer timerAmistosas;
    private Timer timerObjetos;
    private boolean juegoTerminado = false;
    private Image fondoJuego;

    public Juego() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.BLACK);
        nivel = new Nivel(800, 600);
        setFocusable(true);
        requestFocusInWindow();

        // Cargar imagen de fondo
        try {
            fondoJuego = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/fondo_juego.png")).getImage();
        } catch (Exception e) {
            fondoJuego = null;
        }

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
                    case KeyEvent.VK_1 -> jugador.getInventario().usarObjeto("Poción Curativa", jugador);
                    case KeyEvent.VK_2 -> jugador.getInventario().usarObjeto("Semilla Rara", jugador);
                    case KeyEvent.VK_3 -> jugador.getInventario().usarObjeto("Esencia Mágica", jugador);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                Jugador jugador = nivel.getJugador();
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN ||
                    e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    jugador.mover(0, 0);
                }
            }
        });

        iniciarTimers();
    }

    private void iniciarTimers() {
        // Lógica general
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

        // Enemigos
        timerEnemigos = new Timer(2000, e -> {
            if (!juegoTerminado) nivel.generarEnemigo();
        });
        timerEnemigos.start();

        // Plantas amistosas
        timerAmistosas = new Timer(10000, e -> {
            if (!juegoTerminado) nivel.generarPlantaAmistosa();
        });
        timerAmistosas.start();

        // Objetos del inventario
        timerObjetos = new Timer(15000, e -> {
            if (!juegoTerminado) nivel.generarObjeto();
        });
        timerObjetos.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (fondoJuego != null) {
            g.drawImage(fondoJuego, 0, 0, getWidth(), getHeight(), this);
        }

        nivel.dibujar(g);

        // Puntaje
        g.setColor(Color.WHITE);
        g.drawString("Puntaje: " + nivel.getPuntaje(), 10, 20);

        // Vida
        int vida = nivel.getJugador().getVida();
        g.setColor(Color.GRAY);
        g.fillRect(10, 40, 200, 15);
        g.setColor(Color.GREEN);
        g.fillRect(10, 40, (int)(200 * (vida / 100.0)), 15);
        g.setColor(Color.WHITE);
        g.drawRect(10, 40, 200, 15);
        g.drawString("Vida: " + vida, 80, 52);

        // Inventario
        int x = 600;
        int y = 20;
        g.drawString("Inventario:", x, y);
        int i = 1;
        for (ObjetoInventario obj : nivel.getJugador().getInventario().getObjetos()) {
            g.drawString(i + ". " + obj.getNombre(), x, y + i * 15);
            i++;
        }
    }

    public void finalizarJuego() {
        timerJuego.stop();
        timerEnemigos.stop();
        timerAmistosas.stop();
        timerObjetos.stop();
        juegoTerminado = true;

        ArchivoPuntaje.guardarPuntaje(nivel.getPuntaje());

        JOptionPane.showMessageDialog(this,
            "¡Juego terminado!\nPuntaje: " + nivel.getPuntaje(),
            "Fin del juego", JOptionPane.INFORMATION_MESSAGE);

        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
        ventana.dispose();

        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
        });
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

