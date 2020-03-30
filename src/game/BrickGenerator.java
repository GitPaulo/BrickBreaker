package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import gui.Game;

public class BrickGenerator {
    // Map Defaults
    public static final int WIDTH = (int) (Game.WIDTH / 1.38);
    public static final int HEIGHT = (int) (Game.HEIGHT / 3.25);
    
    // Instance Variables
    private final int map[][];
    private final int brickWidth;
    private final int brickHeight;

    public BrickGenerator(int row, int col) {
        map = new int[row][col];
        
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                map[i][j] = 1;

        brickWidth = WIDTH / col;
        brickHeight = HEIGHT / row;
    }

    public void paintComponent(Graphics2D g2d) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g2d.setColor(Color.white);
                    g2d.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    g2d.setStroke(new BasicStroke(3));
                    g2d.setColor(Color.BLACK);
                    g2d.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }

    public void setBrickValue(int row, int col, int value) {
        map[row][col] = value;
    }

    public void reset() {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                map[i][j] = 1;
    }

    public int[][] getMap() {
        return map;
    }

    public int getBrickWidth() {
        return brickWidth;
    }

    public int getBrickHeight() {
        return brickHeight;
    }
}
