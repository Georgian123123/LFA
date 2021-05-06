import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class InputParser {
    private File myFile;

    InputParser(File myFile) {
        this.myFile = myFile;
    }

    public StringBuilder readInput() {
        StringBuilder finalString = new StringBuilder();
        try {
            Scanner myreader = new Scanner(myFile);
            
            while(myreader.hasNextLine()) {
                String data = myreader.nextLine();
                finalString.append(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return finalString;
    }
}
