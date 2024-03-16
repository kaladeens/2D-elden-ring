package game.Utils;

import java.util.Random;

/**
 * A random number generator
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Sam Abdi
 *
 */
public class RandomNumberGenerator {
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }

    /**
     * Random Boolean generator
     * @param bound number limitation
     * @return Boolean output
     */
    public static boolean getBool(int bound){
        int random=new Random().nextInt(100);
        return random<=bound;
    }

    /**
     * Random Integer generator
     * @param lowerBound Lower bound
     * @param upperBound Upper bound
     * @return Random Number between Lower and Upper Bound
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }
}
