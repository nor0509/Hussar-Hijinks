package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static utils.Directions.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel = gamePanel;

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyPressed) {

        switch (keyPressed.getKeyCode()){
            case KeyEvent.VK_W:
                gamePanel.setPlayerDirection(UP);
                break;
            case KeyEvent.VK_S:
                gamePanel.setPlayerDirection(DOWN);
                break;
            case KeyEvent.VK_A:
                gamePanel.setPlayerDirection(LEFT);
                break;
            case KeyEvent.VK_D:
                gamePanel.setPlayerDirection(RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyReleased) {
        switch (keyReleased.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_A:
            case KeyEvent.VK_D:
                gamePanel.setMoving(false);
                break;
        }
    }
}

