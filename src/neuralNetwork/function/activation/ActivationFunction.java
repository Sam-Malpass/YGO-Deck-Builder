/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.function.activation;
public interface ActivationFunction {
    /**
     * Function definition for calculateOutput()
     * <p>
     *     Makes a calculation based on the sum of input neurons output
     * </p>
     * @param summedInput is the neuron's sum of outputs for inputs of the connected neuron
     * @return output's calculation based on the sum of inputs
     */
    double calculateOutput(double summedInput);
    /**
     * Function definition for calculateDerivative()
     * <p>
     *     Calculates a function's derivative
     * </p>
     * @param totalInput is the neuron's total input
     * @return function's derivative
     */
    double calculateDerivative(double totalInput);
}
