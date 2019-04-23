
package worldsimulator.entity;

import java.util.Random;

public class Race {
    /*
    private Entity[] entities;
    private Entity nullEntity;
    private Random rnd;
    
    public Race(Random r) {
        rnd = r;
    }
    
    public void generate(TerrainCell[][] map) {
        
        int totalSquares = map.length * map[0].length / 1000;
        
        entities = new Entity[rnd.nextInt(totalSquares / 2) + totalSquares / 4 * 3];//idk either okay
        
        nullEntity = new Entity(rnd, map);
        nullEntity.setEmpty();
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j].setOwner(nullEntity);
            }
        }
        
        for (int i = 0; i < entities.length; i++) {
            entities[i] = new Entity(rnd, map);
            entities[i].generate();
            if (i == 10) System.out.println(entities[i].getLanguage().generateWord());
        }
    }
    
    public Entity[] getEntities() {
        return entities;
    }
    */
}
