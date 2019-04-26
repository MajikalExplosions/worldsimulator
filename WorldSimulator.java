
package worldsimulator;

import worldsimulator.entity.Language;
import worldsimulator.ui.UIManager;

public class WorldSimulator {
    
    public static void main(String[] args) {
        World world = new World();
        UIManager ui = new UIManager();
        world.populate();
    }
}
