/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Nivel {

    private Jugador jugador;
    private List<PlantaCorrupta> enemigos;
    private List<Rociador> rociadores;
    private List<PlantaAmistosa> plantasAmistosas;
    private int ancho, alto;
    private int puntaje;

    public Nivel(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.enemigos = new ArrayList<>();
        this.rociadores = new ArrayList<>();
        this.plantasAmistosas = new ArrayList<>();
        this.puntaje = 0;
        jugador = new Jugador(ancho / 10, alto - 80, 50, 50);
    }

    public void dibujar(Graphics g) {
        jugador.dibujar(g);
        for (Rociador r : rociadores) r.dibujar(g);
        for (PlantaCorrupta e : enemigos) e.dibujar(g);
        for (PlantaAmistosa pa : plantasAmistosas) pa.dibujar(g);
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
    }

    private void verificarColisiones() {
        Iterator<PlantaCorrupta> itE = enemigos.iterator();
        while (itE.hasNext()) {
            PlantaCorrupta enemigo = itE.next();
            Rectangle boundsEnemigo = enemigo.getBounds();

            for (Rociador r : rociadores) {
                if (r.getBounds().intersects(boundsEnemigo)) {
                    r.desactivar();
                    enemigo.detener();
                    if (enemigo instanceof CactusExplosivo) {
                        ((CactusExplosivo) enemigo).explotar();
                    }
                    itE.remove();
                    puntaje += 10;
                    break;
                }
            }
        }
    }

    private void verificarRecolectables() {
        Iterator<PlantaAmistosa> itPA = plantasAmistosas.iterator();
        while (itPA.hasNext()) {
            PlantaAmistosa pa = itPA.next();
            if (pa.estaActiva() && pa.getBounds().intersects(jugador.getBounds())) {
                jugador.curar(20);  // 💖 Curar
                jugador.aumentarPuntaje(10);
                pa.recolectar();
                itPA.remove();
            }
        }
    }

    private void verificarDañoEnemigos() {
        for (PlantaCorrupta enemigo : enemigos) {
            if (enemigo.getBounds().intersects(jugador.getBounds())) {
                jugador.recibirDanio(5);  // 💥 Recibe daño
                break;
            }
        }
    }

    public void generarEnemigo() {
        int tipo = (int) (Math.random() * 3);
        PlantaCorrupta enemigo;
        int x = (int) (Math.random() * (ancho - 40));
        int y = (int) (Math.random() * (alto / 2));

        switch (tipo) {
            case 0 -> enemigo = new HiedraVenenosa(x, y, 40, 40);
            case 1 -> enemigo = new CactusExplosivo(x, y, 40, 40);
            default -> enemigo = new FlorCarnivora(x, y, 40, 40);
        }

        enemigos.add(enemigo);
        new Thread(enemigo).start();
    }

    public void generarPlantaAmistosa() {
        PlantaAmistosa planta = new PlantaAmistosa((int)(Math.random() * (ancho - 30)), (int)(Math.random() * (alto - 200)), 30, 30);
        plantasAmistosas.add(planta);
    }

    public void disparar() {
        Rociador nuevo = new Rociador(jugador.getX() + jugador.getAncho(), jugador.getY() + jugador.getAlto() / 2 - 5, 20, 10);
        rociadores.add(nuevo);
    }

    public Jugador getJugador() { return jugador; }
    public int getPuntaje() { return puntaje; }
    public List<PlantaCorrupta> getEnemigos() { return enemigos; }
    public List<Rociador> getRociadores() { return rociadores; }
}