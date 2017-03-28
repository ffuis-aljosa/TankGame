package tanks;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageProvider {
    static BufferedImage sprites;
    
    public static void loadSprites() {
        try {
            sprites = ImageIO.read(new File("./src/images/sprites.png"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
