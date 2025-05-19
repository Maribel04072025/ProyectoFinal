/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autonoma.ProyectoFinal.views;

import autonoma.ProyectoFinal.models.Entidad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Juego extends JPanel implements KeyListener {

    private ArrayList<Entidad> entidades;
    private PlantaJugador jugador;
    private Timer timer;
    private int puntaje = 0;
    private Random random = new Random();

    public Juego() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(new Color(200, 255, 200));
        setFocusable(true);
        addKeyListener(this);

        entidades = new ArrayList<>();

        jugador = new PlantaJugador(400, 500);
        entidades.add(jugador);

        // Bucle del juego
        timer = new Timer(30, e -> {
            actualizar();
            repaint();
        });
        timer.start();
    }

    private void actualizar() {
        Iterator<Entidad> it = entidades.iterator();
        while (it.hasNext()) {
            Entidad e = it.next();
            e.actualizar();

            if (e != jugador && jugador.getBounds().intersects(e.getBounds())) {
                puntaje += e.getPuntos();
                it.remove();
            }

            if (e.getY() > getHeight()) {
                it.remove();
            }
        }

        if (random.nextDouble() < 0.02) {
            entidades.add(new HiedraVenenosa(random.nextInt(760), -40));
        }
        if (random.nextDouble() < 0.015) {
            entidades.add(new FlorCarnivora(random.nextInt(760), -40));
        }
        if (random.nextDouble() < 0.01) {
            entidades.add(new PlantaAmistosa(random.nextInt(760), -40));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Entidad e : entidades) {
            e.dibujar(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Puntaje: " + puntaje, 10, 20);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_LEFT) jugador.mover(-10, 0);
        if (tecla == KeyEvent.VK_RIGHT) jugador.mover(10, 0);
        if (tecla == KeyEvent.VK_UP) jugador.mover(0, -10);
        if (tecla == KeyEvent.VK_DOWN) jugador.mover(0, 10);
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
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

