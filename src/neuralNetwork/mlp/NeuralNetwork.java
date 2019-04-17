/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package neuralNetwork.mlp;

import neuralNetwork.function.activation.ActivationFunction;
import neuralNetwork.function.activation.IdentityActivation;
import neuralNetwork.function.activation.SigmoidActivation;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    /**
     * Holds the input Layer of the network
     */
    private final List<Processor> inputLayer = new ArrayList<>();
    /**
     * Holds the hidden Layer of the network
     */
    private final List<Processor> hiddenLayer = new ArrayList<>();
    /**
     * Holds the output layer for the network
     */
    private final List<Processor> outputLayer = new ArrayList<>();
    /**
     * Holds a bias neuron
     */
    private final Processor bias = new Neuron("BIAS");
    /**
     * Holds the learning rate
     */
    private static double learningRate = 0.85f;
    /**
     * Holds the momentum
     */
    private static double momentum = 1.8f;
    /**
     * FOR EVERY INPUT VARIABLE YOU USE YOU WILL NEED THE NUMBER OF INPUT NEURONS
     * SO FOR THE FOUR ATTRIBUTES OF MONSTERS YOU'LL NEED FOUR NEURONS
     */
    private static ArrayList<ArrayList<Double>> inputs = new ArrayList<>();
    /**
     * Expected outputs for the input data
     */
    private static ArrayList<ArrayList<Double>> expectedOutputs = new ArrayList<>();
    /**
     * Used to store the results of the network
     */
    private static ArrayList<ArrayList<Double>> resultOutputs = new ArrayList<>();
    /**
     * data holds the DataReader
     */
    private static DataReader data;
    /**
     * writer holds the DataWriter
     */
    private static DataWriter writer;
    /**
     * sseTracker holds the current best SSE
     */
    private static double sseTracker = Double.MAX_VALUE;
    /**
     * maxRuns is how many epochs to run for
     */
    private static int maxRuns = 1000000;
    /**
     * prevError is the previous error
     */
    private double prevError = Double.MAX_VALUE;
    /**
     * hiddenNeuronCount is the number of neurons in the hidden layer
     */
    private static int hiddenNeuronCount;
    /**
     * flagx is whether the settings file exists
     */
    private static boolean flagx;
    /**
     * Constructor with arguments
     * <p>
     *     Creates a network using the passed variables
     * </p>
     * @param numberOfInputNeurons is the number of neurons to have in the input layer
     * @param numberOfHiddenNeurons is the number of neurons to have in the hidden layer
     * @param numberOfOutputNeurons is the number of neurons to have in the output layer
     * @param activationFunction is activation function to use
     */
    public NeuralNetwork(int numberOfInputNeurons, int numberOfHiddenNeurons, int numberOfOutputNeurons, ActivationFunction activationFunction) {

        for (int j = 0; j < numberOfInputNeurons; j++) {
            Processor neuron = new Neuron(Randomizer.generateId(), new IdentityActivation());
            inputLayer.add(neuron);
        }

        for (int j = 0; j < numberOfHiddenNeurons; j++) {
            Processor neuron = new Neuron(Randomizer.generateId(), inputLayer, bias, activationFunction);
            hiddenLayer.add(neuron);
        }

        for (int j = 0; j < numberOfOutputNeurons; j++) {
            Neuron neuron = new Neuron(Randomizer.generateId(), hiddenLayer, bias, activationFunction);
            outputLayer.add(neuron);
        }

        for (Processor neuron : hiddenLayer) {
            List<Connection> connections = neuron.getInputConnections();
            for (Connection conn : connections) {
                conn.setWeight(Randomizer.getRandom());
            }
        }
        for (Processor neuron : outputLayer) {
            List<Connection> connections = neuron.getInputConnections();
            for (Connection conn : connections) {
                conn.setWeight(Randomizer.getRandom());
            }
        }

        if(data.settingsExist() && flagx == true) {
            int i = 0;
            for (Processor p : inputLayer) {
                for(Connection c : p.getInputConnections()) {
                    c.setWeight(data.getInputWeights().get(i));
                    i++;
                }
            }
            i = 0;
            for(Processor p : hiddenLayer) {
                for(Connection c : p.getInputConnections()) {
                    c.setWeight(data.getHiddenWeights().get(i));
                    i++;
                }
            }
            i = 0;
            for(Processor p : outputLayer) {
                for(Connection c : p.getInputConnections()) {
                    c.setWeight(data.getOutputWeights().get(i));
                    i++;
                }
            }
        }

    }
    /**
     * Function definition for prep()
     * <p>
     *     Prepares a network for actual use, not training
     * </p>
     * @param inputSize
     * @return
     */
    public static NeuralNetwork prep(int inputSize) {
        flagx = true;
        data = new DataReader();
        data.loadSettings();
        NeuralNetwork net = new NeuralNetwork(inputSize, data.getNumHidden(), 1, new SigmoidActivation());
        return net;
    }
    /**
     * Function definition for runNetwork()
     * <p>
     *      Sets up and trains the network
     * </p>
     */
    public static void run(int num ,boolean flag) {
        data = new DataReader();
        writer = new DataWriter();
        data.readIn();
        inputs = data.getInputs();
        expectedOutputs = data.getOutputs();
        for(int i = 0; i < expectedOutputs.size(); i++){
            ArrayList<Double> tmp = new ArrayList<>();
            tmp.add(0.0);
            resultOutputs.add(tmp);
        }
        flagx = flag;
        if(data.settingsExist() && flagx == true) {
            System.out.println("[SYSTEM] Settings data found and will be used...");
            data.loadSettings();
            sseTracker = data.getSseTracker();
            NeuralNetwork neuralNet = new NeuralNetwork(inputs.get(0).size(), data.getNumHidden(),1, new SigmoidActivation());
            double minErrorCondition = 0.01;
            neuralNet.train(maxRuns, minErrorCondition);
        }
        else {
            NeuralNetwork neuralNet = new NeuralNetwork(inputs.get(0).size(), num,1, new SigmoidActivation());
            double minErrorCondition = 0.01;
            neuralNet.train(maxRuns, minErrorCondition);
        }

    }
    /**
     * Function definition for getHiddenNeuronCount()
     * <p>
     *     Returns the hiddenNeuronCount
     * </p>
     * @return the hiddenNeuronCount
     */
    public static int getHiddenNeuronCount() {
        return hiddenNeuronCount;
    }
    /**
     * Function definition for getOutput()
     * <p>
     *     Creates an array and adds the outputs to it, then returns the array
     * </p>
     * @return the outputs
     */
    public ArrayList<Double> getOutput() {
        ArrayList<Double> outputs = new ArrayList<>();
        for (int i = 0; i < outputLayer.size(); i++)
            outputs.add(outputLayer.get(i).getOutput());
        return outputs;
    }
    /**
     * Function definition for getResultOutputs()
     * <p>
     *     Returns the results
     * </p>
     * @return the resultOutputs
     */
    public ArrayList<ArrayList<Double>> getResultOutputs() {
        return resultOutputs;
    }
    /**
     * Function definition for setMaxRuns()
     * <p>
     *     Sets the maxRuns to the passed value
     * </p>
     * @param runs is the value to use
     */
    public static void setMaxRuns(int runs) {
        maxRuns = runs;
    }
    /**
     * Function definition for setLearningRate()
     * <p>
     *     Sets the learning rate to the passed value
     * </p>
     * @param lr is the learning rate to use
     */
    public static void setLearningRate(double lr) {
        learningRate = lr;
    }
    /**
     * Function definition for setMomentum()
     * <p>
     *     Sets the momentum to the passed value
     * </p>
     * @param mom is the value to use
     */
    public static void setMomentum(double mom) {
        momentum = mom;
    }
    /**
     * Function definition for setInput()
     * <p>
     *     Sets the outputs for the input layer to the input data
     * </p>
     * @param inputs
     */
    public void setInput(ArrayList<Double> inputs) {
        for (int i = 0; i < inputLayer.size(); i++) {
            inputLayer.get(i).setOutput(inputs.get(i));
        }
    }
    /**
     * Function definition setResultOutputs()
     * <p>
     *     Sets the resultOutputs to the passed array
     * </p>
     * @param resultOutputs is the data to use
     */
    public void setResultOutputs(ArrayList<ArrayList<Double>> resultOutputs) {
        this.resultOutputs = resultOutputs;
    }
    /**
     * Function definition for outputWeightsConsole()
     * <p>
     *     Outputs the weights' ID and value
     * </p>
     */
    public void outputWeightsConsole() {
        System.out.println("[SYSTEM] Input Layer Weights: ");
        for(Processor p : inputLayer){
            List<Connection> x = p.getInputConnections();
            for(Connection c : x) {
                System.out.println(c.getId() + " = " + c.getWeight());
            }
        }
        System.out.println("[SYSTEM] Hidden Layer Weights: ");
        for(Processor p : hiddenLayer) {
            List<Connection> x = p.getInputConnections();
            for(Connection c : x) {
                System.out.println(c.getId() + " = " + c.getWeight());
            }
        }
        System.out.println("[SYSTEM] Output Layer Weights: ");
        for(Processor p : outputLayer) {
            List<Connection> x = p.getInputConnections();
            for(Connection c : x) {
                System.out.println(c.getId() + " = " + c.getWeight());
            }
        }

    }
    /**
     * Function definition for activate()
     * <p>
     *     Calculate the output based on the input
     * </p>
     */
    public void activate() {
        for (Processor neuron : hiddenLayer)
            neuron.calculateOutput();
        for (Processor neuron : outputLayer)
            neuron.calculateOutput();
    }
    /**
     * Function definition for applyBackpropagation()
     * <p>
     *     Updates the weights to make the actual output as close to target as possible
     * </p>
     * @param expectedOutput is the neural network's expected outputs
     */
    private void applyBackpropagation(ArrayList<Double> expectedOutput) {
        int i = 0;
        for (Processor neuron : outputLayer) {
            List<Connection> connections = neuron.getInputConnections();
            for (Connection con : connections) {
                double ak = neuron.getOutput();
                double ai = con.getFromNeuron().getOutput();
                double desiredOutput = expectedOutput.get(i);

                double partialDerivative = -ak * (1 - ak) * ai * (desiredOutput - ak);
                double deltaWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
            }
            i++;
        }

        for (Processor neuron : hiddenLayer) {
            List<Connection> connections = neuron.getInputConnections();
            for (Connection con : connections) {
                double aj = neuron.getOutput();
                double ai = con.getFromNeuron().getOutput();
                double sumKoutputs = 0;
                int j = 0;
                for (Processor outputNeuron : outputLayer) {
                    double wjk = outputNeuron.getConnection(neuron.getId()).getWeight();
                    double desiredOutput = (double) expectedOutput.get(j);
                    double ak = outputNeuron.getOutput();
                    j++;
                    sumKoutputs = sumKoutputs + (-(desiredOutput - ak) * ak * (1 - ak) * wjk);
                }

                double partialDerivative = aj * (1 - aj) * ai * sumKoutputs;
                double deltaWeight = -learningRate * partialDerivative;
                double newWeight = con.getWeight() + deltaWeight;
                con.setDeltaWeight(deltaWeight);
                con.setWeight(newWeight + momentum * con.getPrevDeltaWeight());
            }
        }
    }
    /**
     * Function definition train()
     * <p>
     *     Trains the network for a number of steps
     * </p>
     * @param maxSteps is the max steps to take
     * @param minError is the min error to look for
     */
    private void train(int maxSteps, double minError) {
        int i;
        double error = 1;
        double lowestError= Double.MAX_VALUE;
        System.out.println("##### BEGIN TRAINING #####");
        if(flagx) {
            System.out.println("Network Stats: ");
            System.out.println("Hidden Neurons: " + this.hiddenLayer.size());
            System.out.println("Learning Rate : " + this.learningRate);
            System.out.println("Momentum : " + this.momentum);
            System.out.println("Current SSE : " + sseTracker);
            outputWeightsConsole();
            lowestError= sseTracker;
            prevError = sseTracker;
        }
        for (i = 0; i < maxSteps && error > minError; i++) {
            error = 0;
            for (int p = 0; p < inputs.size(); p++) {
                setInput(inputs.get(p));

                activate();

                ArrayList<Double> output = getOutput();
                resultOutputs.set(p, output);

                for (int j = 0; j < expectedOutputs.get(p).size(); j++) {
                    double err = Math.pow(output.get(j) - expectedOutputs.get(p).get(j), 2);
                    error += err;
                }

                applyBackpropagation(expectedOutputs.get(p));
            }
            if(i % 10000 == 0) {
                System.out.print("[SYSTEM] SSE: " + error);
                if(error < prevError) {
                    System.out.print(" and is less than previous SSE\n");
                    if(error < lowestError && flagx == true) {
                        System.out.println("[SYSTEM] New lowest error found, saving to file...");
                        sseTracker = error;
                        writer.writeData(hiddenLayer.size(), sseTracker, inputLayer, hiddenLayer, outputLayer);
                        lowestError = error;
                    }
                }
                else {
                    System.out.print(" and is MORE than previous SSE\n");
                }
                prevError = error;
            }
        }
        printResult(inputs);
        if(error < sseTracker) {
            System.out.println("[SYSTEM] Error has reached a new minimum, saving...");
            sseTracker = error;
            hiddenNeuronCount = this.hiddenLayer.size();
            System.out.println(this.hiddenLayer.size());
            saveSettings();
        }
        if (i == maxSteps) {
            System.out.println("[ERROR] Max Steps Reached, further training needed");
        }
        else {
            outputWeightsConsole();
            System.out.println("[SYSTEM] Optimal Neuron Number: " + hiddenNeuronCount + " with standard error " + sseTracker);
        }
        System.out.println("#####\tTRAINING COMPLETE\t#####");
    }
    /**
     * Function definition for printResult()
     * <p>
     *     Outputs results to the developer terminal
     * </p>
     * @param inputs are the inputs used
     */
    private void printResult(ArrayList<ArrayList<Double>> inputs) {
        System.out.println("[SYSTEM] Multilayer perceptron Training:");
        for (int p = 0; p < inputs.size(); p++) {
            System.out.print("INPUTS: ");
            for (int x = 0; x < inputLayer.size(); x++) {
                System.out.print(inputs.get(p).get(x) + " ");
            }

            System.out.print("EXPECTED: ");
            for (int x = 0; x < outputLayer.size(); x++) {
                System.out.print(expectedOutputs.get(p).get(x) + " ");
            }

            System.out.print("ACTUAL: ");
            for (int x = 0; x < outputLayer.size(); x++) {
                System.out.print(resultOutputs.get(p).get(x) + " ");
            }
            System.out.println();
        }
    }
    /**
     * Function definition for saveSettings()
     * <p>
     *     Saves the network settings
     * </p>
     */
    private void saveSettings() {
        System.out.println("[SYSTEM] Saving Network settings...");
        DataWriter data = new DataWriter();
        data.writeData(hiddenNeuronCount, sseTracker, inputLayer, hiddenLayer, outputLayer);
    }
}
