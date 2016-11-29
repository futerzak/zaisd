package labs.Helpers;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFileHelper {


    public static String readFile(String filePath) {
        String data = "";
        try {
            data = new Scanner(new File(filePath)).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
