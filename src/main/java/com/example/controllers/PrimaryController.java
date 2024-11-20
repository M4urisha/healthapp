package com.example.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import com.example.FitnessApp;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        FitnessApp.setRoot("secondary");
    }
}
