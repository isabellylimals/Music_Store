package src.utils.sons.vendafinalizada;

import java.io.File;
import javax.sound.sampled.*;

public class SomUtil {

    private static Clip clip;

    public static void carregarSom(String caminhoArquivo) {
        try {
            File file = new File(caminhoArquivo);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            System.out.println("Erro ao carregar som: " + e.getMessage());
        }
    }

    public static void tocarSom() {
        if (clip != null) {
            clip.setFramePosition(0); 
            clip.start();
        }
    }
}