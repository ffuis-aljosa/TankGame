package tanks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Granade extends MovingObject {

    public static final int BIG_EDGE = 15;
    public static final int SMALL_EDGE = 20;
    
    private static final int SPEED = 10;
    
    private final Color mainColor = Color.BLACK;
    
    public Granade(int x, int y, Direction direction) {
        super(x, y, BIG_EDGE, SMALL_EDGE, direction, true, SPEED);
    }

    @Override
    public void paint(Graphics2D g2d) {
        Rectangle2D.Double rect = getCollisionRect();
        
        g2d.setPaint(mainColor);
        g2d.drawRect((int)rect.x, (int)rect.y, (int)rect.width, (int)rect.height);
    }
    
}
