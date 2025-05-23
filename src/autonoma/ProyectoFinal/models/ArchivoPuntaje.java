/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.io.*;

/**
 * Clase que maneja la persistencia del puntaje más alto usando un archivo de texto.
 */
public class ArchivoPuntaje {
    private static final String ARCHIVO_PUNTAJE = "puntaje_maximo.txt";

    /**
     * Guarda el puntaje si es mayor que el previamente guardado.
     * @param nuevoPuntaje el puntaje actual del jugador
     */
    public static void guardarPuntaje(int nuevoPuntaje) {
        int puntajeGuardado = leerPuntaje();
        if (nuevoPuntaje > puntajeGuardado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PUNTAJE))) {
                writer.write(String.valueOf(nuevoPuntaje));
            } catch (IOException e) {
                System.err.println("Error al guardar el puntaje: " + e.getMessage());
            }
        }
    }

    /**
     * Lee el puntaje más alto guardado desde el archivo.
     * @return el puntaje leído o 0 si no existe o hay error
     */
    public static int leerPuntaje() {
        File archivo = new File(ARCHIVO_PUNTAJE);
        if (!archivo.exists()) {
            return 0;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea = reader.readLine();
            return Integer.parseInt(linea);
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el puntaje: " + e.getMessage());
            return 0;
        }
    }
}