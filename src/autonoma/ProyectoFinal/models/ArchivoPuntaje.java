/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.io.*;

public class ArchivoPuntaje {

    private static final String ARCHIVO = "puntaje_maximo.txt";

    // Guarda el puntaje si es mayor al actual
    public static void guardarPuntaje(int puntaje) {
        int max = obtenerPuntajeMaximo();
        if (puntaje > max) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO))) {
                writer.println(puntaje);
            } catch (IOException e) {
                System.err.println("Error al guardar el puntaje: " + e.getMessage());
            }
        }
    }

    // Devuelve el puntaje máximo guardado
    public static int obtenerPuntajeMaximo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea = reader.readLine();
            return Integer.parseInt(linea);
        } catch (Exception e) {
            return 0; // Si no existe o hay error, asumimos 0
        }
    }
}
