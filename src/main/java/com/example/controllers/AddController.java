package com.example.controllers;

import com.example.models.Food;
import com.example.utils.DatabaseQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddController {

    @FXML
    public ComboBox<Food> foodComboBox;
    @FXML
    public Label addedItemsLabel;
    @FXML
    public Button backButton;
    @FXML
    public TextField servingsTextField;
    @FXML
    public TextField manualFoodName;
    @FXML
    public TextField manualFoodCalories;

    private DatabaseQueries dbQueries;
    private Map<Food, Integer> addedFoods = new HashMap<>();
    private SecondaryController secondaryController; // Reference to the SecondaryController

    public AddController() {
        dbQueries = new DatabaseQueries();
    }

    @FXML
    public void initialize() {
        // Load foods into the combo box
        List<Food> foodList = dbQueries.getFoodItems();
        ObservableList<Food> observableFoodList = FXCollections.observableArrayList(foodList);
        foodComboBox.setItems(observableFoodList);
    }

    @FXML
    public void addFoodItem() {
        Food selectedFood = foodComboBox.getValue();
        if (selectedFood != null) {
            // Get servings input
            int servings = 1;
            try {
                servings = Integer.parseInt(servingsTextField.getText());
            } catch (NumberFormatException e) {
                servings = 1; // Default to 1 serving if invalid input
            }
    
            // Add the food and servings to the map
            addedFoods.put(selectedFood, addedFoods.getOrDefault(selectedFood, 0) + servings);
    
            // Update the label with the added foods
            StringBuilder addedItems = new StringBuilder("Added Food Items:\n");
            addedFoods.forEach((food, count) -> addedItems.append(food.getFoodName())
                    .append(" - ")
                    .append(food.getCaloriesPerServing())
                    .append(" calories per serving, ")
                    .append(count)
                    .append(" servings\n"));
            addedItemsLabel.setText(addedItems.toString());
    
            // Calculate total calories consumed, and accumulate to totalCalories
            int totalCaloriesFromFoods = addedFoods.entrySet()
                    .stream()
                    .mapToInt(entry -> entry.getKey().getCaloriesPerServing() * entry.getValue())
                    .sum();
    
            // Accumulate calories instead of overwriting
            SecondaryController.totalCalories += totalCaloriesFromFoods;
    
            // Update the static totalCalories in SecondaryController
            if (secondaryController != null) {
                secondaryController.updateCaloriesConsumed(SecondaryController.totalCalories);
            }
        }
    }
    
    
    
    

    @FXML
    private void handleBackButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/secondary.fxml"));
            Parent secondaryPage = loader.load();
            SecondaryController secondaryController = loader.getController();
            
            // Ensure the updated totalCalories is passed to SecondaryController
            secondaryController.updateCaloriesConsumed(SecondaryController.totalCalories);
    
            Scene secondaryScene = new Scene(secondaryPage);
            Stage currentStage = (Stage) ((Node) backButton).getScene().getWindow();
            currentStage.setScene(secondaryScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    public void setSecondaryController(SecondaryController secondaryController) {
        this.secondaryController = secondaryController;
    }

    public void handleAddFoodManually(ActionEvent event) {
        String foodName = manualFoodName.getText();
        String foodCalories = manualFoodCalories.getText();
        if (!foodName.isEmpty() && !foodCalories.isEmpty()) {
            try {
                int calories = Integer.parseInt(foodCalories);
                // Create a new Food object with the entered name and calories
                Food newFood = new Food(foodName, calories);
                
                // Add the new food to the ComboBox
                foodComboBox.getItems().add(newFood);
    
                // Add the manually added food to the addedFoods map with 1 serving
                addedFoods.put(newFood, addedFoods.getOrDefault(newFood, 0) + 1);
    
                // Update the label with the added foods
                StringBuilder addedItems = new StringBuilder("Added Food Items:\n");
                addedFoods.forEach((food, count) -> addedItems.append(food.getFoodName())
                        .append(" - ")
                        .append(food.getCaloriesPerServing())
                        .append(" calories per serving, ")
                        .append(count)
                        .append(" servings\n"));
                addedItemsLabel.setText(addedItems.toString());
    
                // Recalculate total calories after adding the new manually added food
                int totalCaloriesFromFoods = addedFoods.entrySet()
                        .stream()
                        .mapToInt(entry -> entry.getKey().getCaloriesPerServing() * entry.getValue())
                        .sum();
    
                // Accumulate the calories
                SecondaryController.totalCalories += totalCaloriesFromFoods;
    
                // Update the calories in SecondaryController
                if (secondaryController != null) {
                    secondaryController.updateCaloriesConsumed(SecondaryController.totalCalories);
                }
            } catch (NumberFormatException e) {
                // Handle invalid input
                System.out.println("Invalid calorie input");
            }
        }
    }
    
}
