package main.java.pl.edu.wat.no.pojo;

public class Result {
    private int N;
    private double FI;
    private double ET;

    public Result(int N, double FI, double ET) {
        this.N = N;
        this.FI = FI;
        this.ET = ET;
    }

    public int getN() {
        return N;
    }

    public double getFI() {
        return FI;
    }

    public double getET() {
        return ET;
    }
}
