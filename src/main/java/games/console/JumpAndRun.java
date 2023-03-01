package games.console;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * In diesem Beispiel-Spiel muss der Spieler (ein rotes Rechteck) über Hindernisse (grüne Rechtecke) springen und dabei Punkte sammeln.
 * Der Spieler wird mit der Leertaste gesteuert und springt, wenn er nicht bereits in der Luft ist.
 * Wenn der Spieler ein Hindernis berührt, ist das Spiel vorbei und die Punktzahl wird angezeigt.
 * Bitte beachten Sie, dass dies ein sehr einfaches Spiel ist und dass der Code nicht sehr robust oder skalierbar ist.
 * Es dient nur als Beispiel dafür, wie ein Jump and Run Spiel in Java erstellt werden könnte.
 */

public class JumpAndRun extends JPanel implements ActionListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int PLAYER_WIDTH = 10;
    private static final int PLAYER_HEIGHT = 10;
    private static final int GROUND_HEIGHT = 50;
    private static final int MAX_ENEMIES = 5;
    private static final int ENEMY_WIDTH = 3;
    private static final int ENEMY_HEIGHT = 3;
    private static final int ENEMY_SPEED = 3;
    private static final int JUMP_VELOCITY = 10;
    private static final int GRAVITY = 1;
    private static final int TICK_DELAY = 10;
    private static final int SCORE_INCREMENT = 10;
    
    private enum GameState {
        RUNNING, PAUSED, GAMEOVER
    }

    private GameState state;

    private Rectangle player;
    private ArrayList<Rectangle> enemies;
    private int score;
    private boolean isJumping;
    private int jumpVelocity;
    private Timer timer;
    private Random random;

    public JumpAndRun() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(this);
        setFocusable(true);

        player = new Rectangle(0, HEIGHT - GROUND_HEIGHT - PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        enemies = new ArrayList<Rectangle>();
        score = 0;
        isJumping = false;
        jumpVelocity = 0;
        timer = new Timer(TICK_DELAY, this);
        random = new Random();
        state = GameState.RUNNING;
        
        spawnEnemies();
    }

    private void spawnEnemies() {
        int numEnemies = random.nextInt(MAX_ENEMIES) + 1;
        for (int i = 0; i < numEnemies; i++) {
            int x = random.nextInt(WIDTH - ENEMY_WIDTH);
            int y = HEIGHT - GROUND_HEIGHT - ENEMY_HEIGHT;
            enemies.add(new Rectangle(x, y, ENEMY_WIDTH, ENEMY_HEIGHT));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + score, 10, 20);

        g.setColor(Color.RED);
        g.fillRect(player.x, player.y, player.width, player.height);

        g.setColor(Color.GREEN);
        for (Rectangle enemy : enemies) {
            g.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);
        }
    }

    private void update() {
        if (isJumping) {
            player.y -= jumpVelocity;
            jumpVelocity -= GRAVITY;
            if (player.y >= HEIGHT - GROUND_HEIGHT - PLAYER_HEIGHT) {
                player.y = HEIGHT - GROUND_HEIGHT - PLAYER_HEIGHT;
                isJumping = false;
            }
        } else {
            player.y = HEIGHT - GROUND_HEIGHT - PLAYER_HEIGHT;
        }

        for (Rectangle enemy : enemies) {
            enemy.x -= ENEMY_SPEED;
            if (enemy.x < -ENEMY_WIDTH) {
                enemy.x = WIDTH;
                score += SCORE_INCREMENT;
            }
            if (player.intersects(enemy)) {
                gameOver();
            }
        }

        if (enemies.size() == 0) {
            spawnEnemies();
        }

        repaint();
    }

    private void gameOver() {
        state = GameState.GAMEOVER;
        timer.stop();
        System.out.println("Game Over!");
        System.out.println("Score: " + score);
    }
    
    private void restartGame() {
        player = new Rectangle(0, HEIGHT - GROUND_HEIGHT - PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
        enemies = new ArrayList<Rectangle>();
        score = 0;
        isJumping = false;
        jumpVelocity = 0;
        state = GameState.RUNNING;
        spawnEnemies();
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE && !isJumping) {
            isJumping = true;
            jumpVelocity = JUMP_VELOCITY;
        } else if (keyCode == KeyEvent.VK_Q && state == GameState.GAMEOVER) {
            restartGame();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jump and Run");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JumpAndRun game = new JumpAndRun();
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.timer.start();
    }
}