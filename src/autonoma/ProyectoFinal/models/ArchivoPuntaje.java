/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

/**
 *
 * @author marib
 */

import java.io.*;

public class ArchivoPuntaje {
    public static void guardarPuntaje(int puntaje) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("puntaje.txt"))) {
            writer.write(String.valueOf(puntaje));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int leerPuntaje() {
        try (BufferedReader reader = new BufferedReader(new FileReader("puntaje.txt"))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0;
        }
    }
}