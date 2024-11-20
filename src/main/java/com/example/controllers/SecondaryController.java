package com.example.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import com.example.FitnessApp;

public class SecondaryController {

    @FXML
    private void switchToAdd() throws IOException {
        FitnessApp.setRoot("add");
    }
}
