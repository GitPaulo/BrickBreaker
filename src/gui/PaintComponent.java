package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import game.Ball;
import game.BrickGenerator;
import game.Paddle;

public class PaintComponent extends JComponent {
	// Vars
    private Game game;
    private Paddle paddle;
    private Ball ball;
    private BrickGenerator map;

    PaintComponent(Ball ball, Paddle paddle, BrickGenerator map, Game game) {
        this.paddle = paddle;
        this.ball = ball;
        this.map = map;
        this.game = game;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Background
        g.setColor(Game.BACKGROUND_COLOR);
        g.fillRect(1, 1, Game.WIDTH, Game.HEIGHT);

        // Borders
        g.setColor(Game.BORDER_COLOR);
        g.fillRect(0, 0, Game.BORDER_SIZE, Game.HEIGHT);
        g.fillRect(0, 0, Game.WIDTH, Game.BORDER_SIZE);
        g.fillRect(Game.WIDTH - Game.BORDER_SIZE * 3, 0, Game.BORDER_SIZE, Game.HEIGHT);

        // Ball
        g.setColor(ball.getColor());
        g.fillOval(ball.getPosX(), ball.getPosY(), ball.getDiameter(), ball.getDiameter());

        // Paddle
        g.setColor(paddle.getColor());
        g.fillRect(paddle.getPosX(), paddle.getPosY(), paddle.getPaddleSize(), 4);

        // Map
        Graphics2D g2d = (Graphics2D) g;
        map.paintComponent(g2d);

        // Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 18));
        g.drawString("Score: " + game.getScore(), (int) (Game.WIDTH / 1.1 - 75), 30);

        // Game over
        if (game.isGameOver()) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 20));
            g.drawString("Game Over!", Game.WIDTH / 2 - 75, Game.HEIGHT / 2 - 20);
            g.drawString("Restart: SPACE OR End: ENTER", Game.WIDTH / 2 - 150, Game.HEIGHT / 2 + 5);
        }
    }
}
