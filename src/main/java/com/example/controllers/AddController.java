package com.example.controllers;

import com.example.models.Food;
import com.example.utils.DatabaseQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox<Food> foodComboBox;
    @FXML
    private Label addedItemsLabel;
    @FXML
    private Button backButton;
    @FXML
    private TextField servingsTextField;

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
    private void addFoodItem() {
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

            // Update total calories in SecondaryController
            if (secondaryController != null) {
                int totalCalories = addedFoods.entrySet()
                        .stream()
                        .mapToInt(entry -> entry.getKey().getCaloriesPerServing() * entry.getValue())
                        .sum();
                secondaryController.updateCaloriesConsumed(totalCalories);
            }
        }
    }

    @FXML
    private void handleBackButtonAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/secondary.fxml"));
            Parent secondaryPage = loader.load();
            Scene secondaryScene = new Scene(secondaryPage);

            SecondaryController controller = loader.getController();
            controller.setAddedFoods(addedFoods);

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
}

