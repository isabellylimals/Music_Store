package src.utils.sons.Ambiente;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class SomAmbiente {

    private static Clip clip;

    public static void tocarSomAmbiente(String caminho) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(caminho));
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

        
            volumeControl.setValue(-30.0f); 

            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | IllegalArgumentException e) {
            System.out.println("Erro ao tocar som ambiente: " + e.getMessage());
        }
    }

    public static void pararSomAmbiente() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
