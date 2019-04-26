

package worldsimulator;

/**
 *
 * @author MajikalExplosions
 */
public class Constants {
    
    //Information
    public static final String VERSION_NUMBER = "";
    public static final String VERSION_NAME = "";
    
    
    //Terrain
    public static final int MAP_SIZE = 100;//width and height of map where 1 = 1km
    public static final double WATER_LEVEL = 0.35d;
    public static final double MOUNTAIN_LEVEL = 0.6d;//1d = 4km
    
    public static final int NOISE_OCTAVE_COUNT = 8;
    public static final double NOISE_OCTAVE_FREQUENCY_FACTOR = 2d;
    public static final double NOISE_OCTAVE_FREQUENCY_BASE = 1d / 32d;//Map scaling (the larger the more hilly the terrain)
    public static final double NOISE_OCTAVE_AMPLITUDE_FACTOR = 1d / 2d;
    public static final double NOISE_OCTAVE_Z_COORDINATE_BASE = 1000d;
    public static final double NOISE_OCTAVE_Z_COORDINATE_FACTOR = 100d;
    
    public static final double NOISE_OCTAVE_RICHNESS = 2500d;
    public static final double NOISE_RESOURCE_MODIFIER = 0.25d;
    public static final double STEEPNESS_ADJ_RESOURCE_MODIFIER = 0.25d;
    public static final double STEEPNESS_LOCATION_OFFSET = 0.5d;
    public static final double RESOURCE_AVERAGE_VALUE = 25000d;
    
    //NPCs
    public static final int CULTURE_COUNT_MIN = 3;
    public static final int CULTURE_COUNT_MAX = 7;
    
    public static final int RACE_COUNT_MIN = 1;
    public static final int RACE_COUNT_MAX = 1;
    
    public static final int MIN_RACE_AGE = 1000;//In years
    public static final int MAX_RACE_AGE = 2000;//In years
    
    public static final double SETTLEMENT_DISTANCE_MIN = 1.5d;
    
    public static final double MIGRATION_DISTANCE_MIN = SETTLEMENT_DISTANCE_MIN;
    public static final double MIGRATION_DISTANCE_MAX = SETTLEMENT_DISTANCE_MIN * 4;
    public static final double MIGRATION_TRIGGER_WEALTH_RATIO_THRESHOLD = 1.25d;
    public static final double MIGRATION_TRIGGER_THRESHOLD = 0.5d;
    
    public static final int POPULATION_INIT_MIN = 5000;
    public static final int POPULATION_INIT_MAX = 10000;
    public static final int POPULATION_NEW_MIN = 500;
    public static final int POPULATION_NEW_MAX = 1000;
    public static final double WEALTH_INIT_MODIFIER = 4d;
    
    public static final double POPULATION_GROWTH_RATIO = 0.1d;
    public static final double POPULATION_GROWTH_RANDOM_MIN = 0.05d;
    public static final double POPULATION_GROWTH_RANDOM_MAX = 0.30d;
    public static final double POPULATION_GROWTH_CAP_RATIO = 0.5d;
    public static final double POPULATION_WORKING_RATIO = 0.35d;
    public static final double POPULATION_PRODUCTIVITY_MIN = 2.0d;
    public static final double POPULATION_PRODUCTIVITY_MAX = 3.8d;
    
    
    //Math
    public static final double SQRT_TWO = 1.41421356237d;
    
    
    //Language
    public static final String[] ALL_VOWELS = {"a", "e", "i", "o", "u", "ai", "ay", "he", "in",
        "er", "an", "re", "at", "on", "ha", "es", "en", "ed", "to", "it", "hi", "is",
        "or", "ti", "as", "te", "et", "of", "al", "de", "se", "le", "sa", "si", "ar", "ve", "ra",
        "ci", "aa",  "ee", "ea", "ie", "oa", "oe", "ue", "ui", "oo", "ij", "eu", "qu", "ei", "oj",
        "ej", "aj", "eh", "au", "ou"};
    
    public static final String[] ALL_CONSONANTS = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m",
        "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z", "b", "c", "d", "f", "g", "h", "j", "k",
        "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z", "th", "nd", "nt", "st", "ng",
        "ld", "ur", "ch", "ph", "ck", "ll", "sh", "zh", "ss", "kh", "ts", "gh", "dh", "kr", "dd", "ff",
        "rh", "cs", "dz", "gy", "ly", "ny", "sz", "ty", "zs", "sj", "sk", "kj", "gn", "sc", "gl",
        "nh", "lh", "gk", "rk"};
    
    public static final int WORD_MIN_LENGTH = 4;
    public static final int WORD_MAX_LENGTH = 9;
    public static final double CHAR_VOWEL_PROBABILITY = 0.25d;
    
    public static final double ENTRY_MUTATION_MAX_DIFFERENCE = 0.15d;
    
    public static final double LANGUAGE_MIN_CHAR_RATIO = 1d / 5d;
    public static final double LANGUAGE_MAX_CHAR_RATIO = 1d / 3d;
    
    public static final double ENTRY_WEIGHT_MIN_BASE = 0.3d;
    public static final double ENTRY_WEIGHT_MAX_BASE = 2d;
    public static final double ENTRY_WEIGHT_EXPONENT = 6d;
}
