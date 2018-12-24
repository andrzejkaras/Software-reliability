package pl.edu.wat.no.model;

import pl.edu.wat.no.pojo.Result;

import java.util.List;

public abstract class AbstractModel {
    protected int n;
    protected Integer[] data;

    public AbstractModel(List<Integer> list) {
        n = list.size();
        data = list.toArray(new Integer[n]);
    }

    abstract public Result calculate(double epsilon);
}
