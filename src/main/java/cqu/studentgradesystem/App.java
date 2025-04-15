package cqu.studentgradesystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("FXML.fxml"));
        Parent root = loader.load();
        
        // construct the DataSet and GradeAnalyser objects
        DataSet data = new DataSet();
        GradeAnalyser analyser = new GradeAnalyser(data);
        // get the reference to the controller object
        Controller controller = loader.getController();
        // inject the analyser into the controller
        controller.inject(analyser);
        
        scene = new Scene(root, 840, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Java version: "+ System.getProperty("java.version"));
        launch();
    }

}