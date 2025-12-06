package utils;

public enum PlayerAction {

    IDLE(0,5),
    RUNNING(1,6),
    JUMP(2,3),
    FALLING(3,1),
    GROUND(4,2),
    HIT(5,4),
    ATTACK_1(6,3),
    ATTACK_JUMP_1(7,3),
    ATTACK_JUMP_2(8,3);

    private final int index;
    private final int spriteAmount;

    PlayerAction(int index, int spriteAmount){
        this.index = index;
        this.spriteAmount = spriteAmount;
    }

    public int getIndex(){
        return index;
    }

    public int getSpriteAmount() {
        return spriteAmount;
    }
}
