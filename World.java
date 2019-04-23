
package worldsimulator;

import worldsimulator.entity.Race;
import worldsimulator.terrain.Terrain;
import java.util.HashSet;
import java.util.Random;

public class World {
    
    public static final double COASTLINE_ALTITUDE = 0.6;
    public static final double DESERT_BOUND = 0.1;
    
    public Terrain terrain;
    public Race humans;
    
    private Random rnd;
    
    public World(Random r) {
        rnd = r;
        terrain = new Terrain(rnd);
        humans = new Race();
    }
    
    public boolean generate() {
        
        /*
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
        */
        System.out.println("Planet Generated.");
        return true;
    }
    
    public void simulate(int n) {
        //while (n-- > 0) simulateYear();
    }
    /*
    private void simulateYear() {
        Entity[] entities = humans.getEntities();
        
        int index = rnd.nextInt(entities.length);
        
        for(int i = 0; i < entities.length; i++) {
            entities[(i + index) % entities.length].update();
        }
    }
    */
}
