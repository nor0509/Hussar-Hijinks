package main;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow extends JFrame {
    public GameWindow(GamePanel gamePanel) {

        new JFrame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent windowEvent) {

            }

            @Override
            public void windowLostFocus(WindowEvent windowEvent) {
                gamePanel.getGame().windowFocusLost();
            }
        });
    }
}
