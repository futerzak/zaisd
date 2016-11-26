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
    }

    @Override
    public Matrix call() throws Exception {

        List<Matrix> matrices = new Stack<>();
        for(int i=this.startMatrix; i<this.endMatrix;i++) {
            matrices.add(this.matrices.get(i));
        }


        while(matrices.size()>1){
            Matrix temp = matrices.remove(0).times(matrices.remove(0));
            matrices.add(temp);
        }
        return this.result = matrices.get(0);
    }
}
