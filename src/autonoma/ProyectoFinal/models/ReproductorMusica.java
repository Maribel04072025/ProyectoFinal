/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Clase encargada de reproducir archivos de música en formato compatible con Java Sound API.
 * Permite reproducir, detener y continuar música con opción de reproducción en bucle.
 * 
 * El archivo de audio debe estar dentro del classpath y se accede mediante una ruta interna.
 * 
 * Uso típico:
 * <pre>
 *     ReproductorMusica rm = new ReproductorMusica();
 *     rm.reproducirMusica("/ruta/al/archivo.wav", true);
 * </pre>
 * 
 * Nota: Solo soporta formatos compatibles con AudioSystem, como WAV o AIFF.
 * 
 * @author marib
 * @version 1.0
 * @since 2025-05-24
 */
public class ReproductorMusica {
    private Clip clip;

    /**
     * Reproduce un archivo de música desde una ruta interna (recurso).
     * 
     * @param rutaInterna Ruta interna del recurso de audio dentro del classpath (e.g., "/sonidos/musica.wav").
     * @param loop Indica si la música debe reproducirse en bucle continuo.
     */
    public void reproducirMusica(String rutaInterna, boolean loop) {
        try {
            URL ruta = getClass().getResource(rutaInterna);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(ruta);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("⚠ Error al reproducir la música: " + e.getMessage());
        }
    }

    /**
     * Detiene la reproducción actual de la música si está en curso.
     */
    public void detenerMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    /**
     * Continúa la reproducción de la música pausada o detenida.
     */
    public void continuarMusica() {
        if (clip != null) {
            clip.start();
        }
    }
}