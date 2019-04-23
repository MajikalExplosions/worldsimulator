
package worldsimulator.entity;

import java.util.Random;

public class Language {
    
    private static String allVowels = "aeiou";
    private static String singleConsts = "bcdfghjklmnpqrstvwxyz";//21 consts
    private static String multiConsts = "ckthphchllshzhsskhtsghdhkrngdwnwswndpl";//Every two, 19 consts
    
    private String[] vowels;
    private String[] consonants;
    private String[] endings;
    private double[] vWeights;
    private double[] cWeights;
    private double[] eWeights;
    private Random rnd;
    
    public Language(Random r) {
        rnd = r;
        generate(allVowels, singleConsts, multiConsts);
    }
    
    public Language(Random r, String[] v, String[] c, String[] e, double[] vW, double[] cW, double[] eW) {
        rnd = r;
        vowels = v;
        consonants = c;
        endings = e;
        vWeights = vW;
        cWeights = cW;
        eWeights = eW;
    }
    
    private void generate(String v, String s, String m) {
        
        vowels = new String[allVowels.length()];
        for (int i = 0; i < allVowels.length(); i++) vowels[i] = String.valueOf(allVowels.charAt(i));
        
        vWeights = new double[vowels.length];
        for (int i = 0; i < vowels.length; i++) vWeights[i] = rnd.nextDouble() * 19 + 1;
        
        String[] aC = new String[singleConsts.length() + multiConsts.length() / 2];
        
        for (int i = 0; i < aC.length; i++) {
            if (i < singleConsts.length()) aC[i] = String.valueOf(singleConsts.charAt(i));
            else aC[i] = multiConsts.substring((i - singleConsts.length()) * 2, (i - singleConsts.length()) * 2 + 2);
        }
        
        for (int i = 0; i < aC.length * 10; i++) {
            int x1 = rnd.nextInt(aC.length), x2 = rnd.nextInt(aC.length);
            String t = aC[x1];
            aC[x1] = aC[x2];
            aC[x2] = t;
        }
        
        int cL = rnd.nextInt(12) + 12;//12-24
        
        consonants = new String[cL];
        
        for (int i = 0; i < cL; i++) consonants[i] = aC[i];
        cWeights = new double[consonants.length];
        for (int i = 0; i < consonants.length; i++) cWeights[i] = rnd.nextDouble() * 90 + 10;
        
        
        endings = new String[rnd.nextInt(3) + 4];//4-7
        eWeights = new double[endings.length];
        
        for (int i = 0; i < endings.length; i++) {
            if (rnd.nextBoolean()) {
                endings[i] = randomChar(vowels, vWeights);
            }
            else {
                endings[i] = randomChar(consonants, cWeights);
            }
            eWeights[i] = rnd.nextDouble() * 75 + 25;
        }
    }
    
    public Language mutate() {
        String[] nV = new String[vowels.length];
        String[] nC = new String[consonants.length];
        String[] nE = new String[endings.length];
        double[] nVw = new double[vowels.length];
        double[] nCw = new double[consonants.length];
        double[] nEw = new double[endings.length];
        
        for (int i = 0; i < vWeights.length; i++) {
            nVw[i] = vWeights[i] * rnd.nextDouble() / 4 + 0.875;
        }
        
        for (int i = 0; i < cWeights.length; i++) {
            nCw[i] = cWeights[i] * rnd.nextDouble() / 4 + 0.875;
        }
        
        for (int i = 0; i < eWeights.length; i++) {
            nEw[i] = eWeights[i] * rnd.nextDouble() / 4 + 0.875;
        }
        
        for (int i = 0; i < vowels.length; i++) {
            nV[i] = new String(vowels[i]);
        }
        
        for (int i = 0; i < consonants.length; i++) {
            nC[i] = new String(consonants[i]);
        }
        
        for (int i = 0; i < endings.length; i++) {
            nE[i] = new String(endings[i]);
        }
        
        return new Language(rnd, nV, nC, nE, nVw, nCw, nEw);
    }
    
    public String generateWord() {
        String n = "";
        
        int length = rnd.nextInt(4) + 4;//4-8
        
        int lastV = 0, lastC = 0;
        while(n.length() < length) {
            if (lastC >= 2) {lastV++; n += randomChar(consonants, cWeights); lastC = 0;}
            if (lastV >= 1) { n += randomChar(vowels, vWeights); lastV = 0; lastC++; continue; }
            if (rnd.nextDouble() < 0.3) { n += randomChar(vowels, vWeights); lastV = 0; lastC++; continue; }
            lastV++;
            n += randomChar(consonants, cWeights);
            lastC = 0;
        }
        
        if (lastV != 1 && lastV != 0) n = n.substring(0, n.length() - 1);
        
        n += randomChar(endings, eWeights);
        
        return n;
    }
    
    private String randomChar(String[] values, double[] weights) {
        double total = 0;
        
        for (int i = 0; i < weights.length; i++) total += weights[i];
        
        double r = rnd.nextDouble() * total;
        int i = 0;
        while(r > 0 && i < values.length) {
            r -= weights[i];
            i++;
        }
        return values[i - 1];
    }
}
