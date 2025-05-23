/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autonoma.ProyectoFinal.views;

import autonoma.ProyectoFinal.models.ArchivoPuntaje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    private Image fondo;

    public MenuPrincipal() {
        setTitle("El Reino de las Plantas Mágicas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Cargar imagen de fondo
        fondo = new ImageIcon(getClass().getResource("/autonoma/ProyectoFinal/resources/fondo_menu.png")).getImage();

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };

        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(800, 600));

        // Record (más abajo, sin título)
        JLabel record = new JLabel(" Récord actual: " + ArchivoPuntaje.leerPuntaje() + " puntos");
        record.setFont(new Font("Arial", Font.BOLD, 20));
        record.setForeground(Color.WHITE);
        record.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botonJugar = new JButton("Iniciar Juego");
        botonJugar.setFont(new Font("Arial", Font.BOLD, 24));
        botonJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });

        JButton botonSalir = new JButton("Salir");
        botonSalir.setFont(new Font("Arial", Font.BOLD, 20));
        botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSalir.addActionListener(e -> System.exit(0));

        panel.add(Box.createVerticalStrut(280)); // espacio arriba
        panel.add(record);
        panel.add(Box.createVerticalStrut(30));
        panel.add(botonJugar);
        panel.add(Box.createVerticalStrut(20));
        panel.add(botonSalir);

        setContentPane(panel);
    }

    private void iniciarJuego() {
        getContentPane().removeAll();
        Juego juego = new Juego();
        add(juego);
        revalidate();
        repaint();
        juego.requestFocusInWindow();
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

