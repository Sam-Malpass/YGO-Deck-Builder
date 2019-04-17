/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.mlp;
public class Connection {
    /**
     * Holds the connection's identifier
     */
    private final String id;
    /**
     * The source neuron for this connection
     */
    private final Processor fromNeuron;
    /**
     * The destination neuron for this connection
     */
    private final Processor toNeuron;
    /**
     * The weight for the connection
     */
    private double weight = 0;
    /**
     * The change in weight
     */
    private double deltaWeight = 0;
    /**
     * For momentum
     */
    private double prevDeltaWeight = 0;
    /**
     * Constructor with arguments
     * <p>
     *     Sets up the object using the passed variables/objects
     * </p>
     * @param id is the identifier to use
     * @param fromN is the object to use for the fromNeuron
     * @param toN is the object to use for the toNeuron
     */
    public Connection(String id, Processor fromN, Processor toN) {
        this.id = id;
        fromNeuron = fromN;
        toNeuron = toN;
    }
    /**
     *  Function definition for getId()
     *  <p>
     *      Returns the id for this connection
     *  </p>
     * @return the id
     */
    public String getId() {
        return id;
    }
    /**
     * Function definition for getWeight()
     * <p>
     *     Returns the weight for this connection
     * </p>
     * @return return the weight
     */
    public double getWeight() {
        return weight;
    }
    /**
     * Function definition for getPrevDeltaWeight()
     * <p>
     *     Returns the prevDeltaWeight for this connection
     * </p>
     * @return prevDeltaWeight
     */
    public double getPrevDeltaWeight() {
        return prevDeltaWeight;
    }
    /**
     * Function for getFromNeuron()
     * <p>
     *     Returns the fromNeuron for this connection
     * </p>
     * @return the fromNeuron
     */
    public Processor getFromNeuron() {
        return fromNeuron;
    }
    /**
     * Function definition for setWeight()
     * <p>
     *     Sets the weight to the passed value
     * </p>
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
    /**
     * Function definition for setDeltaWeight()
     * <p>
     *     Sets the prevDeltaWeight to the current deltaWeight before setting the current deltaWeight
     *     to the passed value
     * </p>
     * @param deltaWeight is the new deltaWeight to be used
     */
    public void setDeltaWeight(double deltaWeight) {
        this.prevDeltaWeight = this.deltaWeight;
        this.deltaWeight = deltaWeight;
    }
    /**
     * Function definition for getWeightedInput()
     * <p>
     *     Returns the weighted input using the fromNeuron's output
     * </p>
     * @return the weighted input
     */
    public double getWeightedInput() {
        fromNeuron.calculateOutput();
        return fromNeuron.getOutput() * weight;
    }
    /**
     * Function definition for hashCode()
     * <p>
     *     Overrides the existing hashCode function
     * </p>
     * @return the result of hash code calculation
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(deltaWeight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((fromNeuron == null) ? 0 : fromNeuron.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        temp = Double.doubleToLongBits(prevDeltaWeight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((toNeuron == null) ? 0 : toNeuron.hashCode());
        temp = Double.doubleToLongBits(weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
    /**
     * Function definition for equals()
     * <p>
     *     Overrides the existing equals function
     * </p>
     * @param obj is the object to compare against
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
        Connection other = (Connection) obj;
        if (Double.doubleToLongBits(deltaWeight) != Double.doubleToLongBits(other.deltaWeight))
            return false;
        if (fromNeuron == null) {
            if (other.fromNeuron != null)
                return false;
        } else if (!fromNeuron.equals(other.fromNeuron))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (Double.doubleToLongBits(prevDeltaWeight) != Double.doubleToLongBits(other.prevDeltaWeight))
            return false;
        if (toNeuron == null) {
            if (other.toNeuron != null)
                return false;
        } else if (!toNeuron.equals(other.toNeuron))
            return false;
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
            return false;
        return true;
    }
}
