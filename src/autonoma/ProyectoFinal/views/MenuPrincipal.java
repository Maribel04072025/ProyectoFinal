/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autonoma.ProyectoFinal.views;

import autonoma.ProyectoFinal.models.ArchivoPuntaje;

import javax.swing.*;
import java.awt.*;

/**
 * Menú principal del juego con fondo, récord y botones bien ubicados.
 */
public class MenuPrincipal extends JPanel {

    private Image fondo;

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

        // Récord actual
        int record = ArchivoPuntaje.obtenerPuntajeMaximo();
        JLabel recordLabel = new JLabel("Récord actual: " + record + " puntos", SwingConstants.CENTER);
        recordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        recordLabel.setForeground(Color.WHITE);
        recordLabel.setBounds(xCentro, 310, botonAncho, 30);
        add(recordLabel);

        // Botón Jugar
        JButton jugar = new JButton("Jugar");
        jugar.setBounds(xCentro, 360, botonAncho, 40);
        add(jugar);

        // Botón Salir
        JButton salir = new JButton("Salir");
        salir.setBounds(xCentro, 420, botonAncho, 40);
        add(salir);

        // Acción botón Jugar
        jugar.addActionListener(e -> {
            JFrame ventana = (JFrame) SwingUtilities.getWindowAncestor(this);
            ventana.setContentPane(new PantallaDificultad(ventana));
            ventana.revalidate();
        });

        // Acción botón Salir
        salir.addActionListener(e -> System.exit(0));
    }

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

