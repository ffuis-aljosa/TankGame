package tanks;

import java.awt.Graphics2D;
import java.awt.Color;

public class Tank {

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    };

    private int x;
    private int y;
    private final int dimension = 75;

    private Direction direction;
    private final int speed = 5;
    private boolean isMoving;

    private final Color mainColor = Color.BLACK;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
        this.direction = Direction.UP;
        this.isMoving = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    
    public void handleBottomHit(int height)
    {
        if (y + dimension >= height)
            y = height - dimension;
    }
    
    public void handleTopHit()
    {
        if (y <= 0)
            y = 0;
    }
    
    public void handleRightHit(int width)
    {
        if (x + dimension >= width)
            x = width - dimension;
    }
    
    public void handleLeftHit()
    {
        if (x <= 0)
            x = 0;
    }

    public void move() {
        if (isMoving) {
            switch (direction) {
                case UP:
                    this.y -= speed;
                    break;
                case DOWN:
                    this.y += speed;
                    break;
                case RIGHT:
                    this.x += speed;
                    break;
                case LEFT:
                    this.x -= speed;
                    break;
                default:
                    break;
            }
        }
    }

    public void paint(Graphics2D g2d) {
        g2d.setPaint(mainColor);
        g2d.drawRect(x, y, dimension, dimension);
    }

}
