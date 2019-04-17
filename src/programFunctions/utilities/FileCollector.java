/**
 * @author Sam Malpass
 * @version 0.0.0.d
 * @since 0.0.0.d
 */
package programFunctions.utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class FileCollector {
    /**
     * Constructor with no arguments
     * <p>
     *     Simply sets up the object
     * </p>
     */
    FileCollector() {

    }
    /**
     * Function definition for getVersionOnline()
     * <p>
     *     Gets and returns the version on the server
     * </p>
     * @return the version
     */
    public String getVersionOnline() {
        String url = "https://www.dropbox.com/s/t282ojlcfgn4xig/details.txt?raw=1";
        String filename="details.txt";
        String ver = "";
        try{
            URL download=new URL(url);
            ReadableByteChannel rbc= Channels.newChannel(download.openStream());
            FileOutputStream fileOut = new FileOutputStream(filename);
            fileOut.getChannel().transferFrom(rbc, 0, 1 << 24);
            fileOut.flush();
            fileOut.close();
            rbc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            File tmp = new File("details.txt");
            FileReader fr = new FileReader(tmp);
            BufferedReader br = new BufferedReader(fr);
            ver = br.readLine();
            br.close();
            tmp.delete();
        }
        catch (Exception e) {

        }
        return ver;
    }
    /**
     * Function definition for getSeriesList()
     * <p>
     *     Gets the seriesList from the server and returns it
     * </p>
     * @return the masterList
     */
    public ArrayList<String> getSeriesList() {
        ArrayList<String> masterList = new ArrayList<>();
        String url = "https://www.dropbox.com/s/teia5ty1t552wx9/seriesList.txt?raw=1";
        String filename="seriesList.txt";
        try{
            URL download=new URL(url);
            ReadableByteChannel rbc= Channels.newChannel(download.openStream());
            FileOutputStream fileOut = new FileOutputStream(filename);
            fileOut.getChannel().transferFrom(rbc, 0, 1 << 24);
            fileOut.flush();
            fileOut.close();
            rbc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            File tmp = new File("seriesList.txt");
            try (Scanner input = new Scanner(tmp))
            {
                while (input.hasNextLine()) {
                    System.out.print(input.hasNextLine());
                    masterList.add(input.nextLine());
                }
            }
            catch (Exception e) {
            }
            tmp.delete();
        }
        catch (Exception e) {
        }
        return masterList;
    }
    /**
     * Function definition for getSeriesMasterListURLS()
     * <p>
     *     Gets the list of Series URLS from the server
     * </p>
     * @return the list of URLS
     */
    public ArrayList<String> getSeriesMasterListURLS() {
        ArrayList<String> masterList = new ArrayList<>();
        String url = "https://www.dropbox.com/s/78gtqe1f0vks1f4/seriesMasterListURLS.txt?raw=1";
        String filename="seriesMasterListURLS.txt";
        try{
            URL download=new URL(url);
            ReadableByteChannel rbc= Channels.newChannel(download.openStream());
            FileOutputStream fileOut = new FileOutputStream(filename);
            fileOut.getChannel().transferFrom(rbc, 0, 1 << 24);
            fileOut.flush();
            fileOut.close();
            rbc.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{
            File tmp = new File("seriesMasterListURLS.txt");
            try (Scanner input = new Scanner(tmp))
            {
                while (input.hasNextLine()) {
                    System.out.print(input.hasNextLine());
                    masterList.add(input.nextLine());
                }
            }
            catch (Exception e) {
            }
            tmp.delete();
        }
        catch (Exception e) {

        }
        return masterList;
    }
    /**
     * Function definition for getMissingSeries()
     * <p>
     *     Acquires all missing series
     * </p>
     * @param missingSeries is the list of missing series
     */
    public void getMissingSeries(ArrayList<String> missingSeries) {
        ArrayList<String> urlList = getSeriesMasterListURLS();
        for(String s : missingSeries) {
            for(String x : urlList) {
                if(x.contains(s)) {
                    String str[] = x.split(" ");
                    List<String> split = new ArrayList<>();
                    split = Arrays.asList(str);
                    String url = split.get(1);
                    System.out.println("URL : " + url);
                    String filename= s+".ser";
                    System.out.println("filename : " + filename);
                    try{
                        URL download=new URL(url);
                        ReadableByteChannel rbc= Channels.newChannel(download.openStream());
                        FileOutputStream fileOut = new FileOutputStream(filename);
                        fileOut.getChannel().transferFrom(rbc, 0, 1 << 24);
                        fileOut.flush();
                        fileOut.close();
                        rbc.close();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    try{
                        File tmp = new File(filename);
                        tmp.renameTo(new File("series/" + filename));
                        tmp.delete();
                    }
                    catch (Exception e) {

                    }
                }
            }
        }
    }
}
