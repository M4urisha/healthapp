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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddExerciseController {

    @FXML
    private ComboBox<Exercise> exerciseComboBox;
    @FXML
    private Label addedItemsLabel;
    @FXML
    private Button backButton;
    @FXML
    private TextField minutesTextField;  // TextField for user to input minutes

    private DatabaseQueries dbQueries;
    private List<Exercise> addedExercises;

    public AddExerciseController() {
        dbQueries = new DatabaseQueries();
        addedExercises = new ArrayList<>();
    }

    @FXML
    public void initialize() {
        // Load exercises into the combo box
        List<Exercise> exerciseList = dbQueries.getExerciseItems();
        exerciseComboBox.getItems().clear();
        exerciseComboBox.getItems().addAll(exerciseList);
    }

    @FXML
    private void addExerciseItem() {
        // Get selected exercise from the combo box
        Exercise selectedExercise = exerciseComboBox.getValue();
        if (selectedExercise != null) {
            // Get the number of minutes the user performed the exercise
            String minutesText = minutesTextField.getText();
            int minutes = 0;
            try {
                // Parse the minutes input to an integer
                minutes = Integer.parseInt(minutesText);
            } catch (NumberFormatException e) {
                // If the input is not a valid number, show an error message
                addedItemsLabel.setText("Please enter a valid number for minutes.");
                return;
            }

            // Calculate total calories burned (calories per minute * number of minutes)
            int totalCaloriesBurned = selectedExercise.getCaloriesBurned() * minutes;

            // Add the selected exercise and the total calories burned to the list
            addedExercises.add(selectedExercise);

            // Update the label to show all added exercises and total calories burned
            StringBuilder addedItems = new StringBuilder("Added Exercise Items:\n");
            for (Exercise exercise : addedExercises) {
                addedItems.append(exercise.getExerciseName())
                        .append(" - ").append(exercise.getCaloriesBurned())
                        .append(" calories/minute\n");
            }
            addedItems.append("Total Calories Burned: ").append(totalCaloriesBurned).append("\n");
            addedItemsLabel.setText(addedItems.toString());

            // Update the total calories in the static variable of SecondaryController
            SecondaryController.totalCalories -= totalCaloriesBurned;  // Deduct calories burned from total

            // Call updateCaloriesConsumed on the SecondaryController instance, passing the burned calories
            updateCaloriesInSecondaryController(totalCaloriesBurned);
        }
    }

    private void updateCaloriesInSecondaryController(int caloriesBurned) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/secondary.fxml"));
            Parent secondaryPage = loader.load();
            SecondaryController secondaryController = loader.getController();

            // Pass the updated total calories to the secondary screen
            secondaryController.updateCaloriesConsumed(caloriesBurned);

            // Switch to the secondary scene
            Scene secondaryScene = new Scene(secondaryPage);
            Stage currentStage = (Stage) ((Node) backButton).getScene().getWindow();
            currentStage.setScene(secondaryScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackButtonAction() {
        updateCaloriesInSecondaryController(0); // Ensure that calories are updated when going back
    }
}

