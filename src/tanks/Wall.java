package tanks;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Wall extends StationaryObject {

    public Wall(int x, int y, int bigEdge, int smallEdge) {
        super(x, y, bigEdge, smallEdge);
    }

    @Override
    public void paint(Graphics2D g2d) {
        Rectangle2D.Double rect = getCollisionRect();

        g2d.drawImage(ImageProvider.sprites,
                (int)rect.x, (int)rect.y,
                (int)(rect.x + rect.width), (int)(rect.y + rect.height),
                256, 0,
                271, 15,
                null
        );
    }

}
