package entities;

import utils.PlayerAction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

import static java.lang.module.ModuleDescriptor.read;
import static utils.PlayerAction.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed =18;
    private PlayerAction playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private final float playerSpeed = 2;
    private boolean left, up, right, down;

    public Player(float posX, float posY) {
        super(posX, posY);
        loadAnimations();

    }

    public void update(){
        updatePosition();
        updateAnimationTick();
        setAnimation();

    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction.getIndex()][aniIndex], (int)posX, (int)posY, 480, 320, null);
    }

    private void setAnimation() {
        PlayerAction startAni = playerAction;

        if(moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if(attacking)
            playerAction = ATTACK_1;

        if (startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex =0;
    }


    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= playerAction.getSpriteAmount()){
                aniIndex = 0;
                attacking = false;
            }
        }
    }


    private void loadAnimations() {
        animations = new BufferedImage[PlayerAction.values().length][];
        try{
            for(PlayerAction playerAction : PlayerAction.values()){

                animations[playerAction.getIndex()] = new BufferedImage[playerAction.getSpriteAmount()];
                InputStream is = getClass().getResourceAsStream(playerAction.getPath());
                if(is == null){
                    System.err.println("File not found: " + playerAction.getPath());
                    continue;
                }
                BufferedImage img = ImageIO.read(is);
                for(int spriteNumber = 0; spriteNumber < playerAction.getSpriteAmount(); spriteNumber++){
                    animations[playerAction.getIndex()][spriteNumber] = img.getSubimage(120*spriteNumber, 0, 120, 80);

                }
        }
        } catch (IOException e){
            System.out.println("Nie można wczytać obrazu: " + playerAction.getPath());
        }

    }

    private void updatePosition() {

        moving = false;

        if(left && !right) {
            posX-= playerSpeed;
            moving = true;
        }else if(right && !left){
            posX+= playerSpeed;
            moving = true;
        }

        if(up && !down){
            posY-=playerSpeed;
            moving = true;
        }else if (!up && down){
            posY += playerSpeed;
            moving = true;
        }
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        down = false;
        up = false;
    }

    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }
}
