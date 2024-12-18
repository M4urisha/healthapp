package com.example.controllers;

import com.example.models.Exercise;
import com.example.utils.DatabaseQueries;
//import javafx.application.Platform;
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

import java.io.IOException;
import java.util.List;

public class AddExerciseController {

    @FXML
    public ComboBox<Exercise> exerciseComboBox;  
    @FXML
    public Label addedItemsLabel;
    @FXML
    private Button backButton;
    @FXML
    public TextField minutesTextField;  

    private DatabaseQueries dbQueries;
    private List<Exercise> exerciseList;

    private SecondaryController secondaryController; // This field is now being used properly

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
    public void addExerciseItem() {
        Exercise selectedExercise = exerciseComboBox.getValue();
        if (selectedExercise != null) {
            String minutesText = minutesTextField.getText();
            int minutes;
            try {
                minutes = Integer.parseInt(minutesText);
            } catch (NumberFormatException e) {
                addedItemsLabel.setText("Please enter a valid number for minutes.");
                return;
            }
    
            int totalCaloriesBurned = selectedExercise.getCaloriesBurned() * minutes;
            SecondaryController.totalExerciseCalories += totalCaloriesBurned;
    
            // Update the exercise calories in the SecondaryController
            if (secondaryController != null) {
                secondaryController.updateExerciseCalories(SecondaryController.totalExerciseCalories);
            }
    
            // Feedback to the user
            addedItemsLabel.setText("Exercise added: " + selectedExercise.getExerciseName() + ", burned " + totalCaloriesBurned + " calories.");
        }
    }
    

    
    
    // Setter method to inject the SecondaryController instance
    public void setSecondaryController(SecondaryController controller) {
        this.secondaryController = controller;
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