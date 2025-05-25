/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package autonoma.ProyectoFinal.interfaces;

/**
 * Interfaz que representa objetos que pueden ser recolectados 
 * en el juego y que otorgan una cantidad determinada de puntos.
 * 
 * Implementar esta interfaz permite que un objeto sea evaluado
 * en cuanto a los puntos que proporciona al ser recolectado.
 * 
 * @author Maribel Ceballos 
 * @version 20250524
 * @since 1.0
 */
public interface Recolectable {

    /**
     * Obtiene la cantidad de puntos que el objeto recolectable otorga.
     * 
     * @return puntos que se obtienen al recolectar el objeto.
     */
    int getPuntos();
}