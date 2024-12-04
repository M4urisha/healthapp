package com.example.controllers;

import com.example.models.Exercise;
import com.example.utils.DatabaseQueries;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

public class AddExerciseController {

    @FXML
    private ComboBox<Exercise> exerciseComboBox;  
    @FXML
    private Label addedItemsLabel;
    @FXML
    private Button backButton;
    @FXML
    private TextField minutesTextField;  

    private DatabaseQueries dbQueries;
    private List<Exercise> exerciseList;

    public AddExerciseController() {
        dbQueries = new DatabaseQueries();
    }

    @FXML
    public void initialize() {
        // Load exercises from the database into the combo box
        exerciseList = dbQueries.getExerciseItems();
        exerciseComboBox.getItems().clear();
        exerciseComboBox.getItems().addAll(exerciseList);
    }

    @FXML
    private void addExerciseItem() {
        Exercise selectedExercise = exerciseComboBox.getValue();
        if (selectedExercise != null) {
            String minutesText = minutesTextField.getText();
            int minutes = 0;
            try {
                minutes = Integer.parseInt(minutesText);
            } catch (NumberFormatException e) {
                addedItemsLabel.setText("Please enter a valid number for minutes.");
                return;
            }
    
            int totalCaloriesBurned = selectedExercise.getCaloriesBurned() * minutes;
    
            // Update total exercise calories, not affecting total food calories
            SecondaryController.totalCalories -= totalCaloriesBurned; // If you subtract calories burned
            SecondaryController secondaryController = getSecondaryController(); // Get controller via FXMLLoader
            secondaryController.updateCaloriesConsumed(SecondaryController.totalCalories); // Update total calories
    
            // Add to the exercise list
            List<String> exerciseDetails = new ArrayList<>();
            exerciseDetails.add(selectedExercise.getExerciseName() + " - " + minutes + " min - " + totalCaloriesBurned + " calories");
            secondaryController.setAddedExercises(exerciseDetails);

            addedItemsLabel.setText("Exercise added: " + selectedExercise.getExerciseName() + ", burned " + totalCaloriesBurned + " calories.");
        }
    }

    private SecondaryController getSecondaryController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/secondary.fxml"));
            loader.load(); 
            return loader.getController(); 
        } catch (IOException e) {
            e.printStackTrace();
            return null; 
        }
    }
    

    @FXML
    private void handleBackButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/secondary.fxml"));
            Parent secondaryPage = loader.load();
            SecondaryController secondaryController = loader.getController();
            
            secondaryController.updateCaloriesConsumed(SecondaryController.totalCalories);

            Scene secondaryScene = new Scene(secondaryPage, 375, 667);
            Stage currentStage = (Stage) ((Node) backButton).getScene().getWindow();
            currentStage.setScene(secondaryScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
