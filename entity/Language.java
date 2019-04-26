
package worldsimulator.entity;

import java.util.ArrayList;
import java.util.Random;
import worldsimulator.util.CMath;
import worldsimulator.Constants;
import worldsimulator.World;

public class Language {
    private ArrayList<LanguageEntry> vowelEntries;
    private ArrayList<LanguageEntry> consonantEntries;
    
    public Language() {
        this(true);
    }
    
    public Language(boolean generate) {
        vowelEntries = new ArrayList<>();
        consonantEntries = new ArrayList<>();
        if (generate) generateFromScratch();
    }
    
    public String getWord() {
        String n = "";
        
        int length = CMath.randInt(World.getDefaultWorld().random, Constants.WORD_MIN_LENGTH, Constants.WORD_MAX_LENGTH);
        
        int lastV = 0, lastC = 0;
        while(n.length() < length) {
            if (lastC >= 1) {lastV++; n += randomEntry(consonantEntries); lastC = 0;}
            if (lastV >= 1) { n += randomEntry(vowelEntries); lastV = 0; lastC++; continue; }
            if (World.getDefaultWorld().random.nextDouble() < Constants.CHAR_VOWEL_PROBABILITY) { n += randomEntry(vowelEntries); lastV = 0; lastC++; continue; }
            lastV++;
            n += randomEntry(consonantEntries);
            lastC = 0;
        }
        
        if (lastV != 1 && lastV != 0) n = n.substring(0, n.length() - 1);
        
        return n.substring(0, 1).toUpperCase() + n.substring(1);
    }
    
    public Language getMutation() {
        Language l = new Language(false);
        for (LanguageEntry v : vowelEntries) l.vowelEntries.add(new LanguageEntry(v.value, v.weight * CMath.randDouble(World.getDefaultWorld().random, 1 - Constants.ENTRY_MUTATION_MAX_DIFFERENCE, 1 + Constants.ENTRY_MUTATION_MAX_DIFFERENCE)));
        for (LanguageEntry c : consonantEntries) l.consonantEntries.add(new LanguageEntry(c.value, c.weight * CMath.randDouble(World.getDefaultWorld().random, 1 - Constants.ENTRY_MUTATION_MAX_DIFFERENCE, 1 + Constants.ENTRY_MUTATION_MAX_DIFFERENCE)));
        return l;
    }
    
    private void generateFromScratch() {
        Random r = World.getDefaultWorld().random;
        
        ArrayList<String> vowels = new ArrayList<>();
        ArrayList<String> consonants = new ArrayList<>();
        
        int cC = CMath.randInt(r, (int)((double)Constants.ALL_CONSONANTS.length * Constants.LANGUAGE_MIN_CHAR_RATIO), (int)((double)Constants.ALL_CONSONANTS.length * Constants.LANGUAGE_MAX_CHAR_RATIO));
        int cV = CMath.randInt(r, (int)((double)Constants.ALL_VOWELS.length * Constants.LANGUAGE_MIN_CHAR_RATIO), (int)((double)Constants.ALL_VOWELS.length * Constants.LANGUAGE_MAX_CHAR_RATIO));
        
        for (int i = 0; i < cC; i++) {
            String s = Constants.ALL_CONSONANTS[r.nextInt(Constants.ALL_CONSONANTS.length)];
            if (! consonants.contains(s)) {
                consonants.add(s);
                continue;
            }
            i--;
        }
        
        for (int i = 0; i < cV; i++) {
            String s = Constants.ALL_VOWELS[r.nextInt(Constants.ALL_VOWELS.length)];
            if (! vowels.contains(s)) {
                vowels.add(s);
                continue;
            }
            i--;
        }
        
        for (String c : consonants) {
            consonantEntries.add(new LanguageEntry(c, Math.pow(CMath.randDouble(r, Constants.ENTRY_WEIGHT_MIN_BASE, Constants.ENTRY_WEIGHT_MAX_BASE), Constants.ENTRY_WEIGHT_EXPONENT)));
        }
        
        for (String v : vowels) {
            vowelEntries.add(new LanguageEntry(v, Math.pow(CMath.randDouble(r, Constants.ENTRY_WEIGHT_MIN_BASE, Constants.ENTRY_WEIGHT_MAX_BASE), Constants.ENTRY_WEIGHT_EXPONENT)));
        }
    }
    
    private String randomEntry(ArrayList<LanguageEntry> entries) {
        double total = 0;
        
        for (int i = 0; i < entries.size(); i++) total += entries.get(i).weight;
        
        double r = World.getDefaultWorld().random.nextDouble() * total;
        int i = 0;
        while(r > 0 && i < entries.size()) {
            r -= entries.get(i++).weight;
        }
        return entries.get(i - 1).value;
    }
    
    public class LanguageEntry {
        public String value;
        public double weight;

        public LanguageEntry() {
            this("", 0);
        }

        public LanguageEntry(String val) {
            this(val,0);
        }

        public LanguageEntry(String val, double w) {
            value = val;
            weight = w;
        }

        public String toString() {
            return value + " (" + weight + ")";
        }
    }
}
