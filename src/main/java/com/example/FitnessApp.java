package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FitnessApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Health App");
        setRoot("primary"); // Starts with the primary scene
    }

    public static void setRoot(String fxml) throws IOException {
        try {
            // Ensure the FXML file path is correct
            FXMLLoader fxmlLoader = new FXMLLoader(FitnessApp.class.getResource(fxml + ".fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Failed to load FXML: " + fxml, e); // Pass 'fxml' variable as part of the exception message
        }
    }
    



    public static void main(String[] args) {
        launch();
    }
}
