/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autonoma.ProyectoFinal.views;

import autonoma.ProyectoFinal.models.ArchivoPuntaje;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa el menú principal del juego.
 * 
 * Este menú se muestra al inicio del juego y permite al jugador ver el récord actual,
 * comenzar una nueva partida o salir de la aplicación. También se encarga de mostrar
 * un fondo personalizado para una mejor experiencia visual.
 * 
 * Los botones están centrados y bien distribuidos visualmente.
 * 
 * @author Juan Jacobo Cañas 
 * @version 1.0
 * @since 2025-05-24
 */
public class MenuPrincipal extends JPanel {

    private Image fondo;

    /**
     * Constructor que inicializa el menú principal, carga el fondo, el récord actual y los botones.
     */
    public MenuPrincipal() {
        setLayout(null);
        setPreferredSize(new Dimension(900, 700));

        // Cargar imagen de fondo
        try {
            fondo = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/menu_fondo.png")).getImage();
        } catch (Exception e) {
            fondo = null;
        }

        int panelAncho = 900;
        int botonAncho = 200;
        int xCentro = (panelAncho - botonAncho) / 2;

        // Mostrar el récord actual del jugador
        int record = ArchivoPuntaje.obtenerPuntajeMaximo();
        JLabel recordLabel = new JLabel("Récord actual: " + record + " puntos", SwingConstants.CENTER);
        recordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        recordLabel.setForeground(Color.WHITE);
        recordLabel.setBounds(xCentro, 310, botonAncho, 30);
        add(recordLabel);

        // Botón para iniciar el juego
        JButton jugar = new JButton("Jugar");
        jugar.setBounds(xCentro, 360, botonAncho, 40);
        add(jugar);

        // Botón para salir del juego
        JButton salir = new JButton("Salir");
        salir.setBounds(xCentro, 420, botonAncho, 40);
        add(salir);

        // Acción del botón Jugar: cambia a la pantalla de selección de dificultad
        jugar.addActionListener(e -> {
            JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
            ventana.setContentPane(new PantallaDificultad(ventana));
            ventana.revalidate();
        });

        // Acción del botón Salir: cierra el programa
        salir.addActionListener(e -> System.exit(0));
    }

    /**
     * Dibuja el fondo del menú si está disponible.
     *
     * @param g el contexto gráfico utilizado para pintar
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (fondo != null) {
            g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        }
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

