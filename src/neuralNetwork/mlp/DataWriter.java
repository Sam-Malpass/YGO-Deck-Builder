/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.mlp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class DataWriter {
    /**
     * Constructor with no arguments
     * <p>
     *     Creates the object
     * </p>
     */
    DataWriter() {
    }
    /**
     * Function definition for writeData()
     * <p>
     *     Writes passed data to a file
     * </p>
     * @param numHidden is the number of hidden neurons
     * @param sseTracker is the current sse
     * @param inputLayer is the input layer
     * @param hiddenLayers is the hidden layer
     * @param outputLayer is the output layer
     */
    public void writeData(int numHidden, double sseTracker, List<Processor> inputLayer, List<Processor> hiddenLayers, List<Processor> outputLayer) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        File f = new File("networksettings/settings.txt");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            bw.write(timestamp);
            bw.newLine();
            bw.write(Integer.toString(numHidden));
            bw.newLine();
            bw.write(Double.toString(sseTracker));
            bw.newLine();
            bw.write("INPUT LAYER");
            bw.newLine();
            for(Processor p : inputLayer){
                List<Connection> x = p.getInputConnections();
                for(Connection c : x) {
                    bw.write(Double.toString(c.getWeight()));
                    bw.newLine();
                }
            }
            bw.write("HIDDEN LAYER");
            bw.newLine();
            for(Processor p : hiddenLayers) {
                List<Connection> x = p.getInputConnections();
                for(Connection c : x) {
                    bw.write(Double.toString(c.getWeight()));
                    bw.newLine();
                }
            }
            bw.write("OUTPUT LAYER");
            bw.newLine();
            for(Processor p : outputLayer) {
                List<Connection> x = p.getInputConnections();
                for(Connection c : x) {
                    bw.write(Double.toString(c.getWeight()));
                    bw.newLine();
                }
            }
        }
        catch (Exception e) {

        }
    }
}
