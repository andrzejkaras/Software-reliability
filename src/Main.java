import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = ReadFileUtil.read("input2.txt");
        AbstractModel model = new JelinskiMorandaModel(list);
        model.calculate(0.00001);

        System.out.println("-----------------------------------------");

        List<Integer> listTwo = ReadFileUtil.read("input2.txt");
        SchickWolvertonModel modeL = new SchickWolvertonModel(listTwo);
        modeL.calculate(0.00001);
    }
}
