/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.mlp;
import neuralNetwork.function.activation.ActivationFunction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Neuron implements Processor {
    /**
     * Holds the identifier
     */
    private final String id;
    /**
     * Holder the output of the neuron
     */
    private double output;
    /**
     * Holds the bias value
     */
    private final double bias = 1;
    /**
     * Holds the bias connection
     */
    private Connection biasConnection;
    /**
     * Holds a list of input connections
     */
    private List<Connection> inputConnections = new ArrayList<>();
    /**
     * Holds a map of connections
     */
    private Map<String, Connection> connectionLookup = new HashMap<>();
    /**
     * Holds the activation function
     */
    protected ActivationFunction activationFunction;
    /**
     * Constructor with argument
     * <p>
     *     Creates an object with the passed variables
     * </p>
     * @param id is for the identifier
     */
    public Neuron(String id) {
        this.id = id;
    }
    /**
     * Constructor with arguments
     * <p>
     *     Creates an object using the passed variables
     * </p>
     * @param id is for the identifier
     * @param activationFunction is the activation function to use
     */
    public Neuron(String id, ActivationFunction activationFunction) {
        this.id = id;
        this.activationFunction = activationFunction;
    }
    /**
     * Constructor with arguments
     * <p>
     *      Creates an object using passed variables
     * </p>
     * @param id is for the identifier
     * @param inNeurons is a list of inputNeurons
     * @param activationFunction is the activation function to use
     */
    public Neuron(String id, List<Processor> inNeurons, ActivationFunction activationFunction) {
        this.id = id;
        this.activationFunction = activationFunction;
        addInConnections(inNeurons);
    }
    /**
     * Constructor with arguments
     * <p>
     *     Creates an object using passed variables
     * </p>
     * @param id is for the identifier
     * @param inNeurons is a list of input neurons
     * @param bias is the bias connection
     * @param activationFunction is the activation function to use
     */
    public Neuron(String id, List<Processor> inNeurons, Processor bias, ActivationFunction activationFunction) {
        this(id, inNeurons, activationFunction);
        addBiasConnection(bias);
    }
    /**
     * Function definition for getId()
     * <p>
     *     Returns the id of the neuron
     * </p>
     * @return the id
     */
    @Override
    public String getId() {
        return id;
    }
    /**
     * Function definition for getConnection()
     * <p>
     *     Returns the connection to a given neuron
     * </p>
     * @param neuronId is the neuron to get the connection to
     * @return the connection
     */
    @Override
    public Connection getConnection(String neuronId) {
        return connectionLookup.get(neuronId);
    }
    /**
     * Function definition for getInputConnections()
     * <p>
     *     Returns the inputConnections
     * </p>
     * @return the inputConnections
     */
    @Override
    public List<Connection> getInputConnections() {
        return inputConnections;
    }
    /**
     * Function definition for getOutput()
     * <p>
     *     Return the output
     * </p>
     * @return the output
     */
    @Override
    public double getOutput() {
        return output;
    }
    /**
     * Function definition for setOutput()
     * <p>
     *     Sets the output to the passed value
     * </p>
     * @param output is the output to be used
     */
    public void setOutput(double output) {
        this.output = output;
    }
    /**
     * Function definition for calculateOutput()
     * <p>
     *      Overrides existing calculateOutput function
     * </p>
     */
    @Override
    public void calculateOutput() {
        double weightedSum = 0;
        for (Connection con : inputConnections) {
            double weight = con.getWeight();
            double previousLayerOutput = con.getFromNeuron().getOutput();

            weightedSum = weightedSum + (weight * previousLayerOutput);
        }

        if (biasConnection != null) {
            weightedSum += (biasConnection.getWeight() * bias);
        }

        output = activationFunction.calculateOutput(weightedSum);
    }
    /**
     * Function definition for addInConnections()
     * <p>
     *     Adds connections for all input neurons
     * </p>
     * @param inNeurons is a list of input neurons
     */
    private void addInConnections(List<Processor> inNeurons) {
        for (Processor neuron : inNeurons) {
            Connection con = new Connection(Randomizer.generateId(), neuron, this);
            inputConnections.add(con);
            connectionLookup.put(neuron.getId(), con);
        }
    }
    /**
     * Function definition for addBiasConnection()
     * <p>
     *     Adds a biased connection to the neuron
     * </p>
     * @param neuron is the neuron to act as a biased connection
     */
    private void addBiasConnection(Processor neuron) {
        Connection con = new Connection(Randomizer.generateId(), neuron, this);
        biasConnection = con;
        inputConnections.add(con);
    }
    /**
     * Function definition for hashCode()
     * <p>
     *     Overrides the existing hashCode function
     * </p>
     * @return the result of the calculations
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((inputConnections == null) ? 0 : inputConnections.hashCode());
        return result;
    }
    /**
     * Function definition for equals()
     * <p>
     *     Overrides the existing equals function
     * </p>
     * @param obj is the object to be compared against
     * @return whether equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Neuron other = (Neuron) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (inputConnections == null) {
            if (other.inputConnections != null)
                return false;
        } else if (!inputConnections.equals(other.inputConnections))
            return false;
        return true;
    }
}
