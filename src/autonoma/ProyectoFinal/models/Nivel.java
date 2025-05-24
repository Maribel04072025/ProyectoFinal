/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representa un nivel del juego. Gestiona al jugador, enemigos, objetos y otros elementos del entorno.
 * Controla la lógica de actualización, dibujo, colisiones y generación de entidades.
 * 
 * Se encarga de:
 * - Dibujar todos los elementos en pantalla.
 * - Detectar colisiones entre entidades.
 * - Recolectar objetos o plantas.
 * - Gestionar el puntaje y daño recibido.
 * 
 * @author TuNombre
 * @version 20250524
 * @since 1.0
 */
public class Nivel {

    private Jugador jugador;
    private List<PlantaCorrupta> enemigos;
    private List<Rociador> rociadores;
    private List<PlantaAmistosa> plantasAmistosas;
    private List<ObjetoInventario> objetos;

    private int ancho, alto;
    private int puntaje;
    private int dificultad;

    /**
     * Crea un nuevo nivel con sus elementos iniciales.
     *
     * @param ancho      Ancho del mapa.
     * @param alto       Alto del mapa.
     * @param dificultad Nivel de dificultad (1: fácil, 2: medio, 3: difícil).
     */
    public Nivel(int ancho, int alto, int dificultad) {
        this.ancho = ancho;
        this.alto = alto;
        this.dificultad = dificultad;

        enemigos = new ArrayList<>();
        rociadores = new ArrayList<>();
        plantasAmistosas = new ArrayList<>();
        objetos = new ArrayList<>();

        // Jugador centrado
        jugador = new Jugador(ancho / 2 - 25, alto / 2 - 25, 50, 50, dificultad);
        jugador.setNivel(this);
    }

    /**
     * Dibuja todos los elementos del nivel: jugador, enemigos, plantas, objetos y proyectiles.
     *
     * @param g Contexto gráfico proporcionado por el motor de renderizado.
     */
    public void dibujar(Graphics g) {
        jugador.dibujar(g);

        for (Rociador r : rociadores) r.dibujar(g);
        for (PlantaCorrupta enemigo : enemigos) enemigo.dibujar(g);
        for (PlantaAmistosa planta : plantasAmistosas) planta.dibujar(g);
        for (ObjetoInventario obj : objetos) obj.dibujar(g);
    }

    /**
     * Actualiza el estado de todos los elementos del nivel (proyectiles, colisiones, objetos, etc.).
     */
    public void actualizar() {
        jugador.actualizar();

        // Actualizar y eliminar proyectiles inactivos
        Iterator<Rociador> itR = rociadores.iterator();
        while (itR.hasNext()) {
            Rociador r = itR.next();
            r.actualizar();
            if (!r.estaActivo()) itR.remove();
        }

        verificarColisiones();
        verificarRecolectables();
        verificarDañoEnemigos();
        verificarObjetos();
    }

    /**
     * Verifica colisiones entre proyectiles del jugador y enemigos. Elimina enemigos y suma puntaje.
     */
    private void verificarColisiones() {
        Iterator<PlantaCorrupta> itE = enemigos.iterator();
        while (itE.hasNext()) {
            PlantaCorrupta enemigo = itE.next();
            for (Rociador r : rociadores) {
                if (r.getBounds().intersects(enemigo.getBounds())) {
                    r.desactivar();
                    enemigo.detener();
                    itE.remove();
                    puntaje += 10;
                    break;
                }
            }
        }
    }

    /**
     * Verifica si el jugador recolecta una planta amistosa. Aumenta vida y puntaje.
     */
    private void verificarRecolectables() {
        Iterator<PlantaAmistosa> it = plantasAmistosas.iterator();
        while (it.hasNext()) {
            PlantaAmistosa planta = it.next();
            if (planta.estaActiva() && planta.getBounds().intersects(jugador.getBounds())) {
                jugador.curar(20);
                jugador.aumentarPuntaje(10);
                planta.recolectar();
                it.remove();
            }
        }
    }

    /**
     * Verifica si algún enemigo toca al jugador. Se aplica daño y penalización de puntaje.
     */
    private void verificarDañoEnemigos() {
        Iterator<PlantaCorrupta> it = enemigos.iterator();
        while (it.hasNext()) {
            PlantaCorrupta enemigo = it.next();
            if (enemigo.getBounds().intersects(jugador.getBounds())) {
                jugador.recibirDanio(enemigo.getDanio());
                jugador.aumentarPuntaje(-enemigo.getPenalizacionPuntaje());
                enemigo.detener();
                it.remove();
                break;
            }
        }
    }

    /**
     * Verifica si el jugador recoge un objeto y lo añade al inventario.
     */
    private void verificarObjetos() {
        Iterator<ObjetoInventario> it = objetos.iterator();
        while (it.hasNext()) {
            ObjetoInventario obj = it.next();
            if (jugador.getBounds().intersects(obj.getBounds())) {
                if (obj instanceof SemillaRara) {
                    jugador.getInventario().agregarObjeto(new SemillaRara(0, 0));
                } else if (obj instanceof PocionCurativa) {
                    jugador.getInventario().agregarObjeto(new PocionCurativa(0, 0));
                } else if (obj instanceof EsenciaMagica) {
                    jugador.getInventario().agregarObjeto(new EsenciaMagica(0, 0));
                }
                it.remove();
            }
        }
    }

    /**
     * Genera un nuevo enemigo aleatorio (de tres tipos posibles) y lo añade al nivel.
     */
    public void generarEnemigo() {
        int tipo = (int)(Math.random() * 3);
        PlantaCorrupta enemigo;
        int x = (int)(Math.random() * (ancho - 40));
        int y = (int)(Math.random() * (alto / 2));

        switch (tipo) {
            case 0 -> enemigo = new HiedraVenenosa(x, y, 40, 40, dificultad);
            case 1 -> enemigo = new CactusExplosivo(x, y, 40, 40, dificultad);
            default -> enemigo = new FlorCarnivora(x, y, 40, 40, dificultad);
        }

        enemigos.add(enemigo);
        new Thread(enemigo).start();
    }

    /**
     * Genera una planta amistosa en una posición aleatoria del nivel.
     */
    public void generarPlantaAmistosa() {
        PlantaAmistosa planta = new PlantaAmistosa(
            (int)(Math.random() * (ancho - 30)),
            (int)(Math.random() * (alto - 150)),
            30, 30
        );
        plantasAmistosas.add(planta);
    }

    /**
     * Genera un objeto del inventario en una posición aleatoria.
     */
    public void generarObjeto() {
        int tipo = (int)(Math.random() * 3);
        ObjetoInventario objeto;
        int x = (int)(Math.random() * (ancho - 25));
        int y = (int)(Math.random() * (alto - 150));

        switch (tipo) {
            case 0 -> objeto = new PocionCurativa(x, y);
            case 1 -> objeto = new SemillaRara(x, y);
            default -> objeto = new EsenciaMagica(x, y);
        }

        objetos.add(objeto);
    }

    /**
     * Dispara un proyectil desde la posición del jugador en la dirección actual.
     */
    public void disparar() {
        String direccion = jugador.getDireccion();
        Rociador r = new Rociador(
            jugador.getX() + jugador.getAncho() / 2,
            jugador.getY() + jugador.getAlto() / 2,
            16, 16,
            direccion
        );
        rociadores.add(r);
    }

    /**
     * Elimina todos los enemigos (plantas corruptas) presentes en el nivel.
     */
    public void eliminarTodasLasPlantasCorruptas() {
        for (PlantaCorrupta p : enemigos) {
            p.detener();
        }
        enemigos.clear();
    }

    // Getters

    /**
     * @return El jugador actual del nivel.
     */
    public Jugador getJugador() { return jugador; }

    /**
     * @return Puntaje acumulado en este nivel.
     */
    public int getPuntaje() { return puntaje; }

    /**
     * @return Dificultad del nivel (1 a 3).
     */
    public int getDificultad() { return dificultad; }
}