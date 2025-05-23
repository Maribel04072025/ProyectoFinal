/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa un nivel del juego. Controla al jugador, enemigos,
 * objetos y la lógica de juego general.
 */
public class Nivel {

    private Jugador jugador;
    private List<PlantaCorrupta> enemigos;
    private List<Rociador> rociadores;
    private List<PlantaAmistosa> plantasAmistosas;
    private List<ObjetoInventario> objetos;
    private int ancho, alto;
    private int puntaje;

    public Nivel(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.enemigos = new ArrayList<>();
        this.rociadores = new ArrayList<>();
        this.plantasAmistosas = new ArrayList<>();
        this.objetos = new ArrayList<>();
        this.puntaje = 0;

        jugador = new Jugador(ancho / 10, alto - 80, 50, 50);
        jugador.setNivel(this); // Conectar jugador ↔ nivel
    }

    public void dibujar(Graphics g) {
        jugador.dibujar(g);
        for (Rociador r : rociadores) r.dibujar(g);
        for (PlantaCorrupta enemigo : enemigos) enemigo.dibujar(g);
        for (PlantaAmistosa planta : plantasAmistosas) planta.dibujar(g);
        for (ObjetoInventario obj : objetos) obj.dibujar(g);
    }

    public void actualizar() {
        jugador.actualizar();

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

    private void verificarObjetos() {
        Iterator<ObjetoInventario> it = objetos.iterator();
        while (it.hasNext()) {
            ObjetoInventario obj = it.next();
            if (!obj.estaRecogido() && obj.getBounds().intersects(jugador.getBounds())) {
                jugador.getInventario().agregarObjeto(obj);
                obj.recoger();
                it.remove();
            }
        }
    }

    public void generarEnemigo() {
        int tipo = (int)(Math.random() * 3);
        PlantaCorrupta enemigo;
        int x = (int)(Math.random() * (ancho - 40));
        int y = (int)(Math.random() * (alto / 2));

        switch (tipo) {
            case 0 -> enemigo = new HiedraVenenosa(x, y, 40, 40);
            case 1 -> enemigo = new CactusExplosivo(x, y, 40, 40);
            default -> enemigo = new FlorCarnivora(x, y, 40, 40);
        }

        enemigos.add(enemigo);
        new Thread(enemigo).start();
    }

    public void generarPlantaAmistosa() {
        PlantaAmistosa planta = new PlantaAmistosa(
            (int)(Math.random() * (ancho - 30)),
            (int)(Math.random() * (alto - 150)),
            30, 30
        );
        plantasAmistosas.add(planta);
    }

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

    public void disparar() {
        Rociador r = new Rociador(
            jugador.getX() + jugador.getAncho(),
            jugador.getY() + jugador.getAlto() / 2 - 5,
            20, 10
        );
        rociadores.add(r);
    }

    public void eliminarTodosLosEnemigos() {
        for (PlantaCorrupta p : enemigos) {
            p.detener();
        }
        enemigos.clear();
    }

    // Getters
    public Jugador getJugador() { return jugador; }
    public int getPuntaje() { return puntaje; }
}
