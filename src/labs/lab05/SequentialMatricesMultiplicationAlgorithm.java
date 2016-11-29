package labs.lab05;

import java.util.ArrayList;

public class SequentialMatricesMultiplicationAlgorithm {

    private Matrix result;
    private ArrayList<Matrix> matrices;

    public SequentialMatricesMultiplicationAlgorithm(ArrayList<Matrix> matrices) {
        this.result = matrices.get(0);
        this.matrices = matrices;
    }

    public void selfReturn(Matrix nextMatrix) {
        this.result  = this.result.times(nextMatrix);
    }

    public Matrix run() {
        for(int i=1;i<this.matrices.size();i++) {
            this.result = this.result.times(this.matrices.get(i));
        }
        return this.result;
    }

    public Matrix getResult() {
        return result;
    }

    public ArrayList<Matrix> getMatrices() {
        return matrices;
    }

    public void setResult(Matrix result) {
        this.result = result;
    }

    public void setMatrices(ArrayList<Matrix> matrices) {
        this.matrices = matrices;
    }
}
