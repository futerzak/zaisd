package labs.lab05;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFileHelper {
    public static List<Matrix> readMatrices(String filePath, int number) {

        List<Matrix> matrices = null;
        try {
            matrices = getData(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Matrix> resultMatrices = new ArrayList<>();

        for(int i=0; i<number; i++) {
            resultMatrices.add(matrices.get(i));
        }

        return resultMatrices;
    }

    private static List<Matrix> getData(String filePath) throws FileNotFoundException {

        String data = "";
        try {
            data = new Scanner(new File(filePath)).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Matrix> matricesArray = new ArrayList<>();
        int row=0, column=0;

        for (String matricesString : data.split("\r\n\r\n")) {
            String[] rowsArray = matricesString.split("\r\n");
            String[] firstColumnArray = rowsArray[0].split(";");

            BigDecimal[][] matrixData = new BigDecimal[rowsArray.length][firstColumnArray.length];

            row=0;
            for(String rowString : rowsArray) {
                String[] columnsArray = rowString.split(";");

                column=0;
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
