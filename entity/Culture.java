
package worldsimulator.entity;

import java.util.Random;
import worldsimulator.World;
/*
public class Culture {
    private Language dialect;
    private String name;
    
    public Entity(Random r, TerrainCell[][] m) {
        rnd = r;
        map = m;
        id = ids++;
    }
    
    public Entity(Random r, TerrainCell[][] m, Language l) {
        this(r, m);
        dialect = l;
    }
    
    public void generate() {
        dialect = new Language(rnd);
        String n = dialect.generateWord();
        name = n.substring(0, 1).toUpperCase() + n.substring(1);
        x = rnd.nextInt(map.length);
        y = rnd.nextInt(map[0].length);
        
        while(map[x][y].getAltitude() < World.COASTLINE_ALTITUDE || map[x][y].getRainfall() < World.DESERT_BOUND || ! (map[x][y].getOwner().isEmpty())) {
            x = rnd.nextInt(map.length);
            y = rnd.nextInt(map[0].length);
        }
        
        map[x][y].setOwner(this);
    }
    
    public void setEmpty() {
        name = "_null";
        id = -1;
        dialect = new Language(rnd);
    }
    
    public boolean isEmpty() {
        if (name.equals("_null") && id == -1) {
            return true;
        }
        return false;
    }
    
    public int getID() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Language getLanguage() {
        return dialect;
    }
    
    public void update() {
        
    }
}
*/