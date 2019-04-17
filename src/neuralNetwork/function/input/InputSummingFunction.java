/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.function.input;
import java.util.List;
public interface InputSummingFunction {
    /**
     * Function definition for collectOutput()
     * <p>
     *     Makes calculations based on the output values of the input neurons
     * </p>
     * @param inputConnections are the neuron's input connections
     * @return the total input for the neuron with the input connections
     */
    double collectOutput(List<Connection> inputConnections);
}
