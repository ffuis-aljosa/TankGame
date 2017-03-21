package tanks;

import java.awt.Graphics2D;
import java.awt.Color;

public class Tank extends MovingObject {

    private static final int DIMENSION = 60;
    private static final int SPEED = 5;

    private final Color mainColor = Color.BLACK;

    public Tank(int x, int y) {
        super(x, y, DIMENSION, DIMENSION, Direction.UP, false, SPEED);
    }

    public void handleBottomHit(int height) {
        if (y + this.smallEdge >= height) {
            y = height - this.smallEdge;
        }
    }

    public void handleTopHit() {
        if (y <= 0) {
            y = 0;
        }
    }

    public void handleRightHit(int width) {
        if (x + this.bigEdge >= width) {
            x = width - this.bigEdge;
        }
    }

    public void handleLeftHit() {
        if (x <= 0) {
            x = 0;
        }
    }

    public Granade fireGranade() {
        int granadeX = 0, granadeY = 0;
        
        switch (direction) {
            case UP:
                granadeX = x + DIMENSION / 2 - Granade.SMALL_EDGE / 2;
                granadeY = y - Granade.BIG_EDGE;
                break;
            case DOWN:
                granadeX = x + DIMENSION / 2 - Granade.SMALL_EDGE / 2;
                granadeY = y + DIMENSION;
                break;
            case RIGHT:
                granadeX = x + DIMENSION;
                granadeY = y + DIMENSION / 2 - Granade.SMALL_EDGE / 2;
                break;
            case LEFT:
                granadeX = x - Granade.BIG_EDGE;
                granadeY = y + DIMENSION / 2 - Granade.SMALL_EDGE / 2;
                break;
        }

        return new Granade(granadeX, granadeY, direction);
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setPaint(mainColor);
        g2d.drawRect(x, y, bigEdge, smallEdge);
    }

}
