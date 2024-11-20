package com.example.models;

public class Exercise {
    private String exerciseName;
    private int caloriesBurned;

    public Exercise(String exerciseName, int caloriesBurned) {
        this.exerciseName = exerciseName;
        this.caloriesBurned = caloriesBurned;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    @Override
    public String toString() {
        return exerciseName; // Shows only the exercise name in the ComboBox
    }
}
