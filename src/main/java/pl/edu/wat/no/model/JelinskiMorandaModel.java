package pl.edu.wat.no.model;

import pl.edu.wat.no.pojo.Result;

import java.util.List;

public class JelinskiMorandaModel extends AbstractModel {
    public JelinskiMorandaModel(List<Integer> list) {
        super(list);
    }

    @Override
    public Result calculate(double epsilon) {
        int N = this.n + 1;

        while (Math.abs(countFirstFactor(N) - countSecondFactor(N)) > epsilon) {
            N += 1;
        }

        double FI = countFI(N);
        return new Result(N, FI, countET(N, FI));
    }

    private double countFirstFactor(int actualN) {
        double sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += (1d / (actualN - (i - 1)));
        }

        return sum;
    }

    private double countSecondFactor(int actualN) {
        double numerator = n * countSum();
        return numerator / countDenominatorDifference(actualN);
    }

    private double countFI(int actualN) {
        return n / countDenominatorDifference(actualN);
    }

    private double countDenominatorDifference(int actualN) {
        double denominatorFactorOne = actualN * countSum();

        double denominatorFactorTwo = 0;

        for (int i = 1; i <= n; i++) {
            denominatorFactorTwo += (i - 1) * data[i - 1];
        }

        return denominatorFactorOne - denominatorFactorTwo;
    }

    private double countSum() {
        double sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += data[i - 1];
        }

        return sum;
    }

    private double countET(int N, double FI) {
        return 1d / (FI * (N - n));
    }
}
