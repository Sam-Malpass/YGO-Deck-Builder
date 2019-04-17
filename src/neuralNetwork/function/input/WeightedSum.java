/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.function.input;
import java.util.List;
public class WeightedSum {
    /**
     * Function definition for collectOutput()
     * <p>
     *     Returns the sum of all the weighted inputs
     * </p>
     * @param inputConnections are the neuron's input connections
     * @return the weightedSum
     */
    @Override
    public double collectOutput(List<Connection> inputConnections) {
        double weightedSum = 0d;
        for (Connection connection : inputConnections) {
            weightedSum += connection.getWeightedInput();
        }
        return weightedSum;
    }
}
