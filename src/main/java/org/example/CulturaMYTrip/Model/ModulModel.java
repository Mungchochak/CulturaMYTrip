package org.example.CulturaMYTrip.Model;

import javafx.animation.Timeline;
import javafx.scene.control.*;
import java.util.HashMap;
import java.util.Map;


public class ModulModel {

    public static void WarningPopup() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Network Error");
        alert.setHeaderText("Connection Failed");
        alert.setContentText("The network connection failed. Please check your network or proxy settings!");
        alert.showAndWait();
    }



    private static final String TEXT = ". . . . . .";
    private static final Map<Label, Timeline> activeTimelines = new HashMap<>();








}





