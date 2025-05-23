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

/**
 * Ventana inicial del juego, muestra el menú principal con botón para iniciar el juego
 * y el puntaje máximo registrado.
 */
public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("El Reino de las Plantas Mágicas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("El Reino de las Plantas Mágicas ");
        titulo.setFont(new Font("Serif", Font.BOLD, 32));
        titulo.setForeground(Color.GREEN);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(80));
        panel.add(titulo);

        JLabel record = new JLabel("Récord actual: " + ArchivoPuntaje.leerPuntaje() + " puntos");
        record.setFont(new Font("Arial", Font.PLAIN, 18));
        record.setForeground(Color.WHITE);
        record.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(30));
        panel.add(record);

        JButton botonJugar = new JButton("Iniciar Juego");
        botonJugar.setFont(new Font("Arial", Font.BOLD, 20));
        botonJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });
        panel.add(Box.createVerticalStrut(50));
        panel.add(botonJugar);

        add(panel);
    }

    /**
     * Reemplaza el contenido de la ventana con el panel del juego.
     */
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

