/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Inventario del jugador. Guarda objetos recogidos.
 */
public class Inventario {

    private List<ObjetoInventario> objetos;

    public Inventario() {
        objetos = new ArrayList<>();
    }

    public void agregarObjeto(ObjetoInventario objeto) {
        objetos.add(objeto);
    }

    public List<ObjetoInventario> getObjetos() {
        return objetos;
    }

    public void usarObjeto(String nombre, Jugador jugador) {
        for (int i = 0; i < objetos.size(); i++) {
            ObjetoInventario obj = objetos.get(i);
            if (obj.getNombre().equals(nombre)) {
                obj.aplicarEfecto(jugador);
                objetos.remove(i);
                break;
            }
        }
    }

    public boolean tieneObjeto(String nombre) {
        for (ObjetoInventario obj : objetos) {
            if (obj.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }
}