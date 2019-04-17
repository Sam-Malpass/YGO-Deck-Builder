/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.function.activation;
public class SigmoidActivation implements ActivationFunction {
    /**
     * Constructor with no arguments
     * <p>
     *     Creates an object
     * </p>
     */
    public SigmoidActivation() {
    }
    /**
     * Function definition for calculateOutput()
     * <p>
     *     Overrides the calculateOutput function
     * </p>
     * @param summedInput is the neuron's sum of outputs for inputs of the connected neuron
     * @return 1d over the calculated denominator
     */
    @Override
    public double calculateOutput(double summedInput) {
        double denominator = 1 + Math.exp(-1d * summedInput);

        return (1d / denominator);
    }
    /**
     * Function definition for calculateDerivative()
     * <p>
     *     Overrides the calculateDerivative function
     * </p>
     * @param input is the neuron's total input
     * @return the calculated derivative
     */
    @Override
    public double calculateDerivative(double input) {
        return calculateOutput(input) * (1 - calculateOutput(input));
    }
}
