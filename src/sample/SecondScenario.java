package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class SecondScenario extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("SecondScenario.fxml"));
        stage.setTitle("RvAccounting");
        stage.setScene(new Scene(root, 500, 700));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
