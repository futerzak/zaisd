package labs.lab04;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SaveFileHelper {
    public static void save(String filePath, String content) throws FileNotFoundException {

        PrintWriter oFile = new PrintWriter(filePath);
        oFile.println(content);
        oFile.close();
    }
}
