

package worldsimulator.util;

import java.util.Random;

/**
 *
 * @author MajikalExplosions
 */
public class CMath {
    public static int randInt(Random r, int min, int max) {
        if (min == max) return min;
        if (min > max) throw new IllegalArgumentException();
        return r.nextInt(max - min) + min;
    }
    
    public static double randDouble(Random r, double min, double max) {
        if (min == max) return min;
        if (min > max) throw new IllegalArgumentException();
        return (max - min) * r.nextDouble() + min;
    }
    
    public static double distanceBetween(Coordinate a, Coordinate b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}
