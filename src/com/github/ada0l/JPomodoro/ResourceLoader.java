package com.github.ada0l.JPomodoro;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ResourceLoader {

    public static final String PATH_IMAGE = "images/";

    public Image loadImage(String filename) {

        ClassLoader cl = this.getClass().getClassLoader();

        return new ImageIcon(Objects.requireNonNull(cl.getResource(PATH_IMAGE + filename))).getImage();
    }
}
