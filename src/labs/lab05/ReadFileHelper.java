package labs.lab05;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileHelper {
    public static ArrayList<Matrix> readMatrices(String filePath, int number) {
        List<Matrix> matrices = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            BigDecimal[][] matrixData = new BigDecimal[][];

            matrixData = getData();
            matrices.add(new Matrix(matrixData));
        }

        return (ArrayList<Matrix>) matrices;


    }
/** TODO FIXME  */
    private static BigDecimal[][] getData(String filePath) throws FileNotFoundException {

        String line = "";

        Scanner scanner = new Scanner(new File(filePath));
        int counter = 0;
        int data = 0;

        while((line = scanner.nextLine()) != null) {
            if (line == null) {
                counter++;
            }
            int number;
            if (counter >= number){
                break;
            }
            ArrayList row = new ArrayList<Double>;
            for (String i : line.split(";")) {
                row.add(Double.parseDouble(i));
            }


        }

        try {
            line = new Scanner(new File(filePath)).nextLine();
            System.out.println(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        return data;
    }

    public String[] readLines(String filename) throws IOException {
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();
        return lines.toArray(new String[lines.size()]);
    }

    public void readFile(String filePath) {
        List<String> stringList = Files.readAllLines(filePath);
    }
}
