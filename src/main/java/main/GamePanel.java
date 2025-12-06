package main;

import entities.Rect;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel {
    private final Random random;
    public ArrayList<Rect> rects;

    public GamePanel() {
        random = new Random();
        MouseInputs mouseInput = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
        rects = new ArrayList<Rect>();
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Rect r : rects){
            updateRectangle(r);
            g.setColor(r.color);
            g.fillRect((int)r.x, (int)r.y, r.width, r.height);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void updateRectangle(Rect r){
        r.x += r.speedX;
        if(r.x >= 400 || r.x <= 0) {
            r.speedX = r.speedX * -1;
            r.color = getRndColor();
        }
        r.y += r.speedY;
        if(r.y >= 400 || r.y <= 0){
            r.speedY = r.speedY*-1;
            r.color = getRndColor();
        }

    }

    private Color getRndColor(){
        int r = random.nextInt(255);
        int b = random.nextInt(255);
        int g = random.nextInt(255);

        return new Color(r,g,b);

    }
}

