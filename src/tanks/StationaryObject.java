package tanks;

import java.awt.geom.Rectangle2D;

public abstract class StationaryObject extends GameObject {

    public StationaryObject(int x, int y, int bigEdge, int smallEdge) {
        super(x, y, bigEdge, smallEdge);
    }

    @Override
    public Rectangle2D.Double getCollisionRect() {
        return new Rectangle2D.Double(x, y, bigEdge, smallEdge);
    }
    
}
