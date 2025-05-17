/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.main;

import autonoma.ProyectoFinal.views.Juego;
import javax.swing.JFrame;


/**
 *
 * @author marib
 */




public class Main {
    public static void main(String[] args) {
         JFrame ventana = new JFrame("El Reino de las Plantas Mágicas");
         Juego juego = new Juego();
         ventana.add(juego);
         ventana.pack();
         ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         ventana.setLocationRelativeTo(null);
         ventana.setVisible(true);
         juego.iniciar();
         
        }
    }
