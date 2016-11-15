package labs.lab05;

import java.math.BigDecimal;

public class Matrix {

    int ROWS;
    int COLUMNS;
    BigDecimal[][] data;

    // create ROWS-by-COLUMNS matrix of 0's
    public Matrix(int rows, int columns) {
        this.ROWS = rows;
        this.COLUMNS = columns;
        data = new BigDecimal[rows][columns];
    }

    // create matrix based on 2d array
    public Matrix(BigDecimal[][] data) {
        this.ROWS = data.length;
        this.COLUMNS = data[0].length;
        this.data = new BigDecimal[this.ROWS][this.COLUMNS];

        for (int i = 0; i < this.ROWS; i++) {
            for (int j = 0; j < this.COLUMNS; j++) {
                this.data[i][j] = data[i][j];
            }
        }
    }

    // copy constructor
    private Matrix(Matrix matrix) { this(matrix.data); }

    private void swap(int i, int j) {
        BigDecimal[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public Matrix times(Matrix matrix) {
        Matrix thisMatrix = this;
        if (thisMatrix.COLUMNS != matrix.ROWS) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix outputMatrix = new Matrix(thisMatrix.COLUMNS, matrix.ROWS);
        for (int i = 0; i < outputMatrix.ROWS; i++)
            for (int j = 0; j < outputMatrix.COLUMNS; j++)
                for (int k = 0; k < thisMatrix.COLUMNS; k++)
                    outputMatrix.data[i][j] += (thisMatrix.data[i][k] * matrix.data[k][j]);
        return outputMatrix;
    }

    // print matrix to standard output
    public void show() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.printf("%9.4f ", data[i][j]);
            }
            System.out.println();
        }
    }
}
