package worldsimulator.terrain;

import worldsimulator.io.CustomImage;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import worldsimulator.Constants;
import worldsimulator.World;


public class Terrain {
    //map size 100*100km
    //Generated using simplexnoise
    private OpenSimplexNoise noise;
    
    public Terrain() {
        noise = new OpenSimplexNoise(World.getDefaultWorld().random.nextLong());
    } 
   
    public double getHeightAt(double x, double y) {
        double val = 0;
        
        for (int i = 0; i < Constants.NOISE_OCTAVE_COUNT; i++) {
            val += get(x * Math.pow(Constants.NOISE_OCTAVE_FREQUENCY_FACTOR, i + 1) * Constants.NOISE_OCTAVE_FREQUENCY_BASE,
                    y * Math.pow(Constants.NOISE_OCTAVE_FREQUENCY_FACTOR, i + 1) * Constants.NOISE_OCTAVE_FREQUENCY_BASE,
                    Constants.NOISE_OCTAVE_Z_COORDINATE_BASE + Constants.NOISE_OCTAVE_Z_COORDINATE_FACTOR * (double)i)
                    * Math.pow(Constants.NOISE_OCTAVE_AMPLITUDE_FACTOR, i + 1);
        }
        
       val = Math.pow((val - Constants.WATER_LEVEL + 0.025d), 3) + 0.3 * (val - Constants.WATER_LEVEL + 0.025d) + Constants.WATER_LEVEL + 0.025d;
        
        //System.out.println(v1 + " " + v2);
        return islandize(val, x, y);
    }
    
    public double getResources(double x, double y) {
        //TODO make this more accurate(inc. rainfall, altitude, etc.)
        double val = get(x, y, Constants.NOISE_OCTAVE_RICHNESS) * Constants.NOISE_RESOURCE_MODIFIER + (1d - Constants.NOISE_RESOURCE_MODIFIER / 2d);
        
        double alt = getHeightAt(x, y),
                altN = getHeightAt(x + Constants.STEEPNESS_LOCATION_OFFSET, y),
                altS = getHeightAt(x - Constants.STEEPNESS_LOCATION_OFFSET, y),
                altW = getHeightAt(x, y - Constants.STEEPNESS_LOCATION_OFFSET),
                altE = getHeightAt(x, y + Constants.STEEPNESS_LOCATION_OFFSET);
        
        double steepness = Math.abs(alt - altN) + Math.abs(alt - altS) + Math.abs(alt - altN) + Math.abs(alt - altW);
        steepness /= 4;
        val *= Math.pow(17500d * Math.pow(steepness - 0.005d, 2) + 0.9d, 2d);
        val *= Constants.RESOURCE_AVERAGE_VALUE;
        return val;
    }
    
    //TODO finish this function
    public double getRainfall(int x, int y) {
        return -1;
    }
    
    private double get(double x, double y, double z) {
        return (noise.eval(x, y, z) + 1d) / 2d;
    }
    
    /**
     * Filter to make the noise roughly round
     * @param height Simplex Noise value
     * @param x X coord
     * @param y Y coord
     * @return The changed noise value
     */
    private double islandize(double height, double x, double y) {
        double distFromCenter = Math.sqrt(Math.pow(x - (double)Constants.MAP_SIZE / 2d, 2) + Math.pow(y - (double)Constants.MAP_SIZE / 2d, 2));
        //System.out.println(distFromCenter);
        //Rescale distFromCenter to (0, 1)
        distFromCenter /= (double)Constants.MAP_SIZE / 2d * Constants.SQRT_TWO;
        
        //System.out.println(x + " " + y + " " + distFromCenter);
        return Math.max(0, height * (-1 * 1.25 * Math.pow(distFromCenter, 2) + 1));//-1.25x^2 + 1
    }
    
    /**
     * Linearly interpolates between two values
     * @param min Smaller value
     * @param max Larger value
     * @param val Value to interpolate
     * @return 
     */
    private double lerp(double min, double max, double val) {
        return min + val * (max - min);
    }
}
