/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.mlp;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class DataReader {
    /**
     * numHidden holds the numHidden read in
     */
    private int numHidden;
    /**
     * sseTracker holds the read in sseTracker
     */
    private double sseTracker;
    /**
     * inputWeights holds a list of weights
     */
    private ArrayList<Double> inputWeights;
    /**
     * hiddenWeights holds a list of weights
     */
    private ArrayList<Double> hiddenWeights;
    /**
     * outputWeights holds a list of weights
     */
    private ArrayList<Double> outputWeights;
    /**
     * inputs holds a list of read in inputs
     */
    private ArrayList<ArrayList<Double>> inputs;
    /**
     * expectedOutputs holds the expected outputs for the inputs
     */
    private ArrayList<ArrayList<Double>> expectedOutputs;
    /**
     * Constructor with no arguments()
     * <p>
     *     Creates the object
     * </p>
     */
    DataReader() {
        inputs = new ArrayList<>();
        expectedOutputs = new ArrayList<>();
    }
    /**
     * Function definition for getInputs()
     * <p>
     *     Returns the inputs
     * </p>
     * @return inputs
     */
    public ArrayList<ArrayList<Double>> getInputs() {
        return inputs;
    }
    /**
     * Function definition for getOutputs()
     * <p>
     *     Returns expectedOutputs
     * </p>
     * @return the expectedOutputs
     */
    public ArrayList<ArrayList<Double>> getOutputs() {
        return expectedOutputs;
    }
    /**
     * Function definition for getNumHidden()
     * <p>
     *     Return the numHidden
     * </p>
     * @return numHidden
     */
    public int getNumHidden() {
        return numHidden;
    }
    /**
     * Function definition for getSseTracker()
     * <p>
     *     Return the sseTracker
     * </p>
     * @return sseTracker
     */
    public double getSseTracker() {
        return sseTracker;
    }
    /**
     * Function definition for getInputWeights()
     * <p>
     *     Return the inputWeights
     * </p>
     * @return inputWeights
     */
    public ArrayList<Double> getInputWeights() {
        return inputWeights;
    }
    /**
     * Function definition for getHiddenWeights()
     * <p>
     *     Return the hiddenWeights
     * </p>
     * @return hiddenWeights
     */
    public ArrayList<Double> getHiddenWeights() {
        return hiddenWeights;
    }
    /**
     * Function definition for getOutputWeights()
     * <p>
     *     Return the outputWeights
     * </p>
     * @return outputWeights
     */
    public ArrayList<Double> getOutputWeights() {
        return outputWeights;
    }
    /**
     * Function definition for readIn()
     * <p>
     *     Reads in the data from the input file
     * </p>
     */
    public void readIn() {
        File file = new File("resources/TEST");
        ArrayList<String> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
        }
        catch (Exception e) {
        }
        for(int i = 0; i < input.size(); i++) {
            String tmp = input.get(i);
            String tmpArr[] = tmp.split(",");
            List<String> tmpList = Arrays.asList(tmpArr);
            ArrayList<Double> tmpData = new ArrayList<>();
            ArrayList<Double> tmpDouble = new ArrayList<>();
            for(int j = 0; j < tmpList.size(); j++) {
                if(j == tmpList.size() - 1){
                    tmpDouble.add(Double.parseDouble(tmpList.get(j)));
                }
                else {
                    tmpData.add(Double.parseDouble(tmpList.get(j)));
                }
            }
            inputs.add(tmpData);
            expectedOutputs.add(tmpDouble);
        }
    }
    /**
     * Function definition for settingsExist()
     * <p>
     *     Checks whether there is a settings file
     * </p>
     * @return whether settings exist
     */
    public boolean settingsExist() {
        File tmp = new File("networksettings/settings.txt");
        return tmp.exists();
    }
    /**
     * Function definition for loadSettings()
     * <p>
     *     Loads the settings for the network
     * </p>
     */
    public void loadSettings() {
        File file = new File("networksettings/settings.txt");
        ArrayList<String> input = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                input.add(line);
            }
        }
        catch (Exception e) {
        }
        inputWeights = new ArrayList<>();
        hiddenWeights = new ArrayList<>();
        outputWeights = new ArrayList<>();
        numHidden = Integer.parseInt(input.get(1));
        sseTracker = Double.parseDouble(input.get(2));
        int determinator = 0;
        for(String S : input) {
            if(S.equals("HIDDEN LAYER")) {
                determinator = 1;
                continue;
            }
            else if(S.equals("OUTPUT LAYER")) {
                determinator = 2;
                continue;
            }
            if(determinator == 0) {
                continue;
            }
            else if(determinator == 1){
                hiddenWeights.add(Double.parseDouble(S));
            }
            else if(determinator == 2) {
                outputWeights.add(Double.parseDouble(S));
            }
        }
    }
}
