
package worldsimulator;

import java.util.HashSet;
import java.util.Random;

public class World {
    
    Terrain terrain;
    Race humans;
    
    private Random rnd;
    
    public World(Random r) {
        rnd = r;
        terrain = new Terrain(rnd, 10);
        /*
        RUN TIMES:
        0 - 7: Negligible
        8: ~0.06GB; 2"; 257*257
        9: ~0.09GB; 3"; 513*513
        10: ~0.16GB; 4"; 1025*1025
        11: ~0.38GB; 10"; 2049*2049
        12: ~1.25GB; 24"; 4097*4097
        13: ~3.85GB; 1'20"; 8193*8193
        14: Don't even try.
        */
        humans = new Race(rnd);
    }
    
    public boolean generate() {
        System.out.println("[A] Generating World...");
        terrain.generate();
        System.out.println("[A] World Generated.\n[A] Generating Entities...");
        humans.generate(terrain.getMap());
        System.out.println("[A] Entities Generated.");
        
        System.out.println("[C] Drawing PNG with Factions...");
        CustomImage ci5 = new CustomImage((int) Math.pow(2, terrain.getSide()) + 1, (int)Math.pow(2, terrain.getSide()) + 1);
        for (int i = 0; i < terrain.getMap().length; i++) {
            for (int j = 0; j < terrain.getMap()[0].length; j++) {
                if (! terrain.getMap()[i][j].getOwner().isEmpty()) ci5.setPixelRGB(i, j, rnd.nextInt(200) + 56, rnd.nextInt(200) + 56, rnd.nextInt(200) + 56);
                else if (terrain.getMap()[i][j].getAltitude() < 0.6) ci5.setPixelBW(i, j, 0);
                else ci5.setPixelBW(i, j, 255);
            }
        }
        ci5.writeToFile("factions");
        
        System.out.println("[0] Planet Generated.");
        return true;
    }
}
