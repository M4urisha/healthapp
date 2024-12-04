package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import com.example.models.Exercise;

public class SecondaryController {

    public static String userName = "User";
    public static int calorieGoal = 2000;
    public static int totalCalories = 0;
    private static List<String> exerciseDetails = new ArrayList<>();
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
    private Label goalLabel;
    @FXML
    private Label caloriesLabel;
    @FXML
    private Label welcomeText;
    @FXML
    private ListView<String> exercisesListView; 

    @FXML
    public void initialize() {
        if (goalLabel != null) {
            goalLabel.setText("Goal: " + calorieGoal + " calories");
        }
    
        updateCaloriesConsumed(totalCalories);
        
        updateWelcomeText();
    
        if (exercisesListView != null) {
            exercisesListView.getItems().addAll(exerciseDetails);
        } else {
            System.out.println("exercisesListView is null!");
        }
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
            Scene addExerciseScene = new Scene(addExercisePage, 375, 667);

            Stage currentStage = (Stage) ((Node) addExerciseButton).getScene().getWindow();
            currentStage.setScene(addExerciseScene);
            currentStage.show();
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
            caloriesLabel.setText("Total Calories: " + totalFoodCalories); // update food calories
        }
    
        double progress = (double) totalFoodCalories / calorieGoal;
        if (progress > 1) {
            progress = 1;
        }
    
        if (calorieProgressBar != null) {
            calorieProgressBar.setProgress(progress);
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

    public void setAddedExercises(List<String> newExercises) {
        if (exercisesListView != null) {
            exerciseDetails.addAll(newExercises);
            exercisesListView.getItems().clear();
            exercisesListView.getItems().addAll(exerciseDetails);
        } else {
            System.out.println("exercisesListView is null!");
        }
    }
    
}

