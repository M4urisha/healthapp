package com.example.models;

public class Food {
    private String foodName;
    private int caloriesPerServing;

    public Food(String foodName, int caloriesPerServing) {
        this.foodName = foodName;
        this.caloriesPerServing = caloriesPerServing;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getCaloriesPerServing() {
        return caloriesPerServing;
    }

    @Override
    public String toString() {
        return foodName; // Shows only the food name in the ComboBox
    }
}

