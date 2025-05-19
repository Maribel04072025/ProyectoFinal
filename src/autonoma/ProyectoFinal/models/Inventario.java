/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

/**
 *
 * @author marib
 */
import java.util.ArrayList;

public class Inventario {
    private ArrayList<Objeto> objetos = new ArrayList<>();

    public void agregarObjeto(Objeto o) {
        objetos.add(o);
    }

    public void usarObjeto(int index) {
        if (index >= 0 && index < objetos.size()) {
            objetos.get(index).usar();
            objetos.remove(index);
        }
    }

    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }
}