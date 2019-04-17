/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.mlp;
import java.util.Random;
import java.util.UUID;
public class Randomizer {
    /**
     * Holds the Random object
     */
    private static final Random random = new Random();
    /**
     * Holds the weight multiplier
     */
    private static final int randomWeightMultiplier = 1;
    /**
     * Constructor with no arguments
     * <p>
     *      Creates the object
     * </p>
     */
    private Randomizer() {
        throw new AssertionError();
    }
    /**
     * Function definition for generateId()
     * <p>
     *     Generates a random ID
     * </p>
     * @return the ID
     */
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
    /**
     * Function definition for getRandom()
     * <p>
     *     Returns a random double
     * </p>
     * @return a random double
     */
    public static double getRandom() {
        double randomValue = randomWeightMultiplier * (random.nextDouble() * 2 - 1); // [-1;1]
        return randomValue;
    }
}
