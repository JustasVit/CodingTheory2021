package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainController {
    @FXML
    ChoiceBox cBox;
    @FXML
    Button confirmButton;
    @FXML
    ImageView imgView;

    @FXML
    public void initialize() {

        cBox.setValue("Pasirinkite");
        cBox.getItems().add(0,"Užkoduoti bitų seką");
        cBox.getItems().add(1,"Užkoduoti tekstą");
        cBox.getItems().add(2,"Užkoduoti paveikslėlį");
    }

    @FXML
    public void selectAction() throws Exception {
        String value = (String) cBox.getValue();
        Stage oldStage = (Stage) confirmButton.getScene().getWindow();
        Stage newStage = new Stage();
        if(value.equals("Užkoduoti bitų seką")) {
            FirstScenario scenario1 = new FirstScenario();
            scenario1.start(newStage);
            oldStage.close();
        }
        else if (value.equals("Užkoduoti tekstą"))
        {
            SecondScenario scenario2 = new SecondScenario();
            scenario2.start(newStage);
            oldStage.close();
        }
    }
}
