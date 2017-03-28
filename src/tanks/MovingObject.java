package tanks;

import java.awt.geom.Rectangle2D;

public abstract class MovingObject extends GameObject {
    
    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    };
    
    protected final int speed;
    
    protected Direction direction;
    protected boolean isMoving;

    public MovingObject(int x, int y, int bigEdge, int smallEdge,
            Direction direction, boolean isMoving, int speed) {
        super(x, y, bigEdge, smallEdge);
        
        this.direction = direction;
        this.isMoving = isMoving;
        this.speed = speed;
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

    @Override
    public Rectangle2D.Double getCollisionRect() {
        int w, h;
        
        if (direction == MovingObject.Direction.UP || direction == MovingObject.Direction.DOWN) {
            h = smallEdge;
            w = bigEdge;
        } else {
            h = bigEdge;
            w = smallEdge;
        }
        
        return new Rectangle2D.Double(x, y, w, h);
    }
}
