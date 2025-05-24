/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

/**
 * Clase abstracta que representa un objeto genérico en el juego.
 * 
 * Esta clase define el método abstracto {@code usar}, que debe ser implementado
 * por todas las subclases para definir el comportamiento específico al usar el objeto.
 * 
 * @author Juan Esteban Hernandez
 * @version 1.0
 * @since 2025-05-24
 */
public abstract class Objeto {

    /**
     * Define la acción que ocurre al usar el objeto.
     * Cada subclase debe implementar esta función según su funcionalidad.
     */
    public abstract void usar();
}