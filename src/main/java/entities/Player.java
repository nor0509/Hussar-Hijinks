package entities;

import utils.PlayerAction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.PlayerAction.*;

public class Player extends Entity{
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed =18;
    private PlayerAction playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private float playerSpeed = 3;
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
        g.drawImage(animations[playerAction.getIndex()][aniIndex], (int)posX, (int)posY, 256, 160, null);
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
            InputStream is = getClass().getResourceAsStream("/player_sprites.png");
            try{
                BufferedImage img = ImageIO.read(is);
                animations = new BufferedImage[PlayerAction.values().length][6];
                for(PlayerAction action : PlayerAction.values()){
                    int index = action.getIndex();
                    int spriteAmount = action.getSpriteAmount();
                    for(int sprite = 0; sprite<spriteAmount; sprite++){
                        animations[index][sprite] = img.getSubimage(sprite*64, index*40, 64, 40);
                    }
                }
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
