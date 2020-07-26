package com.github.ada0l.JPomodoro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPomodoro {
    private JButton button5;
    private JButton button25;
    private JTextField timeTextField;
    private JPanel panel;
    private JButton pauseButton;
    private CountDown countDown;

    public static void main(String[] args) {
        new JPomodoro().go();
    }

    void go() {
        JFrame frame = new JFrame();
        frame.setTitle("JPomodoro");
        frame.setIconImage(new ResourceLoader().loadImage("tomato.png"));
        frame.setContentPane(new JPomodoro().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    JPomodoro() {
        button25.addActionListener(new TimeButtonAction());
        button5.addActionListener(new TimeButtonAction());
        pauseButton.addActionListener(new PauseButtonAction());
    }

    class TimeButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (countDown != null) {
                countDown.cancel();
            }
            int interval = 25 * 60;
            if (e.getSource() == button5) {
                interval = 5 * 60;
            }
            countDown = new CountDown(interval, new Updater());
            countDown.start();
        }
    }

    class PauseButtonAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (countDown != null && ! countDown.isNull()) {
                if (pauseButton.getText().equals("Pause")) {
                    pauseButton.setText("Continue");
                    countDown.cancel();
                }
                else if (pauseButton.getText().equals("Continue")) {
                    pauseButton.setText("Pause");
                    countDown.start();
                }
            }
        }
    }

    class Updater implements CountDownAction {

        @Override
        public void onStart() {
            pauseButton.setText("Pause");
        }

        @Override
        public void onTick() {
            int interval = countDown.getInterval();
            int minutes = interval / 60;
            int seconds = interval - minutes * 60;
            timeTextField.setText(String.format("%02d:%02d", minutes, seconds));
        }

        @Override
        public void onDone() {
        }
    }
}
