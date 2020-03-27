package rus.spbstu.labs2020.lab5.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import rus.spbstu.labs2020.lab5.Connector.Connector;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Connector.setTable();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Lab5");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
