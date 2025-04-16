package cqu.studentgradesystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Entry point for the Student Grade System JavaFX application.
 * This class is responsible for initializing the UI, loading the FXML layout,
 * creating the {@link DataSet} and {@link GradeAnalyser} objects, and injecting
 * the analyser into the controller for further interaction.
 * 
 * @author Ayush Bhandari StudentID S12157470
 */
public class App extends Application {

    private static Scene scene;
    
    /**
     * Constructs a {@code App} instance.
     * This constructor performs no special initialization.
     */
    public App() {
        // Default constructor
    }
    
    /**
     * Starts the JavaFX application.
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     * @throws IOException if the FXML file cannot be loaded.
     */
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
        stage.setTitle("Student Grade System");
        stage.show();
    }

    /**
     * The main method. Launches the JavaFX application.
     *
     * @param args not used.
     */
    public static void main(String[] args) {
        System.out.println("Java version: " + System.getProperty("java.version"));
        launch();
    }
}