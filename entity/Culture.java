
package worldsimulator.entity;

import java.util.ArrayList;
import worldsimulator.terrain.Settlement;

public class Culture {
    private int id;
    private Language language;
    private String name;
    private ArrayList<Settlement> settlements;
    
    public Culture(int i, Language l) {
        id = i;
        language = l;
        name = language.getWord();
        settlements = new ArrayList<>();
    }
    
    public String getName() {
        return name;
    }
    
    public Language getLanguage() {
        return language;
    }
}
