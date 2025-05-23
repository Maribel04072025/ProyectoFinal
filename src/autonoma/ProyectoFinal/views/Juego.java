/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autonoma.ProyectoFinal.views;

import autonoma.ProyectoFinal.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Juego extends JPanel {

    private Nivel nivel;
    private Timer timerJuego;
    private Timer timerEnemigos;
    private Timer timerPlantas;
    private Timer timerObjetos;
    private Image fondoJuego;
    private boolean juegoTerminado = false;

    public Juego(int dificultad) {
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
        requestFocusInWindow();
        setBackground(Color.BLACK);

        nivel = new Nivel(800, 600, dificultad);

        try {
            fondoJuego = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/fondo_juego.png")).getImage();
        } catch (Exception e) {
            fondoJuego = null;
        }

        // Control del teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Jugador j = nivel.getJugador();
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> j.mover(0, -1);
                    case KeyEvent.VK_DOWN -> j.mover(0, 1);
                    case KeyEvent.VK_LEFT -> j.mover(-1, 0);
                    case KeyEvent.VK_RIGHT -> j.mover(1, 0);
                    case KeyEvent.VK_SPACE -> nivel.disparar();
                    case KeyEvent.VK_1 -> j.getInventario().usarObjeto("Poción Curativa", j);
                    case KeyEvent.VK_2 -> j.getInventario().usarObjeto("Semilla Rara", j);
                    case KeyEvent.VK_3 -> j.getInventario().usarObjeto("Esencia Mágica", j);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN ||
                    e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    nivel.getJugador().mover(0, 0);
                }
            }
        });

        iniciarTimers(dificultad);
    }

    private void iniciarTimers(int dificultad) {
        // Timer general del juego
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

        // Enemigos más frecuentes según dificultad
        int intervaloEnemigos = Math.max(500, 2000 - dificultad * 300);
        timerEnemigos = new Timer(intervaloEnemigos, e -> nivel.generarEnemigo());
        timerEnemigos.start();

        // Plantas amistosas (menos en dificultad alta)
        int intervaloPlantas = Math.max(3000, 10000 - dificultad * 2000);
        timerPlantas = new Timer(intervaloPlantas, e -> nivel.generarPlantaAmistosa());
        timerPlantas.start();

        // Objetos
        int intervaloObjetos = Math.max(4000, 15000 - dificultad * 2000);
        timerObjetos = new Timer(intervaloObjetos, e -> nivel.generarObjeto());
        timerObjetos.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondoJuego != null) {
            g.drawImage(fondoJuego, 0, 0, getWidth(), getHeight(), this);
        }

        nivel.dibujar(g);

        // UI: Puntaje
        g.setColor(Color.WHITE);
        g.drawString("Puntaje: " + nivel.getPuntaje(), 10, 20);

        // Barra de vida
        Jugador j = nivel.getJugador();
        int vida = j.getVida();
        int vidaMax = j.getVidaMaxima();

        g.setColor(Color.GRAY);
        g.fillRect(10, 40, 200, 15);
        g.setColor(Color.GREEN);
        g.fillRect(10, 40, (int)(200 * (vida / (double)vidaMax)), 15);
        g.setColor(Color.WHITE);
        g.drawRect(10, 40, 200, 15);
        g.drawString("Vida: " + vida + " / " + vidaMax, 80, 53);

        // Inventario
        int x = 600;
        int y = 20;
        g.drawString("Inventario:", x, y);
        int i = 1;
        for (ObjetoInventario obj : j.getInventario().getObjetos()) {
            g.drawString(i + ". " + obj.getNombre(), x, y + i * 15);
            i++;
        }
    }

    public void finalizarJuego() {
        juegoTerminado = true;

        timerJuego.stop();
        timerEnemigos.stop();
        timerPlantas.stop();
        timerObjetos.stop();

        ArchivoPuntaje.guardarPuntaje(nivel.getPuntaje());

        JOptionPane.showMessageDialog(this,
                "¡Juego terminado!\nPuntaje: " + nivel.getPuntaje(),
                "Fin del juego", JOptionPane.INFORMATION_MESSAGE);

        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
        ventana.dispose();

        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menu = new MenuPrincipal();
            JFrame nuevaVentana = new JFrame("El Reino de las Plantas Mágicas");
            nuevaVentana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            nuevaVentana.setContentPane(menu);
            nuevaVentana.pack();
            nuevaVentana.setLocationRelativeTo(null);
            nuevaVentana.setVisible(true);
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

