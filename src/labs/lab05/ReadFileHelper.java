package labs.lab05;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileHelper {
    public static ArrayList<Matrix> readMatrices(String filePath, int number) {

        ArrayList<Matrix> matrices = null;
        try {
            matrices = getData(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Matrix> resultMatrices = new ArrayList<>();

        for(int i=0; i<number; i++) {
            resultMatrices.add(matrices.get(i));
        }

        return resultMatrices;
    }

/** TODO FIXME  */
    private static ArrayList<Matrix> getData(String filePath) throws FileNotFoundException {

        String data = "";
        try {
            data = new Scanner(new File(filePath)).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BigDecimal[][] matrixData = new BigDecimal[100][100];
        ArrayList<Matrix> matricesArray = new ArrayList<>();

        int row=0, column=0;

        for (String matricesString : data.split("\n\n")) {
            for(String rowString : matricesString.split("\n")) {
                row++;
                for(String unitData: rowString.split(";")) {
                    matrixData[row][column++] = new BigDecimal(unitData);
                }
            }
            matricesArray.add(new Matrix(matrixData));
            matrixData = new BigDecimal[100][100];
        }

        return matricesArray;
    }
}
