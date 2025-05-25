/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package autonoma.ProyectoFinal.interfaces;

/**
 * Interfaz que representa objetos que pueden recibir daño.
 * 
 * Implementar esta interfaz permite que un objeto sea capaz
 * de recibir una cantidad determinada de daño, afectando su estado.
 * 
 * @author Maribel Ceballos 
 * @since 1.0
 */
public interface Dañable {

    /**
     * Aplica daño al objeto con la cantidad especificada.
     * 
     * @param cantidad la cantidad de daño que se debe recibir.
     */
    void recibirDaño(int cantidad);
}