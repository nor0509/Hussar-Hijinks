package inputs;

import entities.Rect;
import main.GamePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Random random = new Random();
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        int width = random.nextInt(20,150);
        int height = random.nextInt(20, 150);
        float speed = random.nextFloat(0.4f,5.0f);
        Color color = new Color(
                random.nextInt(255),
                random.nextInt(255),
                random.nextInt(255)
        );
        gamePanel.rects.add(new Rect(x, y, width, height, speed, color));

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
