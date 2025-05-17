/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.logica;

/**
 *
 * @author marib
 */


import autonoma.ProyectoFinal.models.PlantaCorrupta;
import java.util.ArrayList;

public class Nivel {
    private ArrayList<PlantaCorrupta> enemigos = new ArrayList<>();

    public void generarEnemigosAleatorios() {
        enemigos.add(new PlantaCorrupta((int)(Math.random()*500), (int)(Math.random()*500)));
    }

    public boolean esNivelCompleto() {
        return enemigos.isEmpty();
    }
}