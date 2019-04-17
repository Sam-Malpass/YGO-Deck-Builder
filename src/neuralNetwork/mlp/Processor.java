/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.mlp;
import java.util.List;
public interface Processor {
    String getId();
    double getOutput();
    void setOutput(double output);
    void calculateOutput();
    Connection getConnection(String neuronId);
    List<Connection> getInputConnections();
}
