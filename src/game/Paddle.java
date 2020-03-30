package game;

import java.awt.Color;
import java.awt.Rectangle;

import gui.Game;

public class Paddle {
    // Starting Values
    public static final int STARTING_PADDLE_SIZE = 70;
    public static final int STARTING_PADDLE_POSX = Game.WIDTH / 2 - STARTING_PADDLE_SIZE / 2;
    public static final int STARTING_PADDLE_POSY = Game.HEIGHT - Game.PADDING;
    public static final Color STARTING_PADDLE_COLOR = Color.LIGHT_GRAY;
    
    // Instance Values
    private int posX;
    private int posY;
    private int size;
    private Color color;

    public Paddle(int posX, int posY, int size, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        this.color = color;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPaddleSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rectangle getCollisionRectangle() {
        return new Rectangle(posX, posY, size, 4);
    }
}
