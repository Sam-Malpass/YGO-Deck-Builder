/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.function.activation;
public class IdentityActivation implements ActivationFunction {
    /**
     * Function definition for calculateDerivative()
     * <p>
     *     Overrides the calculateDerivative function
     * </p>
     * @param totalInput is the neuron's total input
     * @return the totalInput
     */
    @Override
    public double calculateDerivative(double totalInput) {
        return totalInput;
    }
    /**
     * Function definition for calculateOutput()
     * <p>
     *     Overrides the calculateOutput function
     * </p>
     * @param summedInput is the neuron's sum of outputs for inputs of the connected neuron
     * @return 1d
     */
    @Override
    public double calculateOutput(double summedInput) {
        return 1d;
    }
}
