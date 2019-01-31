package worldsimulator;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class Terrain {
    //Map of earth would be 40k * 20k km
    //My map will be 32768 * 16384 km I guess
    //8*8 km squares
    //HOI4 is 20km * 20 km in europe, I *could* shrink it in half again but whatevs
    private TerrainCell[][] terrainCells;
    private Random rnd;
    private int side;
    
    public Terrain(Random r, int s) { 
       rnd = r;
       side = s;
    }
    
    public void generate() {
        
        //Create ultra-large array 
        terrainCells = new TerrainCell[(int) Math.pow(2, side) + 1][(int)Math.pow(2, side) + 1];
        for (int i = 0; i < terrainCells.length; i++) {
            terrainCells[i] = new TerrainCell[(int)Math.pow(2, side) + 1];
            for (int j = 0; j < terrainCells[0].length; j++) {
                terrainCells[i][j] = new TerrainCell();
            }
        }
        
        System.out.println("[B] Generating Altitudes...");
        TerrainGenerator terrainGenerator = new TerrainGenerator(rnd, terrainCells, side);
        terrainGenerator.generateAltitude();
        
        System.out.println("[C] Rescaling Altitudes...");
        TerrainGenerator.printifyAltitude(terrainCells);
        
        System.out.println("[B] Generating Rainfall...");
        terrainGenerator.generateRainfall();
        
        System.out.println("[C] Rescaling Rainfall...");
        TerrainGenerator.printifyRainfall(terrainCells);
        
        System.out.println("[B] Generating Temperature...");
        terrainGenerator.generateTemperature();
        
        System.out.println("[C] Rescaling Temperature...");
        TerrainGenerator.printifyTemperature(terrainCells);
        
        System.out.println("[B] Outputting results to PNG...");
        System.out.println("[C] Drawing PNG with Altitudes...");
        CustomImage ci = new CustomImage((int) Math.pow(2, side) + 1, (int)Math.pow(2, side) + 1);
        for (int i = 0; i < terrainCells.length; i++) {
            for (int j = 0; j < terrainCells[0].length; j++) {
                ci.setPixelBW(i, j, (int) ((terrainCells[i][j].getAltitude()) * 255));
            }
        }
        ci.writeToFile("altitude");
        
        System.out.println("[C] Drawing PNG with Rainfall...");
        CustomImage ci2 = new CustomImage((int) Math.pow(2, side) + 1, (int)Math.pow(2, side) + 1);
        for (int i = 0; i < terrainCells.length; i++) {
            for (int j = 0; j < terrainCells[0].length; j++) {
                ci2.setPixelBW(i, j, (int) ((terrainCells[i][j].getRainfall()) * 255));
            }
        }
        ci2.writeToFile("rainfall");
        
        System.out.println("[C] Drawing PNG with Temperature...");
        CustomImage ci3 = new CustomImage((int) Math.pow(2, side) + 1, (int)Math.pow(2, side) + 1);
        for (int i = 0; i < terrainCells.length; i++) {
            for (int j = 0; j < terrainCells[0].length; j++) {
                ci3.setPixelBW(i, j, (int) ((terrainCells[i][j].getTemperature()) * 255));
            }
        }
        ci3.writeToFile("temperature");
        
        System.out.println("[C] Drawing PNG with Coastline...");
        CustomImage ci4 = new CustomImage((int) Math.pow(2, side) + 1, (int)Math.pow(2, side) + 1);
        for (int i = 0; i < terrainCells.length; i++) {
            for (int j = 0; j < terrainCells[0].length; j++) {
                if (terrainCells[i][j].getAltitude() < 0.6) ci4.setPixelBW(i, j, 0);
                else ci4.setPixelBW(i, j, 255);
            }
        }
        ci4.writeToFile("coastline");
    }
    
    public TerrainCell[][] getMap() {
        return terrainCells;
    }
    
    public int getSide() {
        return side;
    }
}
