

package worldsimulator;

/**
 *
 * @author MajikalExplosions
 */
public class Constants {
    
    public static final int MAP_SIZE = 100;//width and height of map where 1 = 1km
    public static final double WATER_LEVEL = 0.45d;
    
    public static final int NOISE_OCTAVE_COUNT = 8;
    public static final double NOISE_OCTAVE_FREQUENCY_FACTOR = 2d;
    public static final double NOISE_OCTAVE_FREQUENCY_BASE = 1d / 32d;//Map scaling (the larger the more hilly the terrain)
    public static final double NOISE_OCTAVE_AMPLITUDE_FACTOR = 1d / 2d;
    public static final double NOISE_OCTAVE_Z_COORDINATE_BASE = 1000d;
    public static final double NOISE_OCTAVE_Z_COORDINATE_FACTOR = 100d;
    
    //Math
    public static final double SQRT_TWO = 1.41421356237;
}
