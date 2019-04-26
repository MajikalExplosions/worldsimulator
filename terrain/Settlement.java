

package worldsimulator.terrain;

import worldsimulator.Constants;
import worldsimulator.World;
import worldsimulator.entity.Culture;
import worldsimulator.entity.Race;
import worldsimulator.util.CMath;
import worldsimulator.util.Coordinate;

/**
 *
 * @author MajikalExplosions
 */
public class Settlement {
    private String name;
    private int id;
    
    private Coordinate location;
    
    //TODO possibly add more fields for more realism?  Memory impact should be small.
    private double population;//1 = 1 person
    private double wealth;//richness of settlement; 1 = food to feed 1 pop for 1 year
    private double resources;//max supported population (food, water, etc.); 1 = 1 wealth / year
    
    private Culture culture;
    private Culture controller;
    private Race race;
    
    public Settlement(int i, Race r) {
        id = i;
        race = r;
        r.addSettlement(this);
        World.getDefaultWorld().settlements.add(this);
    }
    
    public Coordinate getLocation() {
        return location;
    }
    
    public void setLocation(Coordinate c) {
        location = c;
    }
    
    public void setDetails(int p, double w, double r) {
        population = p;
        wealth = w;
        resources = r;
    }
    
    //TODO make this more realistic
    public boolean grow() {
        //simulate growth
        double maxPopulationGrowth = population * Constants.POPULATION_GROWTH_RATIO;
        double populationCap = Constants.POPULATION_GROWTH_CAP_RATIO * (resources - population);
        double maxChildrenSupport = ((wealth - population * 3) / 2 < 0) ? (wealth - population * 3) / 4 : (wealth - population * 3) / 2;
        double income = population * Constants.POPULATION_WORKING_RATIO * CMath.randDouble(World.getDefaultWorld().random, Constants.POPULATION_PRODUCTIVITY_MIN, Constants.POPULATION_PRODUCTIVITY_MAX);
        
        double growth = Math.min(
                maxChildrenSupport,
                Math.min(maxPopulationGrowth, populationCap))
                * CMath.randDouble(World.getDefaultWorld().random, Constants.POPULATION_GROWTH_RANDOM_MIN, Constants.POPULATION_GROWTH_RANDOM_MAX);
        
        if (population < 100) {//check for dead settlements
            World.getDefaultWorld().settlements.remove(this);
            race.getSettlements().remove(this);
            return false;
        }
        
        wealth -= growth * 2 + population;
        wealth += income;
        population += growth;
        
        //city luck events
        
        //check for new city founding
        if ((population + growth) * 3 < wealth * Constants.MIGRATION_TRIGGER_THRESHOLD && population > 2 * Constants.POPULATION_NEW_MAX) {
            return true;
        }
        
        return false;
    }
    
    public void setPopulation(double n) {
        population = n;
    }
    
    public double getPopulation() {
        return population;
    }
    
    public void setWealth(double n) {
        wealth = n;
    }
    
    public double getWealth() {
        return wealth;
    }
    
    public String toString() {
        return name + "(" + id + "): " + population + " " + wealth + " " + resources;
    }
}
