package labs.lab06;

import java.util.concurrent.Callable;

public class ComputePi implements Callable<Double> {
    private int begin;
    private int end;
    private double n;

    public ComputePi(int begin, int end, double n) {
        this.begin = begin;
        this.end = end;
        this.n = n;
    }

    @Override
    public Double call() throws Exception {
        double result = 0.0;
        for(int i=this.begin;i<=this.end;i++){
            result += 4.0 / (1.0 + Math.pow( ( (2.0*i) + 1.0) / (2.0 * this.n), 2));
        }
        return result;
    }
}
