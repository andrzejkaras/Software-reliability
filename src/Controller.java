import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class Controller {
   @FXML
   AnchorPane ap;

   @FXML
   RadioButton jm;

   @FXML
   RadioButton sw;

   @FXML
   Button locationButton;

   @FXML
   Button computationButton;

   @FXML
   TextField epsilonTextField;

   @FXML
   Label NLabel;

   @FXML
   Label FILabel;

   @FXML
   Label ETLabel;

   private File selectedFile;

   private List<Integer> list;

   @FXML
   public void initialize() {
       locationButton.setDisable(true);
       computationButton.setDisable(true);
       epsilonTextField.setDisable(true);
   }

   public void chooseLocalization() {
       FileChooser fileChooser = new FileChooser();
       selectedFile = fileChooser.showOpenDialog(ap.getScene().getWindow());

       String fileExtension = selectedFile.getName();

       if (fileExtension.contains("txt")) {
           locationButton.setText(selectedFile.getName());

           try {
               list = ReadFileUtil.read(selectedFile.getAbsolutePath());
               epsilonTextField.setDisable(false);
               computationButton.setDisable(false);
           } catch (NumberFormatException e) {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               alert.setTitle("Okno błędu");
               alert.setHeaderText("Dane wejściowe mają niepoprawny format");

               epsilonTextField.setDisable(true);
               computationButton.setDisable(true);

               alert.showAndWait();
           }

       } else {
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setTitle("Zmień rozszerzenie");
           alert.setHeaderText(null);
           alert.setContentText("Wybierz plik z rozszerzeniem TXT, który zawiera dane wejściowe oddzielone białymi znakami.");

           locationButton.setText("Wybierz poprawne rozszerzenie");

           alert.showAndWait();
       }
   }

   public void executeComputations() {

       Result result;
       double epsilon;

       try {
           epsilon = Double.parseDouble(epsilonTextField.getText());

           if (epsilon < 0.0 || epsilon > 1.0) {
               throw new NumberFormatException();
           }

           if (jm.isSelected()) {
               AbstractModel model = new JelinskiMorandaModel(list);
               result = model.calculate(epsilon);
           } else {
               AbstractModel model = new SchickWolvertonModel(list);
               result = model.calculate(epsilon);
           }

           NLabel.setText("N = " + result.getN());
           FILabel.setText("FI = " + result.getFI());
           ETLabel.setText("ET = " + result.getET());
       } catch (NullPointerException | NumberFormatException ex) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Okno błędu");
           alert.setHeaderText("Podano niepoprawny format zmiennej epsilon");
           alert.setContentText("Przykłady dozwolonych formatów: 0.00001 lub 1.0E-5");

           NLabel.setText("N = ");
           FILabel.setText("FI = ");
           ETLabel.setText("ET = ");

           alert.showAndWait();
       }
   }

   public void jmChosen() {
        sw.setSelected(false);
        locationButton.setDisable(false);
        NLabel.setText("N = ");
        FILabel.setText("FI = ");
        ETLabel.setText("ET = ");
   }

   public void swChosen() {
       jm.setSelected(false);
       locationButton.setDisable(false);
       NLabel.setText("N = ");
       FILabel.setText("FI = ");
       ETLabel.setText("ET = ");
   }
}
