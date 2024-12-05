package com.example.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

public class SecondaryController {

    public static String userName = "User";
    public static int calorieGoal = 2000;
    public static int totalCalories = 0;
    public static int totalFoodCalories = 0; 
    public static int totalExerciseCalories = 0; 

    @FXML
    private Button addFoodButton;
    @FXML
    private Button addExerciseButton;
    @FXML
    private Button settingsButton;
    @FXML
    private ProgressBar calorieProgressBar;
    @FXML
    private ProgressBar exerciseProgressBar;
    @FXML
    private Label goalLabel;
    @FXML
    private Label caloriesLabel;
    @FXML
    private Label exerciseLabel;
    @FXML
    private Label welcomeText;

    @FXML
    public void initialize() {
        if (exerciseProgressBar != null) {
            System.out.println("Exercise Progress Bar initialized!");
        } else {
            System.out.println("Exercise Progress Bar is null!");
        }
        if (goalLabel != null) {
            goalLabel.setText("Goal: " + calorieGoal + " calories");
        }
        updateCaloriesConsumed(totalCalories);
        updateExerciseCalories(totalExerciseCalories);
        updateWelcomeText();
    }
    
    
    @FXML
    private void switchToAddFood() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/add.fxml"));
            Parent addFoodPage = loader.load();

            AddController addController = loader.getController();
            addController.setSecondaryController(this);

            Scene addFoodScene = new Scene(addFoodPage, 375, 667);
            Stage currentStage = (Stage) ((Node) addFoodButton).getScene().getWindow();
            currentStage.setScene(addFoodScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void switchToAddExercise() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/addexercises.fxml"));
            Parent addExercisePage = loader.load();
        
            // Initialize the controller and pass it to the SecondaryController
            AddExerciseController addExerciseController = loader.getController();
            addExerciseController.setSecondaryController(this); // Pass current controller
        
            Scene addExerciseScene = new Scene(addExercisePage);
            Stage stage = (Stage) exerciseProgressBar.getScene().getWindow(); // Make sure this line is correctly referencing the scene
            stage.setScene(addExerciseScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    @FXML
    private void goToSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/settings.fxml"));
            Parent settingsPage = loader.load();

            SettingsController settingsController = loader.getController();
            settingsController.setGoal(calorieGoal);
            settingsController.setUserName(userName);

            Scene settingsScene = new Scene(settingsPage);
            Stage currentStage = (Stage) ((Node) settingsButton).getScene().getWindow();
            currentStage.setScene(settingsScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateCaloriesConsumed(int totalFoodCalories) {
        if (caloriesLabel != null) {
            caloriesLabel.setText("Total Calories Consumed: " + totalFoodCalories); // update food calories
        }
    
        double progress = (double) totalFoodCalories / calorieGoal;
        if (progress > 1) {
            progress = 1;
        }
    
        if (calorieProgressBar != null) {
            calorieProgressBar.setProgress(progress);
        }
    }    

    public void updateExerciseProgressBar(double progress) {
        if (exerciseProgressBar != null) {
            Platform.runLater(() -> {
                // Ensure the progress bar updates only when the value changes significantly
                if (Math.abs(exerciseProgressBar.getProgress() - progress) > 0.01) {
                    exerciseProgressBar.setProgress(progress);
                }
            });
        }
    }
    
    

    public void updateExerciseCalories(int totalExerciseCalories) {
        if (exerciseLabel != null) {
            exerciseLabel.setText("Total Calories Burned: " + totalExerciseCalories); // Update burned calories
        }
    }

    private void updateWelcomeText() {
        welcomeText.setText("Welcome, " + userName);
    }

    public void setUserName(String name) {
        userName = name;
        updateWelcomeText();
    }

    public void setGoal(int newGoal) {
        calorieGoal = newGoal;
        goalLabel.setText("Goal: " + newGoal + " calories");
        updateCaloriesConsumed(totalCalories);
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public int getCalorieGoal() {
        return calorieGoal;
    }

    public String getUserName() {
        return userName;
    }
}
