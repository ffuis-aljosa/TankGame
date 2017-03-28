package tanks;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Granade extends MovingObject {

    public static final int BIG_EDGE = 9;
    public static final int SMALL_EDGE = 12;
    
    private static final int SPEED = 10;
    
    private final Color mainColor = Color.BLACK;
    
    public Granade(int x, int y, Direction direction) {
        super(x, y, BIG_EDGE, SMALL_EDGE, direction, true, SPEED);
    }

    @Override
    public void paint(Graphics2D g2d) {
        Rectangle2D.Double rect = getCollisionRect();
        
        int dx1 = (int)rect.x, dx2 = (int)(rect.x + rect.width), 
                dy1 = (int)rect.y, dy2 = (int)(rect.y + rect.height),
                sx1 = 0, sx2 = 0, sy1 = 0, sy2 = 0;

        switch (direction) {
            case UP:
                sx1 = 323;
                sx2 = 326;
                sy1 = 102;
                sy2 = 106;
                break;
            case LEFT:
                sx1 = 330;
                sx2 = 335;
                sy1 = 102;
                sy2 = 105;
                break;
            case DOWN:
                sx1 = 339;
                sx2 = 342;
                sy1 = 102;
                sy2 = 106;
                break;
            case RIGHT:
                sx1 = 346;
                sx2 = 350;
                sy1 = 102;
                sy2 = 105;
                break;
        }
        
        g2d.drawImage(ImageProvider.sprites, dx1, dy1, dx2, dy2,
                sx1, sy1, sx2, sy2, null);
    }
    
}
