package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

import gui.Game;

public class Ball {
    // Starting Values
    public static final int STARTING_BALL_DIAMETER = 8;
    public static final int STARTING_BALL_POSX = Paddle.STARTING_PADDLE_POSX + Paddle.STARTING_PADDLE_SIZE / 2
            - STARTING_BALL_DIAMETER / 2;
    public static final int STARTING_BALL_POSY = Paddle.STARTING_PADDLE_POSY - 4 - STARTING_BALL_DIAMETER;
    public static final int STARTING_BALL_DIRX = -(new Random().nextInt(Game.BALL_BASE_SPEED)) - 1;// +1 to avoid
                                                                                                   // straight up
    public static final int STARTING_BALL_DIRY = -1;
    public static final Color STARTING_BALL_COLOR = Color.orange;
    
    // Instance Values
    private int posX;
    private int posY;
    private int dirX;
    private int dirY;
    private int diameter;
    private Color color;

    public Ball(int posX, int posY, int dirX, int dirY, int diameter, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.dirX = dirX;
        this.dirY = dirY;
        this.diameter = diameter;
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

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public Rectangle getCollisionRectangle() {
        return new Rectangle(posX, posY, diameter, diameter);
    }
}
