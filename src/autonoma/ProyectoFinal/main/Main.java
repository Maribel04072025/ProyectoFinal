/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.main;

import autonoma.ProyectoFinal.views.Juego;

import javax.swing.JFrame;

/**
 * Clase principal del juego.
 * Lanza la ventana principal y agrega el panel del juego.
 */
public class Main {
    public static void main(String[] args) {
        // Crear ventana
        JFrame ventana = new JFrame("El Reino de las Plantas Mágicas");

        // Crear panel de juego
        Juego panelJuego = new Juego();
        ventana.add(panelJuego);

        // Configurar ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.pack(); // Ajusta al tamaño del panel
        ventana.setLocationRelativeTo(null); // Centrar en pantalla
        ventana.setResizable(false);
        ventana.setVisible(true);
    }
}