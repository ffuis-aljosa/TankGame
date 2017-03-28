package tanks;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public abstract class GameObject {
    protected int x;
    protected int y;
    
    protected final int bigEdge;
    protected final int smallEdge;

    public GameObject(int x, int y, int bigEdge, int smallEdge) {
        this.x = x;
        this.y = y;
        this.bigEdge = bigEdge;
        this.smallEdge = smallEdge;
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
    
    abstract public Rectangle2D.Double getCollisionRect();
    
    abstract public void paint(Graphics2D g2d);
}
