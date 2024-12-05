package com.example.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

public class SettingsController {

    @FXML
    private TextField newGoalField; // This will be used to hold the goal value
    @FXML
    private Button exitButton;
    @FXML
    private Label goalLabel;
    @FXML
    public TextField nameTextField;

    private SecondaryController secondaryController;

    @FXML
    private void initialize() {
        // Initialize the fields with the current goal and username from SecondaryController
        if (secondaryController != null) {
            goalLabel.setText("Goal: " + secondaryController.getCalorieGoal() + " calories");
            newGoalField.setText(String.valueOf(secondaryController.getCalorieGoal()));
            nameTextField.setText(secondaryController.getUserName());
        }
    }

    public void setSecondaryController(SecondaryController secondaryController) {
        this.secondaryController = secondaryController;
    }

    @FXML
    public void handleChangeGoal() {
        try {
            String goalText = newGoalField.getText();
            if (goalText.isEmpty()) {
                goalText = "2000"; // Set a default value if the field is empty
            }
            int newGoal = Integer.parseInt(goalText); // Get the new goal from newGoalField
            if (secondaryController != null) {
                secondaryController.setGoal(newGoal); // Update goal in SecondaryController
                goalLabel.setText("Goal: " + newGoal + " calories"); // Update the goalLabel in settings
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for goal. Please enter a valid number.");
        }
    }
    
    @FXML
    public void handleSaveName() {
        String newUserName = nameTextField.getText();  // Retrieve the new name from the TextField
        if (secondaryController != null) {
            secondaryController.setUserName(newUserName);  // Set the new user name
            secondaryController.updateWelcomeText();  // Update the welcome text in SecondaryController
        }
        switchToSecondary();  // Go back to the secondary screen
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/secondary.fxml"));
            Parent secondaryRoot = loader.load();

            SecondaryController secondaryController = loader.getController();

            // Pass the updated goal and username to SecondaryController
            String goalText = newGoalField.getText();
            if (goalText.isEmpty()) {
                goalText = "2000"; // Set a default value if the field is empty
            }
            //secondaryController.setGoal(Integer.parseInt(goalText));  // Pass the updated goal
            //secondaryController.setUserName(nameTextField.getText());  // Pass the updated name
            secondaryController.updateWelcomeText();  // Update the welcome text

            Scene secondaryScene = new Scene(secondaryRoot);
            Stage stage = (Stage) newGoalField.getScene().getWindow();
            stage.setScene(secondaryScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void switchToSecondary() {
        try {
            // Ensure the goal field is not empty
            String goalText = newGoalField.getText();
            if (goalText.isEmpty()) {
                goalText = "2000"; // Set a default value if the field is empty
            }
            int newGoal = Integer.parseInt(goalText);  // Parse the goal only if it's not empty
            secondaryController.setGoal(newGoal);  // Pass the updated goal
    
            secondaryController.setUserName(nameTextField.getText());  // Pass the updated name
    
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/secondary.fxml"));
            Parent secondaryRoot = loader.load();
            secondaryController = loader.getController();
    
            Scene secondaryScene = new Scene(secondaryRoot);
            Stage stage = (Stage) newGoalField.getScene().getWindow();
            stage.setScene(secondaryScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    @FXML
    private void exitProgram() {
        Platform.exit();  // Exits the program
    }

    public void setGoal(int newGoal) {
        SecondaryController.calorieGoal = newGoal;
    }

    public void setUserName(String name) {
        SecondaryController.userName = name;
    }
}
