package entities;

public abstract class Entity {
    protected float posX;
    protected float posY;

    public Entity(float posX, float posY) {
        this.posX = posX;
        this.posY = posY;
    }
}
