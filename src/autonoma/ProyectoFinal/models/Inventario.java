/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package autonoma.ProyectoFinal.models;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Inventario {

    private final Map<String, Integer> objetos;

    public Inventario() {
        objetos = new HashMap<>();
        objetos.put("Poción Curativa", 0);
        objetos.put("Semilla Rara", 0);
        objetos.put("Esencia Mágica", 0);
    }

    public void agregarObjeto(ObjetoInventario obj) {
        String nombre = obj.getNombre();
        objetos.put(nombre, objetos.getOrDefault(nombre, 0) + 1);
    }

    public void usarObjeto(String nombre, Jugador jugador) {
        int cantidad = objetos.getOrDefault(nombre, 0);
        if (cantidad <= 0) return;

        switch (nombre) {
            case "Poción Curativa" -> jugador.curar(30);
            case "Semilla Rara" -> jugador.getNivel().eliminarTodasLasPlantasCorruptas();
            case "Esencia Mágica" -> jugador.setVelocidad(10);
        }

        objetos.put(nombre, cantidad - 1);
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 14));

        int x = 700; // Esquina superior derecha
        int y = 20;

        g.drawString("1: Pociones x" + objetos.get("Poción Curativa"), x, y);
        g.drawString("2: Semilla x" + objetos.get("Semilla Rara"), x, y + 18);
        g.drawString("3: Esencia x" + objetos.get("Esencia Mágica"), x, y + 36);
    }
}
