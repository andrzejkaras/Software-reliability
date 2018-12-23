import java.util.List;

public abstract class AbstractModel {
    protected int n;
    protected Integer[] data;

    public AbstractModel(List<Integer> list) {
        n = list.size();
        data = list.toArray(new Integer[n]);
    }

    abstract void calculate(double epsilon);
}
