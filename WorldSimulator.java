
package worldsimulator;

import java.util.Random;
import worldsimulator.io.CustomImage;
import worldsimulator.terrain.Terrain;

public class WorldSimulator {
    
    
    
    public static void main(String[] args) {
        Terrain t = new Terrain(new Random());
        /*
        CustomImage ci2 = new CustomImage(1000, 1000);
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                ci2.setPixelBW(i, j, (t.getHeightAt((double) i / 10d, (double) j / 10d) < Constants.WATER_LEVEL) ? 0 : 255);
            }
        }
        ci2.writeToFile("bleep2");
        */
    }
}
