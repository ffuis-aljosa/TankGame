package tanks;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TankPanel extends JPanel implements ActionListener {
    
    private int ballX = 50;
    private int ballY = 50;
    private final int ballDimension = 75;
    private final Color ballColor = Color.RED;
    private final Color ballBorder = Color.BLACK;
    private final int ballSpeed = 5;
    private int ballXDirection = 1;
    private int ballYDirection = 1;
    
    private final Timer gameTimer;
    private final int INTERVAL = 30;
    
    private Dimension panelSize = null;
    
    public TankPanel() {
        setBackground(Color.white);
        gameTimer = new Timer(INTERVAL, this);
        gameTimer.start();
    }

    @Override
    public void paint(Graphics g) {
        if (panelSize == null)
            panelSize = getSize();
            
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setPaint(ballColor);
        g2d.fillOval(ballX, ballY, ballDimension, ballDimension);
        
        g2d.setPaint(ballBorder);
        g2d.drawOval(ballX, ballY, ballDimension, ballDimension);
    }
    
    private void move()
    {
        ballX += ballXDirection * ballSpeed;
        ballY += ballYDirection * ballSpeed;
    }
    
    private void handleCollisions()
    {
        if (ballY + ballDimension >= panelSize.height)
            ballYDirection *= -1;
        if (ballX + ballDimension >= panelSize.width)
            ballXDirection *= -1;
        if (ballY <= 0)
            ballYDirection *= -1;
        if (ballX <= 0)
            ballXDirection *= -1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        handleCollisions();
        repaint();
    }
}
