/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autonoma.ProyectoFinal.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author juane
 */
public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("El Reino de las Plantas Mágicas");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel del menú
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(34, 139, 34)); // verde bosque

        JLabel titulo = new JLabel("El Reino de las Plantas Mágicas");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Serif", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        panel.add(Box.createVerticalStrut(30));
        panel.add(titulo);

        JButton btnJugar = new JButton("Iniciar Juego");
        JButton btnPuntajes = new JButton("Ver Puntajes");
        JButton btnSalir = new JButton("Salir");

        btnJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPuntajes.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(40));
        panel.add(btnJugar);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnPuntajes);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnSalir);

        add(panel);

        // Acción del botón Jugar
        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // cierra el menú
                JFrame ventanaJuego = new JFrame("Juego");
                Juego juego = new Juego();
                ventanaJuego.add(juego);
                ventanaJuego.pack();
                ventanaJuego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventanaJuego.setLocationRelativeTo(null);
                ventanaJuego.setVisible(true);
                juego.iniciar();
            }
        });

        // Acción del botón Ver Puntajes
        btnPuntajes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int p = persistencia.ArchivoPuntaje.leerPuntaje();
                JOptionPane.showMessageDialog(null, "Puntaje máximo: " + p);
            }
        });

        // Acción del botón Salir
        btnSalir.addActionListener(e -> System.exit(0));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuPrincipal menu = new MenuPrincipal();
            menu.setVisible(true);
        });
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

