/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.ProyectoFinal.models;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class ReproductorMusica {
    private Clip clip;

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

    public void detenerMusica() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void continuarMusica() {
        if (clip != null) {
            clip.start();
        }
    }
}
