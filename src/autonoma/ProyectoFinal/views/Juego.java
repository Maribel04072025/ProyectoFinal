/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autonoma.ProyectoFinal.views;

import autonoma.ProyectoFinal.models.ArchivoPuntaje;
import autonoma.ProyectoFinal.models.Jugador;
import autonoma.ProyectoFinal.models.Nivel;
import autonoma.ProyectoFinal.models.ReproductorMusica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clase que representa el panel principal del juego donde se visualiza y ejecuta la lógica del mismo.
 * Contiene el nivel actual, los bucles de actualización, generación de enemigos, objetos e interfaz gráfica.
 * También gestiona la pausa del juego y la finalización del mismo.
 * 
 * @author maribel ceballos 
 * @version 1.0
 * @since 2025-05-24
 */
public class Juego extends JPanel {

    private Nivel nivel;
    private Timer timerActualizar;
    private Timer timerEnemigos;
    private Timer timerAmistosas;
    private Timer timerObjetos;
    private boolean enPausa = false;
    private ReproductorMusica musicaFondo;
    private Image fondo;
    private int dificultad;

    /**
     * Constructor que inicializa el panel del juego con una dificultad específica.
     * 
     * @param dificultad nivel de dificultad (1 = fácil, 2 = medio, 3 = difícil)
     */
    public Juego(int dificultad) {
        this.dificultad = dificultad;
        this.setPreferredSize(new Dimension(900, 700));
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocusInWindow();

        musicaFondo = new ReproductorMusica();
        musicaFondo.reproducirMusica("/autonoma/ProyectoFinal/musica/MusicaFondo.wav", true);

        try {
            fondo = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/fondo_juego.png")).getImage();
        } catch (Exception e) {
            System.err.println("⚠ Fondo no encontrado");
            fondo = null;
        }

        nivel = new Nivel(1000, 700, dificultad);

        iniciarTimers();
        manejarEventosTeclado();
    }

    /**
     * Inicia los temporizadores que controlan la lógica del juego: actualización, enemigos, plantas amistosas y objetos.
     */
    private void iniciarTimers() {
        timerActualizar = new Timer(30, e -> {
            if (!enPausa) {
                nivel.actualizar();
            }
            if (nivel.getJugador().getVida() <= 0) {
                finalizarJuego();
            }
            repaint();
        });
        timerActualizar.start();

        timerEnemigos = new Timer(getTiempoEnemigos(), e -> nivel.generarEnemigo());
        timerEnemigos.start();

        timerAmistosas = new Timer(10000, e -> nivel.generarPlantaAmistosa());
        timerAmistosas.start();

        timerObjetos = new Timer(12000, e -> nivel.generarObjeto());
        timerObjetos.start();
    }

    /**
     * Determina el tiempo de aparición de enemigos según la dificultad.
     * 
     * @return tiempo en milisegundos
     */
    private int getTiempoEnemigos() {
        return switch (dificultad) {
            case 1 -> 4000;
            case 2 -> 2500;
            default -> 1500;
        };
    }

    /**
     * Configura los eventos de teclado para pausar el juego, salir o controlar al jugador.
     */
    private void manejarEventosTeclado() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    enPausa = !enPausa;
                    repaint();
                } else if (enPausa && e.getKeyCode() == KeyEvent.VK_S) {
                    finalizarJuego();
                } else if (!enPausa) {
                    Jugador j = nivel.getJugador();
                    j.manejarTeclaPresionada(e.getKeyCode());

                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        nivel.disparar();
                    }
                }
            }
        });

        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    /**
     * Finaliza el juego actual, detiene los timers y muestra el puntaje del jugador.
     * Luego vuelve al menú principal.
     */
    private void finalizarJuego() {
        timerActualizar.stop();
        timerEnemigos.stop();
        timerAmistosas.stop();
        timerObjetos.stop();

        int puntaje = nivel.getPuntaje();
        ArchivoPuntaje.guardarPuntaje(puntaje);

        JOptionPane.showMessageDialog(this, "¡Has perdido!\nPuntaje: " + puntaje);

        JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
        ventana.dispose();

        JFrame menu = new JFrame("Menú");
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setContentPane(new MenuPrincipal());
        menu.pack();
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
    }

    /**
     * Método que se llama automáticamente para pintar el componente en pantalla.
     * 
     * @param g el objeto Graphics con el cual se dibuja
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }

        nivel.dibujar(g);
        nivel.getJugador().getInventario().dibujar(g);

        Jugador j = nivel.getJugador();
        int vida = j.getVida();
        int vidaMax = j.getVidaMaxima();

        // Barra de vida
        g.setColor(Color.GRAY);
        g.fillRect(10, 650, 200, 20);
        g.setColor(Color.GREEN);
        g.fillRect(10, 650, (int)(200 * (vida / (double)vidaMax)), 20);
        g.setColor(Color.WHITE);
        g.drawRect(10, 650, 200, 20);
        g.drawString("Vida: " + vida + " / " + vidaMax, 75, 665);

        // Modo pausa
        if (enPausa) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, getWidth(), getHeight());

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("JUEGO EN PAUSA", getWidth() / 2 - 100, getHeight() / 2 - 20);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Presiona ESC para continuar", getWidth() / 2 - 110, getHeight() / 2 + 10);
            g.drawString("Presiona S para salir al menú", getWidth() / 2 - 120, getHeight() / 2 + 40);
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

    private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Container getContentPane() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void pack() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}