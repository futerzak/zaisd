package labs.lab05;

import labs.Helpers.ReadFileHelper;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ReadMatrices {
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

    private static ArrayList<Matrix> getData(String filePath) throws FileNotFoundException {

        String data = ReadFileHelper.readFile(filePath);

        ArrayList<Matrix> matricesArray = new ArrayList<>();
        int row=0, column=0;

        for (String matricesString : data.split("\n\n")) {
            String[] rowsArray = matricesString.split("\n");
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
