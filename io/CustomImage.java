
package worldsimulator.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomImage {
    
    private BufferedImage image;
    
    public CustomImage(int x, int y) {
        image = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
    }
    
    public void setPixelRGB(int x, int y, int r, int g, int b) {
        int rgb = r;
        rgb = (rgb << 8) + g;
        rgb = (rgb << 8) + b;
        image.setRGB(x, y, rgb);
    }
    
    public void setPixelBW(int x, int y, int v) {
        setPixelRGB(x, y, v, v, v);
    }
    
    public void setPixelRaw(int x, int y, int v) {
        image.setRGB(x, y, v);
    }
    
    public boolean writeToFile(String file) {
        try {
            File f = new File(file + ".png");
            ImageIO.write(image, "png", f);
            return true;
        }
        catch(IOException e) {
            return false;
        }
    }
}
