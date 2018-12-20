import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N = 226;
    static int n = N - 1;
    static int[] data;

    public static void main(String[] args) {
        List<Integer> list = readFile();

        data = new int[255];

        int i = 0;
        for (Integer integer : list) {
            data[i] = integer;
            i++;
        }

        double result = checkValueAndCountFI(0.0000001);

        System.out.println(result);
    }

    public static double countFirstFactor(int actualN) {
        double sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += (1d / (actualN - (i - 1)));
        }

        return sum;
    }

    public static double countSecondFactor(int actualN) {
        double sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += data[i - 1];
        }

        double numerator = n * sum;

        double denominatorFactorOne = actualN * sum;

        double denominatorFactorTwo = 0;

        for (int i = 1; i <= n; i++) {
            denominatorFactorTwo += (i - 1) * data[i - 1];
        }

        return numerator / (denominatorFactorOne - denominatorFactorTwo);
    }

    public static  double countFI(int actualN) {
        double sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += data[i - 1];
        }

        double denominatorFactorOne = actualN * sum;

        double denominatorFactorTwo = 0;

        for (int i = 1; i <= n; i++) {
            denominatorFactorTwo += (i - 1) * data[i - 1];
        }

        return n / (denominatorFactorOne - denominatorFactorTwo);
    }

    public static double checkValueAndCountFI(double epsilon) {

        int N = 256;

        while (Math.abs(countFirstFactor(N) - countSecondFactor(N)) > epsilon) {
            N += 1;
        }

        System.out.println("N value: " + N);

        return countFI(N);
    }

    public static List<Integer> readFile() {
        String file = "input.txt";

        List<Integer> list = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line;

            while ((line = br.readLine()) != null) {

                String[] splitted = line.split(",");

                for (String s : splitted) {
                    list.add(Integer.parseInt(s));
                }
            }
            br.close();

        } catch (IOException e) {
            System.out.println("ERROR: unable to read file " + file);
            e.printStackTrace();
        }

        return list;
    }
}
