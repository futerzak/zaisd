package labs.lab05;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFileHelper {
    public static ArrayList<Matrix> readMatrices(String filePath, int number) {

        ArrayList<Matrix> matrix = new ArrayList<>();
        String line = "";
        try {
            line = new Scanner(new File(filePath)).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        return null;
    }
}
