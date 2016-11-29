package labs.lab05;


import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;

public class ParallelMatricesMultiplicationAlgorithm implements Callable{
    private Matrix result;
    private final List<Matrix> matrices;
    private final int endMatrix;
    private final int startMatrix;

    public ParallelMatricesMultiplicationAlgorithm(List<Matrix> matrices, int startMatrix, int endMatrix) {
        this.matrices = matrices;
        this.startMatrix = startMatrix;
        this.endMatrix = endMatrix;
        this.result = matrices.get(startMatrix);
    }

    @Override
    public Matrix call() throws Exception {

        for(int i=this.startMatrix+1;i<this.endMatrix;i++) {
            this.result = this.result.times(this.matrices.get(i));
        }
        return this.result;
    }
}
