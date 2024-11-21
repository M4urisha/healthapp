package com.example.utils;

import com.example.models.Food;
import com.example.models.Exercise;
import com.example.models.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueries {

    public List<Food> getFoodItems() {
        List<Food> foods = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT food_name, calories_per_serving FROM foods")) {
            while (resultSet.next()) {
                foods.add(new Food(resultSet.getString("food_name"), resultSet.getInt("calories_per_serving")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foods;
    }

    public List<Exercise> getExerciseItems() {
        List<Exercise> exercises = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT exercise_name, calories_burned FROM exercises")) {
            while (resultSet.next()) {
                exercises.add(new Exercise(resultSet.getString("exercise_name"), resultSet.getInt("calories_burned")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exercises;
    }
}
