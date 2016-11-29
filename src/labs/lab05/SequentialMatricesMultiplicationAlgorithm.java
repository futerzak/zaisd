package labs.lab05;

import java.util.List;

public class SequentialMatricesMultiplicationAlgorithm {

    private Matrix result;
    private List<Matrix> matrices;

    public SequentialMatricesMultiplicationAlgorithm(List<Matrix> matrices) {
        this.result = matrices.get(0);
        this.matrices = matrices;
    }

    public Matrix run() {
        for(int i=1;i<this.matrices.size();i++) {
            this.result = this.result.times(this.matrices.get(i));
        }
        return this.result;
    }

}
