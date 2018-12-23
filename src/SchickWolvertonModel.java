import java.util.List;

public class SchickWolvertonModel extends AbstractModel {
    public SchickWolvertonModel(List<Integer> list) {
        super(list);
    }

    @Override
    void calculate(double epsilon) {
        Result result = checkValueAndCountFI(epsilon);

        System.out.println("N: " + result.getN());
        System.out.println("FI: " + result.getFI());
        System.out.println("ET: " + result.getET());
    }

    private Result checkValueAndCountFI(double epsilon) {
        int N = this.n + 1;

        int T = countSquareSum();

        while (Math.abs(countFirstFactor(N, T) - countSecondFactor(N, T)) > epsilon) {
            N += 1;
        }

        double FI = countFirstFactor(N, T);

        return new Result(N, FI, countET(N, FI));
    }

    private int countSquareSum() {
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += data[i - 1] * data[i - 1];
        }

        return sum;
    }

    private double countFirstFactor(int actualN, int T) {
        double sum = 0.0;

        for (int i = 1; i <= n; i++) {

            double difference = actualN - (i - 1);
            double denominator = difference * T;

            sum += 1d / denominator;
        }

        return 2 * sum;
    }

    private double countSecondFactor(int actualN, int T) {
        double sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += (i - 1) * data[i - 1] * data[i - 1];
        }

        return (2 * n) / (actualN * T - sum);
    }

    private double countET(int N, double FI) {
        return Math.sqrt(Math.PI / (2 * FI * (N - n)));
    }
}
