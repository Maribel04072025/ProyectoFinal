/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import autonoma.ProyectoFinal.interfaces.Dañable;
import java.awt.Color;
import java.awt.Graphics;

public class Jugador extends Entidad implements Dañable {
    private int vida;
    private Inventario inventario;
    private int puntaje;

    public Jugador(int x, int y) {
        super(x, y, 40, 40);
        this.vida = 100;
        this.inventario = new Inventario();
        this.puntaje = 0;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, ancho, alto);
    }

    @Override
    public void actualizar() {
        // Movimiento manejado desde el teclado en Juego
    }

    public void mover(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void atacar(PlantaCorrupta planta) {
        planta.recibirDaño(25); // daño fijo por ataque
        puntaje += 10;
    }

    public void recolectarObjeto(Objeto o) {
        inventario.agregarObjeto(o);
    }

    @Override
    public void recibirDaño(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public void curar(int cantidad) {
        vida += cantidad;
        if (vida > 100) vida = 100;
    }

    public int getVida() {
        return vida;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public Inventario getInventario() {
        return inventario;
    }
}
