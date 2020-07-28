package com.github.ada0l.JPomodoro;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Objects;

public class ResourceLoader {

    public static final String PATH_IMAGE = "images/";
    public static final String PATH_SOUND = "sounds/";

    public Image loadImage(String filename) {
        return new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(
                    PATH_IMAGE + filename))).getImage();
    }

    public Clip loadSound(String filename) {
        Clip clip = null;
        try {
             clip = AudioSystem.getClip();
             InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PATH_SOUND + filename);

             // buffered stream for mark/reset support
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
             AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
             clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }
}
