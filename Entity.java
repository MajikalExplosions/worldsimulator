
package worldsimulator;

import java.util.Random;

public class Entity {
    private int id;
    private Language dialect;
    private Random rnd;
    private String name;
    private TerrainCell[][] map;
    private int x, y;
    public static int ids = 0;
    
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
        
        while(map[x][y].getAltitude() < 0.6 || map[x][y].getRainfall() < 0.1 || ! (map[x][y].getOwner().isEmpty())) {
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
}
