
package worldsimulator;

import worldsimulator.util.CMath;
import java.util.ArrayList;
import worldsimulator.terrain.Terrain;
import java.util.Random;
import worldsimulator.entity.Race;
import worldsimulator.terrain.Settlement;
import worldsimulator.util.Coordinate;

public class World {
    
    public Random random;
    
    public Terrain terrain;
    
    public ArrayList<Race> races;
    public ArrayList<Settlement> settlements;
    
    private static ArrayList<World> worlds = new ArrayList<>();
    private static int defaultWorldId = 0;
    
    public World(long seed) {
        random = new Random(seed);
        worlds.add(this);
        
        terrain = new Terrain();
        
        races = new ArrayList<>();
        settlements = new ArrayList<>();
        int numRace = CMath.randInt(random, Constants.RACE_COUNT_MIN, Constants.RACE_COUNT_MAX);
        for (int i = 0; i < numRace; i++) races.add(new Race(i));
    }
    
    public World() {
        this(System.currentTimeMillis());
    }
    
    public static World getDefaultWorld() {
        if (worlds.size() <= defaultWorldId) return null;
        return worlds.get(defaultWorldId);
    }
    
    public void populate() {
        
        //Terrain, races, etc. are already generated.
        for (int year = 0; year < Constants.MAX_RACE_AGE; year++) {
            for (Race race : races) {
                if (race.getAge() < Constants.MAX_RACE_AGE - year) continue;
                
                if (race.getAge() == Constants.MAX_RACE_AGE - year) {
                    //Create 1st settlement
                    double x = 0, y = 0;
                    boolean flag = true;
                    while (flag) {
                        if (terrain.getHeightAt(x, y) <= Constants.WATER_LEVEL || terrain.getHeightAt(x, y) >= Constants.MOUNTAIN_LEVEL) {
                            x = CMath.randDouble(random, 0, Constants.MAP_SIZE);
                            y = CMath.randDouble(random, 0, Constants.MAP_SIZE);
                            continue;
                        }
                        boolean flag2 = true;
                        for (Settlement s : settlements) {
                            if (CMath.distanceBetween(s.getLocation(), new Coordinate(x, y)) < Constants.SETTLEMENT_DISTANCE_MIN) {
                                flag2 = false;//Too close.
                                x = CMath.randDouble(World.getDefaultWorld().random, 0, Constants.MAP_SIZE);
                                y = CMath.randDouble(World.getDefaultWorld().random, 0, Constants.MAP_SIZE);
                                break;
                            }
                        }
                        if (flag2) {
                            flag = false;
                            
                            Settlement s = new Settlement(settlements.size(), race);
                            
                            s.setLocation(new Coordinate(x, y));
                            int pop = CMath.randInt(random, Constants.POPULATION_INIT_MIN, Constants.POPULATION_INIT_MAX);
                            double temp = terrain.getResources(x, y);
                            s.setDetails(pop, pop * Constants.WEALTH_INIT_MODIFIER * temp / Constants.RESOURCE_AVERAGE_VALUE, temp);
                        }
                    }
                }
                else {
                    ArrayList<Settlement> raceSettlements = new ArrayList(race.getSettlements());
                    for (Settlement s : raceSettlements) {
                        if (s.grow()) {
                            //found new city!
                            double x = 0, y = 0;
                            boolean flag = true;
                            while (flag) {
                                if (World.getDefaultWorld().terrain.getHeightAt(x, y) <= Constants.WATER_LEVEL || World.getDefaultWorld().terrain.getHeightAt(x, y) >= Constants.MOUNTAIN_LEVEL) {
                                    x = CMath.randDouble(World.getDefaultWorld().random, 0, Constants.MAP_SIZE);
                                    y = CMath.randDouble(World.getDefaultWorld().random, 0, Constants.MAP_SIZE);
                                    continue;
                                }
                                boolean flag2 = true;
                                for (Settlement s2 : settlements) {
                                    if (CMath.distanceBetween(s2.getLocation(), new Coordinate(x, y)) < Constants.SETTLEMENT_DISTANCE_MIN) {
                                        flag2 = false;//Too close.
                                        x = CMath.randDouble(World.getDefaultWorld().random, 0, Constants.MAP_SIZE);
                                        y = CMath.randDouble(World.getDefaultWorld().random, 0, Constants.MAP_SIZE);
                                        break;
                                    }
                                }
                                if (flag2) {
                                    flag = false;

                                    Settlement s2 = new Settlement(World.getDefaultWorld().settlements.size(), race);

                                    s2.setLocation(new Coordinate(x, y));
                                    int pop = CMath.randInt(World.getDefaultWorld().random, Constants.POPULATION_NEW_MIN, Constants.POPULATION_NEW_MAX);
                                    s.setPopulation(s.getPopulation() - pop);
                                    double temp = World.getDefaultWorld().terrain.getResources(x, y);
                                    s.setWealth(s.getWealth() - pop * Constants.WEALTH_INIT_MODIFIER * temp / Constants.RESOURCE_AVERAGE_VALUE * 4);
                                    s2.setDetails(pop, pop * Constants.WEALTH_INIT_MODIFIER * temp / Constants.RESOURCE_AVERAGE_VALUE, temp);
                                    System.out.println(settlements.size());
                                }
                            }
                        }
                    }
                    //country-wide luck events
                    //simulate warfare
                }
            }
        }
    }
}
