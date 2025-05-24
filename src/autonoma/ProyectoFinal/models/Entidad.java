/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase abstracta base para todas las entidades del juego.
 * 
 * Define propiedades comunes como posición y dimensiones, y establece
 * los métodos que deben implementar las entidades específicas, tales como
 * la lógica de actualización, el renderizado y el área de colisión.
 * 
 * Esta clase sirve como punto de partida para representar cualquier
 * objeto interactivo en el juego, como plantas, enemigos, obstáculos, etc.
 * 
 * @author TuNombre
 * @version 20250524
 * @since 1.0
 */
public abstract class Entidad {

    /**
     * Posición X de la entidad en la pantalla.
     */
    protected int x;

    /**
     * Posición Y de la entidad en la pantalla.
     */
    protected int y;

    /**
     * Ancho de la entidad.
     */
    protected int ancho;

    /**
     * Alto de la entidad.
     */
    protected int alto;

    /**
     * Constructor de la entidad.
     * 
     * @param x Coordenada X inicial.
     * @param y Coordenada Y inicial.
     * @param ancho Ancho de la entidad.
     * @param alto Alto de la entidad.
     */
    public Entidad(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    /**
     * @return Coordenada X de la entidad.
     */
    public int getX() {
        return x;
    }

    /**
     * @return Coordenada Y de la entidad.
     */
    public int getY() {
        return y;
    }

    /**
     * @return Ancho de la entidad.
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * @return Alto de la entidad.
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Establece la coordenada X de la entidad.
     * 
     * @param x Nueva posición X.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Establece la coordenada Y de la entidad.
     * 
     * @param y Nueva posición Y.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Establece el ancho de la entidad.
     * 
     * @param ancho Nuevo ancho.
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * Establece el alto de la entidad.
     * 
     * @param alto Nuevo alto.
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * Actualiza la lógica interna de la entidad. 
     * 
     * Debe ser implementado por las subclases para definir su comportamiento.
     */
    public abstract void actualizar();

    /**
     * Dibuja la entidad en el contexto gráfico proporcionado.
     * 
     * @param g Objeto {@code Graphics} utilizado para renderizar.
     */
    public abstract void dibujar(Graphics g);

    /**
     * Devuelve el área de colisión de la entidad como un objeto {@code Rectangle}.
     * 
     * @return Área rectangular utilizada para detección de colisiones.
     */
    public abstract Rectangle getBounds();
}