package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utils.Directions;
import utils.PlayerAction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utils.Directions.*;
import static utils.PlayerAction.*;


public class GamePanel extends JPanel {

    private final Game game;
    private MouseInputs mouseInputs;

    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new MouseInputs(this);


        setPanelSize();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void setPanelSize() {
        Dimension sizeMin = new Dimension(1280,800);
        Dimension sizePr = new Dimension(1280,800);
        Dimension sizeMax = new Dimension(1280,800);
        setMinimumSize(sizeMin);
        setPreferredSize(sizePr);
        setMaximumSize(sizeMax);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
        Toolkit.getDefaultToolkit().sync();
    }

    public void updateGame() {

    }

    public Game getGame() {
        return game;
    }
}

