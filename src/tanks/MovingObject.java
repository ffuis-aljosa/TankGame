package tanks;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class MovingObject {
    
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    };
    
    protected int x;
    protected int y;
    
    protected final int bigEdge;
    protected final int smallEdge;
    
    protected final int speed;
    
    protected Direction direction;
    protected boolean isMoving;

    public MovingObject(int x, int y, int bigEdge, int smallEdge,
            Direction direction, boolean isMoving, int speed) {
        this.x = x;
        this.y = y;
        this.bigEdge = bigEdge;
        this.smallEdge = smallEdge;
        this.direction = direction;
        this.isMoving = isMoving;
        this.speed = speed;
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
    
    public Rectangle2D.Double getCollisionRect() {
        int w, h;
        
        if (direction == Direction.UP || direction == Direction.DOWN) {
            h = smallEdge;
            w = bigEdge;
        } else {
            h = bigEdge;
            w = smallEdge;
        }
        
        return new Rectangle2D.Double(x, y, w, h);
    }
    
    abstract public void paint(Graphics2D g2d);
}
