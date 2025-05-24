/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.io.*;

/**
 * Clase utilitaria para manejar el almacenamiento del puntaje máximo en un archivo.
 * 
 * Esta clase permite guardar un nuevo puntaje si supera el puntaje máximo anterior,
 * y obtener el puntaje máximo guardado desde el archivo.
 * 
 * El archivo utilizado para almacenar el puntaje es "puntaje_maximo.txt".
 * 
 * @author TuNombre
 * @version 20250524
 * @since 1.0
 */
public class ArchivoPuntaje {

    /**
     * Nombre del archivo donde se guarda el puntaje máximo.
     */
    private static final String ARCHIVO = "puntaje_maximo.txt";

    /**
     * Guarda el puntaje especificado si es mayor que el puntaje máximo actual.
     * 
     * @param puntaje Puntaje obtenido que se desea comparar y guardar si es mayor al registrado.
     */
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

    /**
     * Obtiene el puntaje máximo guardado en el archivo.
     * 
     * @return El puntaje máximo guardado. Si no se encuentra el archivo o hay un error de lectura,
     *         se devuelve 0 como valor predeterminado.
     */
    public static int obtenerPuntajeMaximo() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea = reader.readLine();
            return Integer.parseInt(linea);
        } catch (Exception e) {
            return 0; // Si no existe o hay error, asumimos 0
        }
    }
}
