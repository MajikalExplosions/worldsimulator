/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldsimulator;

import java.util.Random;

/**
 *
 * @author joseph
 */
public class TerrainGenerator {
    
    private int width, height;
    private Random rnd;
    private TerrainCell[][] map;
    private float maxDiff;
    private float standardDeviation;
    private final float DIFFERENCE = 1.5f;
    
    public TerrainGenerator(Random r, TerrainCell[][] m, int side) {
        width = (int) Math.pow(2, side) + 1;
        height = width;
        rnd = r;
        map = m;
        maxDiff = 1;
    }
    
    /**
     * Generates altitude for this terrain
     */
    public void generateAltitude() {
        
        //Four Corners
        map[0][0].safeSetAltitude((width / 2) * -1);
        map[width - 1][0].safeSetAltitude((width / 2) * -1);
        map[0][height - 1].safeSetAltitude((width / 2) * -1);
        map[width - 1][height - 1].safeSetAltitude((width / 2) * -1);
        int stepSize = width - 1;
        
        while(stepSize >= 2) {
            int mid = stepSize / 2;
            
            diamond(stepSize, mid);
            square(stepSize, mid);
            
            maxDiff /= DIFFERENCE;
            stepSize /= 2;
        }
    }
    
    /**
     * Rescales the altitude of map to range [0, 1]
     * @param map The map to rescale
     * @return Average of all map nodes after rescaling
     */
    public static float printifyAltitude(TerrainCell[][] map) {
        float min = 100, max = -100, count = 0, avg = 0;
        for (TerrainCell[] row : map) {
            for (TerrainCell cell : row) {
                if (cell.getAltitude() > max) max = cell.getAltitude();
                if (cell.getAltitude() < min) min = cell.getAltitude();
            }
        }
        float diff = max - min;
        for (TerrainCell[] row : map) {
            for (TerrainCell cell : row) {
                if (cell.getAltitude() - min < 0) System.out.println(cell.getAltitude());
                cell.setAltitude((cell.getAltitude() - min) / diff);
                count++;
                avg += cell.getAltitude();
            }
        }
        
        return avg / count;
    }
    
    /**
     * Rescales the altitude of map to parameters
     * @param min minimum value
     * @param max maximum value
     * @param map The map to rescale
     */
    public static void rescaleAltitude(float min, float max, TerrainCell[][] map) {
        printifyAltitude(map);
        
        for (TerrainCell[] row : map) {
            for (TerrainCell cell : row) {
                cell.setAltitude(cell.getAltitude() * (max - min) + min);
            }
        }
    }
    
    /**
     * Generates map of rainfall based on altitude
     */
    public void generateRainfall() {
        printifyAltitude(map);
        for (TerrainCell[] row : map) {
            for (TerrainCell cell : row) {
                cell.setRainfall((1 - cell.getAltitude()) + randomFloat() * 0.15f);
            }
        }
    }
    
    /**
     * Rescales the rainfall of map to range [0, 1]
     * @param map The map to rescale
     * @return Average of all map nodes after rescaling
     */
    public static float printifyRainfall(TerrainCell[][] map) {
        float min = 100, max = -100, count = 0, avg = 0;
        for (TerrainCell[] row : map) {
            for (TerrainCell cell : row) {
                if (cell.getRainfall() > max) max = cell.getRainfall();
                if (cell.getRainfall() < min) min = cell.getRainfall();
            }
        }
        float diff = max - min;
        for (TerrainCell[] row : map) {
            for (TerrainCell cell : row) {
                if (cell.getRainfall() - min < 0) System.out.println(cell.getRainfall());
                cell.setRainfall((cell.getRainfall() - min) / diff);
                count++;
                avg += cell.getRainfall();
                //if (cell.getAltitude() < 0) System.out.println("Oops");
            }
        }
        
        return avg / count;
    }
    
    /**
     * Rescales the rainfall of map to parameters
     * @param min minimum value
     * @param max maximum value
     * @param map The map to rescale
     */
    public static void rescaleRainfall(float min, float max, TerrainCell[][] map) {
        printifyRainfall(map);
        
        for (TerrainCell[] row : map) {
            for (TerrainCell cell : row) {
                cell.setRainfall(cell.getRainfall() * (max - min) + min);
            }
        }
    }
    
    public void generateTemperature() {
        rescaleAltitude(0, 8000, map);//0-8000m as in mount everest
        printifyRainfall(map);
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                map[x][y].setTemperature(y, map[0].length, 25);
            }
        }
        printifyAltitude(map);
    }
    
    /**
     * Rescales the temperature of map to range [0, 1]
     * @param map The map to rescale
     * @return Average of all map nodes after rescaling
     */
    public static float printifyTemperature(TerrainCell[][] map) {
        float min = 100, max = -100, count = 0, avg = 0;
        for (TerrainCell[] row : map) {
            for (TerrainCell cell : row) {
                if (cell.getTemperature() > max) max = cell.getTemperature();
                if (cell.getTemperature() < min) min = cell.getTemperature();
            }
        }
        float diff = max - min;
        for (TerrainCell[] row : map) {
            for (TerrainCell cell : row) {
                cell.setTemperature((cell.getTemperature()- min) / diff);
                count++;
                avg += cell.getTemperature();
            }
        }
        
        return avg / count;
    }
    
    private void diamond(int stepSize, int mid) {
        for (int x = 0; x < height - 1; x += stepSize) {
            for (int y = 0; y < width - 1; y += stepSize) {
                float avg = (map[x][y].getAltitude() + map[x + stepSize][y].getAltitude() + map[x][y + stepSize].getAltitude() + map[x + stepSize][y + stepSize].getAltitude()) / 4;
                map[x + mid][y + mid].safeSetAltitude(avg + randomFloat() * maxDiff);
            }
        }
    }
    
    private void square(int stepSize, int mid) {
        for (int x = 0; x < height - 1; x += stepSize) {
            for (int y = 0; y < width - 1; y += stepSize) {
                int midX = x + mid, midY = y + mid;
                float avg;
                //S
                avg = (map[x][y].getAltitude() + map[midX][midY].getAltitude() + map[x + stepSize][y].getAltitude()) / 3;
                map[midX][y].safeSetAltitude(avg + randomFloat() * maxDiff);
                
                //E
                avg = (map[x + stepSize][y].getAltitude() + map[midX][midY].getAltitude() + map[x + stepSize][y + stepSize].getAltitude()) / 3;
                map[x + stepSize][midY].safeSetAltitude(avg + randomFloat() * maxDiff);
                
                //N
                avg = (map[x + stepSize][y + stepSize].getAltitude() + map[midX][midY].getAltitude() + map[x][y + stepSize].getAltitude()) / 3;
                map[midX][y + stepSize].safeSetAltitude(avg + randomFloat() * maxDiff);
                
                //W
                avg = (map[x][y].getAltitude() + map[midX][midY].getAltitude() + map[x][y + stepSize].getAltitude()) / 3;
                map[x][midY].safeSetAltitude(avg + randomFloat() * maxDiff);
                
            }
        }
    }
    
    private float randomFloat() {
        return rnd.nextFloat() - 0.5f;
    }
}