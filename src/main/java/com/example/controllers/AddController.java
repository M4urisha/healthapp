package com.example.controllers;

import com.example.models.Food;
import com.example.models.Exercise;
import com.example.utils.DatabaseQueries;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.util.List;

public class AddController {

    @FXML
    private ComboBox<Food> foodComboBox;
    @FXML
    private ComboBox<Exercise> exerciseComboBox;
    @FXML
    private Label addedItemsLabel;

    private DatabaseQueries dbQueries;

    public AddController() {
        dbQueries = new DatabaseQueries();
    }

    @FXML
    public void initialize() {
        // Load foods and exercises into the combo boxes
        List<Food> foodList = dbQueries.getFoodItems();
        List<Exercise> exerciseList = dbQueries.getExerciseItems();
        
            // Check if data is being populated correctly
    if (foodList.isEmpty()) {
        System.out.println("No food items found.");
    }
    if (exerciseList.isEmpty()) {
        System.out.println("No exercise items found.");
    }
    foodComboBox.getItems().clear();
    foodComboBox.getItems().addAll(foodList);

    exerciseComboBox.getItems().clear();
    exerciseComboBox.getItems().addAll(exerciseList);
    }

    @FXML
    private void addItem() {
        StringBuilder addedItems = new StringBuilder("Added items:\n");

        // Add selected food
        Food selectedFood = foodComboBox.getValue();
        if (selectedFood != null) {
            addedItems.append(selectedFood.getFoodName()).append(" - ").append(selectedFood.getCaloriesPerServing()).append(" calories\n");
        }

        // Add selected exercise
        Exercise selectedExercise = exerciseComboBox.getValue();
        if (selectedExercise != null) {
            addedItems.append(selectedExercise.getExerciseName()).append(" - ").append(selectedExercise.getCaloriesBurned()).append(" calories\n");
        }

        // Display the added items in the label
        addedItemsLabel.setText(addedItems.toString());
    }
}
