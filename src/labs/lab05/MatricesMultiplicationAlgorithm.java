package labs.lab05;

import java.util.ArrayList;

public class MatricesMultiplicationAlgorithm {
    public MatricesMultiplicationAlgorithm(ArrayList<Matrix> matrices) {
        for(int i=0;i<matrices.size();i++) {
            if(matrices.get(i).ROWS != matrices.get(i+1).COLUMNS) {
                continue;
            }
            matrices.get(i).times(matrices.get(i+1));
        }
    }

}
