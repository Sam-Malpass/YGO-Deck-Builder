/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.dataImporter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
public class PythonInterfacer {
    /**
     * Function definition for classifyEffect()
     * <p>
     *     Runs the effect through the Python network and returns the output
     * </p>
     * @param effect is the effect to value
     * @return the numerical value of the effect
     */
    public static int classifyEffect(String effect) {
        try {
            Process p = Runtime.getRuntime().exec("cmd /c cd C:/Users/Voctor10/IdeaProjects/YGO-Deck-Builder/Python Scripts && python classify.py \"" + effect + "\"");
            p.waitFor();
            String val;
            BufferedReader br = new BufferedReader(new FileReader(new File("Python Scripts/tmp.txt")));
            val = br.readLine();
            System.out.println("[SYSTEM] Python Script Executed Successfully!");
            return Integer.parseInt(val);
        }
        catch (Exception e) {
            System.out.println("[ERROR] Script is not present");
            System.err.println(e);
        }
        return -1;
    }
}
