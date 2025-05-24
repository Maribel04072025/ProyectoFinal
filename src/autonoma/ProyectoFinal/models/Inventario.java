/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package autonoma.ProyectoFinal.models;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa el inventario del jugador que almacena objetos recolectados.
 * 
 * El inventario contiene objetos clave como "Poción Curativa", "Semilla Rara"
 * y "Esencia Mágica", cada uno con un efecto especial sobre el jugador o el entorno.
 * 
 * Este componente permite agregar, usar y visualizar gráficamente los objetos del jugador.
 * 
 * @author Juan Esteban Hernandez
 * @version 20250524
 * @since 1.0
 * @see ObjetoInventario
 * @see Jugador
 */
public class Inventario {

    /**
     * Mapa que relaciona el nombre de un objeto con su cantidad actual en el inventario.
     */
    private final Map<String, Integer> objetos;

    /**
     * Constructor del inventario. Inicializa los objetos disponibles en el juego con cantidad cero.
     */
    public Inventario() {
        objetos = new HashMap<>();
        objetos.put("Poción Curativa", 0);
        objetos.put("Semilla Rara", 0);
        objetos.put("Esencia Mágica", 0);
    }

    /**
     * Agrega un objeto al inventario. Si ya existe, incrementa su cantidad.
     *
     * @param obj Objeto de inventario que se desea agregar.
     */
    public void agregarObjeto(ObjetoInventario obj) {
        String nombre = obj.getNombre();
        objetos.put(nombre, objetos.getOrDefault(nombre, 0) + 1);
    }

    /**
     * Usa un objeto del inventario si hay al menos uno disponible.
     * Aplica efectos sobre el jugador dependiendo del objeto utilizado.
     *
     * @param nombre Nombre del objeto a usar.
     * @param jugador Instancia del jugador sobre el que se aplica el efecto.
     */
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

    /**
     * Dibuja el estado del inventario en pantalla, mostrando la cantidad de objetos.
     *
     * @param g Contexto gráfico sobre el que se dibuja la interfaz del inventario.
     */
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