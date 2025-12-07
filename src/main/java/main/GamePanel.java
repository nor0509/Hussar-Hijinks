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

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100, xSpeed = 3, ySpeed =3;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed =18;
    private PlayerAction playerState = IDLE;
    private Directions playerDirection = NONE;
    private boolean moving = false;

    public GamePanel() {
        MouseInputs mouseInput = new MouseInputs(this);

        importImg();
        loadAnimations();

        setPanelSize();

        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);

    }

    private void loadAnimations() {
        animations = new BufferedImage[PlayerAction.values().length][6];
        for(PlayerAction action : PlayerAction.values()){
            int index = action.getIndex();
            int spriteAmount = action.getSpriteAmount();
            for(int sprite = 0; sprite<spriteAmount; sprite++){
                animations[index][sprite] = img.getSubimage(sprite*64, index*40, 64, 40);
            }
        }

    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try{
            img = ImageIO.read(is);
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

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

        g.drawImage(animations[playerState.getIndex()][aniIndex], (int)xDelta, (int)yDelta, 256, 160, null);
        Toolkit.getDefaultToolkit().sync();
    }

    private void updatePosition() {
        if(moving){
            switch(playerDirection) {
                case LEFT:
                    xDelta -= xSpeed;
                    break;
                case UP:
                    yDelta -= ySpeed;
                    break;
                case RIGHT:
                    xDelta += xSpeed;
                    break;
                case DOWN:
                    yDelta += ySpeed;
                    break;
            }
        }
    }

    private void setAnimation() {
        if(moving)
            playerState = RUNNING;
        else
            playerState = IDLE;
    }

    public void setPlayerDirection(Directions playerDirection) {
        this.playerDirection = playerDirection;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= playerState.getSpriteAmount()){
                aniIndex = 0;
            }
        }
    }

    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }
}

