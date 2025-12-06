package entities;

import java.awt.*;
import java.util.Random;

public class Rect {
    public float speedY;
    public float speedX;
    public Color color;
    public float x = 0;
    public float y = 0;
    public int width = 0;
    public int height = 0;

    public Rect(float x, float y, int width, int height, float speed, Color color){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speedX = speed;
        this.speedY = speed;
        this.color = color;
    }
}
