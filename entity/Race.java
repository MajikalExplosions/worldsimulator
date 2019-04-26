
package worldsimulator.entity;

import java.util.ArrayList;
import java.util.Random;
import worldsimulator.util.CMath;
import worldsimulator.World;
import worldsimulator.Constants;
import worldsimulator.terrain.Settlement;

public class Race {
    private int id;
    
    private String name;
    private int age;
    
    private ArrayList<Culture> cultures;
    private ArrayList<Settlement> settlements;
    
    public Race(int d) {
        Random r = World.getDefaultWorld().random;
        id = d;
        settlements = new ArrayList<>();
        
        Language root = new Language();
        
        name = root.getWord();
        age = CMath.randInt(r, Constants.MIN_RACE_AGE, Constants.MAX_RACE_AGE);
        
        int cultureCount = CMath.randInt(r, Constants.CULTURE_COUNT_MIN, Constants.CULTURE_COUNT_MAX);
        cultures = new ArrayList<>();
        
        for (int i = 0; i < cultureCount; i++) {
            cultures.add(new Culture(i, root.getMutation()));
        }
    }
    
    public ArrayList<Settlement> getSettlements() {
        return settlements;
    }
    
    public void addSettlement(Settlement s) {
        settlements.add(s);
    }
    
    public int getAge() {
        return age;
    }
}
