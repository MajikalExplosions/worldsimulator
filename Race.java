
package worldsimulator;

import java.util.Random;

public class Race {
    
    private Entity[] entities;
    private Random rnd;
    
    public Race(Random r) {
        rnd = r;
    }
    
    public void generate(TerrainCell[][] map) {
        
        int totalSquares = map.length * map[0].length / 1000;
        
        entities = new Entity[rnd.nextInt(totalSquares / 2) + totalSquares / 4 * 3];//idk either okay
        
        entities[0] = new Entity(rnd, map);
        entities[0].setEmpty();
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j].setOwner(entities[0]);
            }
        }
        
        for (int i = 1; i < entities.length; i++) {
            entities[i] = new Entity(rnd, map);
            entities[i].generate();
        }
    }
}
