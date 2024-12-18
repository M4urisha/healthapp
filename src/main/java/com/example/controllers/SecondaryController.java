package com.example.controllers;

//import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.text.Text;
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
    public ProgressBar calorieProgressBar;
    @FXML
    public ProgressBar exerciseProgressBar;
    @FXML
    private Label goalLabel;
    @FXML
    public Label caloriesLabel;
    @FXML
    public Label exerciseLabel;
    @FXML
    private Text welcomePart;  // Part for "Welcome"
    @FXML
    private Text userPart;  // Part for the user's name

    @FXML
    public void initialize() {
        if (exerciseProgressBar != null) {
            System.out.println("Exercise Progress Bar initialized!");
        } else {
            System.out.println("Exercise Progress Bar is null!");
        }
        System.out.println("Initializing SecondaryController...");  // Add this line to debug
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
    
    // In SecondaryController when navigating to Settings screen
    @FXML
    private void goToSettings() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/settings.fxml"));
            Parent settingsPage = loader.load();

            SettingsController settingsController = loader.getController();
            settingsController.setSecondaryController(this);  // Pass the current SecondaryController to SettingsController
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

    public void updateExerciseProgressBar() {
        if (exerciseProgressBar != null) {
            // Calculate progress for the exercise progress bar, similar to food
            double progress = (double) totalExerciseCalories / calorieGoal;
            if (progress > 1) {
                progress = 1;
            }
    
            // Update the exercise progress bar, like the food progress bar
            exerciseProgressBar.setProgress(progress);
        }
    }
    
    
    
    

    public void updateExerciseCalories(int totalExerciseCalories) {
        if (exerciseLabel != null) {
            exerciseLabel.setText("Total Calories Burned: " + totalExerciseCalories);
        }
    
        // Update the exercise progress bar (ensure smooth updates)
        updateExerciseProgressBar();
    }
    

    public void updateWelcomeText() {
        welcomePart.setText("Welcome, ");
        userPart.setText(userName); // Dynamically set the user's name
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
