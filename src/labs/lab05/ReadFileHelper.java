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

        BigDecimal[][] matrixData = new BigDecimal[0][0];
        ArrayList<Matrix> matricesArray = new ArrayList<>();

        int row=0, column=0;
        int test=0;

        for (String matricesString : data.split("\n\n")) {
            row=0;

            String[] rowsArray = matricesString.split("\n");
            String[] firstColumnArray = rowsArray[0].split(";");

            matrixData = new BigDecimal[rowsArray.length][firstColumnArray.length];


            for(String rowString : rowsArray) {
                column=0;
                String[] columnsArray = rowString.split(";");

                for(String unitData: columnsArray) {
                    matrixData[row][column++] = new BigDecimal(unitData.trim()).setScale(2);
                }
                row++;
            }
            matricesArray.add(new Matrix(matrixData));
        }

        return matricesArray;
    }
}
