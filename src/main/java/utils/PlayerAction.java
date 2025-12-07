package utils;

public enum PlayerAction {

    IDLE("/animations/player/_Idle.png",0,10),
    RUNNING("/animations/player/_Run.png",1,10),
    JUMP("/animations/player/_Jump.png",2,3),
    FALLING("/animations/player/_Fall.png",3,3),
    HIT("/animations/player/_Hit.png",4,1),
    DIE("/animations/player/_Death.png",5,10),
    ATTACK_1("/animations/player/_Attack.png",6,4);

    private final String path;
    private final int index;
    private final int spriteAmount;

    PlayerAction(String path, int index, int spriteAmount){
        this.path = path;
        this.index = index;
        this.spriteAmount = spriteAmount;
    }

    public int getIndex(){
        return index;
    }

    public int getSpriteAmount() {
        return spriteAmount;
    }

    public String getPath() {
        return path;
    }

    public static int getMaxSpriteAmount(){
        int max = 0;
        for(PlayerAction playerAction : PlayerAction.values()){
            if(playerAction.getSpriteAmount() > max)
                max = playerAction.getSpriteAmount();

        }
        return max;
    }
}
