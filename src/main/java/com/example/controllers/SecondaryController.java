package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.Map;

import com.example.models.Food;

public class SecondaryController {

    @FXML
    private Button addFoodButton;
    @FXML
    private Button addExerciseButton;
    @FXML
    private Button settingsButton;

    @FXML
    private Label goalLabel;
    @FXML
    private Label caloriesLabel;
    @FXML
    private Text caloriesConsumedText;
    @FXML
    private Text welcomeText;

    private int calorieGoal = 2000;
    public static int totalCalories = 0;  // Make sure totalCalories persists
    private String userName = "User";

    @FXML
    public void initialize() {
        goalLabel.setText("Goal: " + calorieGoal + " calories");
        caloriesConsumedText.setText("Calories consumed today: " + totalCalories);
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
    
            // Pass the current goal and username to SettingsController so it persists
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
    
    public void updateCaloriesConsumed(int calories) {
        // Update the label to show the current total calories consumed
        caloriesLabel.setText("Calories Consumed: " + totalCalories);
        caloriesConsumedText.setText("Calories consumed today: " + totalCalories);
    }

    private void updateWelcomeText() {
        welcomeText.setText("Welcome, " + userName);
    }

    public void setUserName(String name) {
        this.userName = name;
        updateWelcomeText();
    }

    public void setGoal(int newGoal) {
        this.calorieGoal = newGoal;
        goalLabel.setText("Goal: " + newGoal + " calories");
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

    // Define the setAddedFoods method
    public void setAddedFoods(Map<Food, Integer> addedFoods) {
        int totalCaloriesFromFoods = 0;

        // Calculate total calories based on the map
        for (Map.Entry<Food, Integer> entry : addedFoods.entrySet()) {
            Food food = entry.getKey();
            int servings = entry.getValue();
            totalCaloriesFromFoods += food.getCaloriesPerServing() * servings;
        }

        // Update the total calories consumed
        totalCalories += totalCaloriesFromFoods;
        updateCaloriesConsumed(totalCaloriesFromFoods); // Make sure the label reflects updated calories
    }
}

