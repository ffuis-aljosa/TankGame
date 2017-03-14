package tanks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.stream.IntStream;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TankPanel extends JPanel implements ActionListener, KeyListener {

    Tank playerTank;

    private final Timer gameTimer;
    private final int INTERVAL = 30;

    private Dimension panelSize = null;

    public TankPanel() {
        setBackground(Color.white);

        playerTank = new Tank(300, 400);

        gameTimer = new Timer(INTERVAL, this);
        gameTimer.start();

        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {
        if (panelSize == null) {
            panelSize = getSize();
        }

        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        playerTank.paint(g2d);
    }

    private void move() {
        playerTank.move();
    }

    private void handleCollisions() {
        handleTankHittingBorders();
    }
    
    private void handleTankHittingBorders() {
        playerTank.handleBottomHit(panelSize.height);
        playerTank.handleLeftHit();
        playerTank.handleRightHit(panelSize.width);
        playerTank.handleTopHit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        handleCollisions();
        repaint();
    }
    
    boolean isRightDown = false;
    boolean isLeftDown = false;
    boolean isUpDown = false;
    boolean isDownDown = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        int[] movementKeys = {
            KeyEvent.VK_RIGHT, 
            KeyEvent.VK_LEFT, 
            KeyEvent.VK_UP, 
            KeyEvent.VK_DOWN
        };
        
        for (int i = 0; i < movementKeys.length; i++)
            if (key == movementKeys[i]) {
                playerTank.setIsMoving(true);
                break;
            }
        
        switch (key) {
            case KeyEvent.VK_RIGHT:
                playerTank.setDirection(Tank.Direction.RIGHT);
                isRightDown = true;
                break;
            case KeyEvent.VK_LEFT:
                playerTank.setDirection(Tank.Direction.LEFT);
                isLeftDown = true;
                break;
            case KeyEvent.VK_UP:
                playerTank.setDirection(Tank.Direction.UP);
                isUpDown = true;
                break;
            case KeyEvent.VK_DOWN:
                playerTank.setDirection(Tank.Direction.DOWN);
                isDownDown = true;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_RIGHT:
                isRightDown = false;
                break;
            case KeyEvent.VK_LEFT:
                isLeftDown = false;
                break;
            case KeyEvent.VK_UP:
                isUpDown = false;
                break;
            case KeyEvent.VK_DOWN:
                isDownDown = false;
                break;
            default:
                break;
        }
        
        if (!isRightDown && !isLeftDown && !isUpDown && !isDownDown)
            playerTank.setIsMoving(false);
    }
}
