package com.github.ada0l.JPomodoro;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.Objects;

public class ResourceLoader {

    public static final String PATH_IMAGE = "images/";
    public static final String PATH_SOUND = "sounds/";
    private final ClassLoader cl = this.getClass().getClassLoader();

    public Image loadImage(String filename) {
        return new ImageIcon(Objects.requireNonNull(cl.getResource(PATH_IMAGE + filename))).getImage();
    }

    public Clip loadSound(String filename) {
        Clip clip = null;
        try {
             clip = AudioSystem.getClip();
             InputStream inputStream = cl.getResourceAsStream(
                     PATH_SOUND + filename
             );
            assert inputStream != null;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
             System.out.println(clip.isOpen());
             clip.open(audioInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }
}
