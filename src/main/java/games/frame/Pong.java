package games.frame;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pong extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = 1L;
    private int width = 640;
    private int height = 480;
    private int ballX = 300;
    private int ballY = 200;
    private int ballSize = 20;
    private int ballSpeedX = 2;
    private int ballSpeedY = 2;
    private int paddleWidth = 20;
    private int paddleHeight = 80;
    private int playerOneX = 30;
    private int playerOneY = 200;
    private int playerTwoX = 590;
    private int playerTwoY = 200;
    private int playerSpeed = 5;
    private boolean playerOneUp = false;
    private boolean playerOneDown = false;
    private boolean playerTwoUp = false;
    private boolean playerTwoDown = false;

    public Pong() {
        super("Pong");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.fillRect(playerOneX, playerOneY, paddleWidth, paddleHeight);
        g.fillRect(playerTwoX, playerTwoY, paddleWidth, paddleHeight);
        g.fillRect(ballX, ballY, ballSize, ballSize);
    }

    public void update() {
        // Update ball position
        ballX += ballSpeedX;
        ballY += ballSpeedY;

        // Check for collisions with top and bottom walls
        if (ballY <= 0 || ballY + ballSize >= height) {
            ballSpeedY *= -1;
        }

        // Check for collisions with player paddles
        if (ballX <= playerOneX + paddleWidth && ballY + ballSize >= playerOneY && ballY <= playerOneY + paddleHeight) {
            ballSpeedX *= -1;
        }
        if (ballX + ballSize >= playerTwoX && ballY + ballSize >= playerTwoY && ballY <= playerTwoY + paddleHeight) {
            ballSpeedX *= -1;
        }

        // Check for player movement
        if (playerOneUp) {
            playerOneY -= playerSpeed;
        }
        if (playerOneDown) {
            playerOneY += playerSpeed;
        }
        if (playerTwoUp) {
            playerTwoY -= playerSpeed;
        }
        if (playerTwoDown) {
            playerTwoY += playerSpeed;
        }

        // Redraw the screen
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            playerOneUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            playerOneDown = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            playerTwoUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            playerTwoDown = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            playerOneUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            playerOneDown = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            playerTwoUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            playerTwoDown = false;
        }
    }

    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        Pong game = new Pong();
        while (true) {
            game.update();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

