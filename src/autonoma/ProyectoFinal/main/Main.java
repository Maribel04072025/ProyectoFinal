/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.main;

import autonoma.ProyectoFinal.views.MenuPrincipal;

import javax.swing.*;

/**
 * Clase principal del proyecto "El Reino de las Plantas Mágicas".
 * 
 * Esta clase contiene el método main que inicia la aplicación creando
 * la ventana principal y mostrando la interfaz gráfica del menú principal.
 * 
 * @author TuNombre
 * @version 20250524
 * @since 1.0
 */
public class Main {

    /**
     * Método principal que lanza la aplicación.
     * 
     * Utiliza SwingUtilities para asegurar que la creación de la interfaz gráfica
     * se realice en el hilo de eventos de Swing. Se configura y muestra la ventana
     * principal de la aplicación con el menú principal.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("El Reino de las Plantas Mágicas");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setContentPane(new MenuPrincipal());
            ventana.pack();
            ventana.setResizable(false);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}