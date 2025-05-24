/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase abstracta que representa un objeto que puede ser almacenado en el inventario del jugador.
 * 
 * Cada objeto de inventario tiene una posición y tamaño para su representación en pantalla,
 * un nombre identificador y un efecto que se aplica sobre el jugador cuando se utiliza.
 * 
 * Las subclases deben implementar el método {@code aplicarEfecto} para definir la funcionalidad específica.
 * También pueden sobrescribir el método {@code dibujar} para mostrar una imagen personalizada.
 * 
 * @author Juan Esteban Hernandez
 * @version 1.0
 * @since 2025-05-24
 */
public abstract class ObjetoInventario {

    protected int x, y, ancho, alto;
    protected String nombre;

    /**
     * Constructor para crear un objeto de inventario con posición, tamaño y nombre.
     *
     * @param x      Posición horizontal del objeto en pantalla.
     * @param y      Posición vertical del objeto en pantalla.
     * @param ancho  Ancho del objeto.
     * @param alto   Alto del objeto.
     * @param nombre Nombre identificador del objeto.
     */
    public ObjetoInventario(int x, int y, int ancho, int alto, String nombre) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.nombre = nombre;
    }

    /**
     * Obtiene el nombre del objeto.
     * 
     * @return El nombre identificador del objeto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Aplica el efecto específico de este objeto sobre el jugador.
     * 
     * @param jugador El jugador sobre el cual se aplicará el efecto.
     */
    public abstract void aplicarEfecto(Jugador jugador);

    /**
     * Dibuja una representación básica del objeto en pantalla.
     * Puede ser sobrescrito para mostrar una imagen o gráfico personalizado.
     * 
     * @param g El contexto gráfico donde se dibujará el objeto.
     */
    public void dibujar(Graphics g) {
        g.fillRect(x, y, ancho, alto);
    }

    /**
     * Obtiene el rectángulo que representa los límites del objeto para colisiones.
     * 
     * @return Un objeto {@link Rectangle} con la posición y tamaño del objeto.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    // Getters para posición y tamaño
    public int getX() { return x; }
    public int getY() { return y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
}