package com.example;

//import com.example.controllers.SecondaryController;
import com.example.controllers.AddController;
//import com.example.controllers.AddExerciseController;
//import com.example.models.Food;
//import com.example.models.Exercise;
//import com.example.utils.DatabaseQueries;
import javafx.application.Platform;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestFunctions {

    private AddController addController;

    @Test
    void testManualFoodEntry() {
        Platform.runLater(() -> {
            // Ensure addController is properly initialized
            addController = new AddController();
            
            // Simulate entering a food manually
            addController.manualFoodName.setText("Banana");
            addController.manualFoodCalories.setText("120");
            addController.handleAddFoodManually(null);
            
            // Assert that the food list contains the newly added food
            assertTrue(addController.foodComboBox.getItems().stream()
                    .anyMatch(food -> food.getFoodName().equals("Banana") && food.getCaloriesPerServing() == 120));
        });
    }
}
