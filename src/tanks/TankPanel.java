package tanks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TankPanel extends JPanel implements ActionListener, KeyListener {

    Tank playerTank;

    private final Timer gameTimer;
    private final int INTERVAL = 30;

    private Dimension panelSize = null;
    
    private ArrayList<Granade> granades = new ArrayList<>();
    
    private Rectangle2D.Double panelRect;
    
    private long frame;
    
    private long lastGranadeFrame;
    private final int FIRE_RATE = 15;

    public TankPanel() {
        setBackground(Color.white);

        playerTank = new Tank(300, 400);

        gameTimer = new Timer(INTERVAL, this);
        gameTimer.start();

        frame = 0;
        lastGranadeFrame = 0;
        
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    public void paint(Graphics g) {
        if (panelSize == null) {
            panelSize = getSize();
            panelRect = new Rectangle2D.Double(0, 0, panelSize.width, panelSize.height);
        }

        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        playerTank.paint(g2d);
        
        for (Granade granade : granades)
            granade.paint(g2d);
    }

    private void move() {
        playerTank.move();
        
        for (Granade granade : granades)
            granade.move();
    }

    private void handleCollisions() {
        handleTankHittingBorders();
        cleanUpGranades();
    }
    
    private void cleanUpGranades() {
        int size = granades.size();
        
        for (int i = size - 1; i >= 0; i--) {
            Granade granade = granades.get(i);
            
            if (!granade.getCollisionRect().intersects(panelRect))
                granades.remove(i);
        }
    }
    
    private void handleTankHittingBorders() {
        playerTank.handleBottomHit(panelSize.height);
        playerTank.handleLeftHit();
        playerTank.handleRightHit(panelSize.width);
        playerTank.handleTopHit();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame++;
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
        
        if (key == KeyEvent.VK_SPACE && frame - lastGranadeFrame > FIRE_RATE) {
            lastGranadeFrame = frame;
            granades.add(playerTank.fireGranade());
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
