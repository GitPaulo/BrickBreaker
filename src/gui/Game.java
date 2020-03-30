package gui;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import game.Ball;
import game.BrickGenerator;
import game.Paddle;
import game.Player;

public class Game extends JFrame implements KeyListener {    
    // Default GAME values
    public static final int WIDTH = 600;
    public static final int HEIGHT = 550;
    public static final int PADDING = 50;
    public static final int BALL_BASE_SPEED = 1;
    public static final int PADDLE_SPEED = 25;
    public static final int BORDER_SIZE = 3;
    public static final Color BACKGROUND_COLOR = Color.DARK_GRAY;
    public static final Color BORDER_COLOR = Color.BLACK;

    // Instance GAME values
    private final int delayPaint = 8;
    private final int delayPhysics = 6;
    private boolean play = false;
    private boolean gameOver = false;
    private int score = 0;
    private int totalBricks = 0;
    private int rowBricks = 0;
    private int colBricks = 0;
    private int increaseInSpeed = 0;
    
    // Instance Objects
    private Paddle paddle = null;
    private Ball ball = null;
    private BrickGenerator map = null;
    private PaintComponent paint = null;
    private Timer timerPaint = null;
    private Timer timerPhysics = null;
    private Player player = null;

    public Game(Player player) {
        // Frame setup
        this.setBounds(10, 10, WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        
        // Difficult set up
        switch (player.getDifficulty()) {
            case EASY:
                increaseInSpeed = 0;
                rowBricks = 3;
                colBricks = 7;
                break;
            case MEDIUM:
                increaseInSpeed = 1;
                rowBricks = 3;
                colBricks = 7;
                break;
            case HARD:
                increaseInSpeed = 2;
                rowBricks = 3;
                colBricks = 8;
                break;
            case INSANE:
                increaseInSpeed = 3;
                rowBricks = 3;
                colBricks = 9;
                break;
        }
        
        // Instance objects and timers
        this.totalBricks = rowBricks * colBricks;
        this.player = player;
        this.paddle = new Paddle(Paddle.STARTING_PADDLE_POSX, Paddle.STARTING_PADDLE_POSY, Paddle.STARTING_PADDLE_SIZE,
                Paddle.STARTING_PADDLE_COLOR);
        this.ball = new Ball(Ball.STARTING_BALL_POSX, Ball.STARTING_BALL_POSY, Ball.STARTING_BALL_DIRX,
                Ball.STARTING_BALL_DIRY, Ball.STARTING_BALL_DIAMETER, Ball.STARTING_BALL_COLOR);
        this.map = new BrickGenerator(rowBricks, colBricks);
        this.paint = new PaintComponent(ball, paddle, map, this);
        this.add(paint);
        
        // Timers
        timerPhysics = new Timer(delayPhysics, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                calculatePhysics();
            }
        });
        
        timerPaint = new Timer(delayPaint, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                paint.repaint();
            }
        });
        
        timerPhysics.start();
        timerPaint.start();
    }

    public void calculatePhysics() {
        if (play) {
            // Paddle-Ball intersection
            if (ball.getCollisionRectangle().intersects(paddle.getCollisionRectangle())) {
                ball.setDirY(-ball.getDirY());
            }
            
            // Ball-Brick intersection
            A: for (int i = 0; i < map.getMap().length; i++) {
                for (int j = 0; j < map.getMap()[0].length; j++) {
                    if (map.getMap()[i][j] > 0) {
                        int brickX = j * map.getBrickWidth() + map.getBrickWidth();
                        int brickY = i * map.getBrickHeight() + map.getBrickHeight();
                        int brickWidth = map.getBrickWidth();
                        int brickHeight = map.getBrickHeight();

                        Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);

                        if (ball.getCollisionRectangle().intersects(brickRect)) {
                            System.out.println("Collision!");
                            map.setBrickValue(i, j, 0);
                            totalBricks = totalBricks - 1;
                            score = score + 5;

                            if (ball.getPosX() + (brickWidth - 1) <= brickRect.x
                                    || ball.getPosX() + 1 >= brickRect.x + brickRect.width) {
                                ball.setDirX(increaseInSpeed + BALL_BASE_SPEED);
                            } else {
                                ball.setDirY(increaseInSpeed + BALL_BASE_SPEED);
                            }

                            break A;
                        }
                    }
                }
            }

            ball.setPosX(ball.getPosX() + ball.getDirX());
            ball.setPosY(ball.getPosY() + ball.getDirY());

            // Upper boundary and left boundary
            if (ball.getPosX() <= BORDER_SIZE) {
                ball.setDirX(increaseInSpeed + BALL_BASE_SPEED);
            }

            if (ball.getPosY() <= BORDER_SIZE * 9) { // header counts
                ball.setDirY(increaseInSpeed + BALL_BASE_SPEED);
            }

            // upper boundary
            if (ball.getPosX() > WIDTH) {
                ball.setDirX(-BALL_BASE_SPEED - increaseInSpeed);
            }

            // End game
            if ((ball.getPosY() > (paddle.getPosY() + 4)) || totalBricks == 0) {
                play = false;
                gameOver = true;
                System.out.println("Game ended!");
            }
        }
    }

    private void moveRight() {
        play = true;
        paddle.setPosX(paddle.getPosX() + PADDLE_SPEED);
    }

    private void moveLeft() {
        play = true;
        paddle.setPosX(paddle.getPosX() - PADDLE_SPEED);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            int limit = WIDTH - paddle.getPaddleSize() - BORDER_SIZE;
            if (paddle.getPosX() >= limit) {
                paddle.setPosX(limit);
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            int limit = BORDER_SIZE;
            if (paddle.getPosX() <= limit) {
                paddle.setPosX(limit);
            } else {
                moveLeft();
            }
        }

        if (gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) { // Leaderboard
                player.setScore(score);
                Leaderboard ld = new Leaderboard(player);
                ld.setVisible(true);
                ld.setLocationRelativeTo(null);
                dispose();
            }

            if (e.getKeyCode() == KeyEvent.VK_SPACE) { // Restart
                gameOver = false;
                score = 0;
                // paddle first, since starting ball pos is dependent on paddle position
                paddle.setPosX(Paddle.STARTING_PADDLE_POSX);
                paddle.setPosY(Paddle.STARTING_PADDLE_POSY);
                ball.setPosX(Ball.STARTING_BALL_POSX);
                ball.setPosY(Ball.STARTING_BALL_POSY);
                map.reset();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }
}
