package org.example.jobby.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Duration;

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


    public void ClickDisplayFullcontent(Label label) {

        // click
        label.setCursor(Cursor.HAND);


        label.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Jobby");
            alert.setContentText(label.getText());
            alert.getButtonTypes().add(ButtonType.OK);

            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.setPrefSize(800, 400);
            alert.setResizable(true);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.getDialogPane().setStyle("-fx-font-size: 22px;");
            alert.showAndWait();
        });
    }

    private static final String TEXT = ". . . . . . .";
    private static final Map<Label, Timeline> activeTimelines = new HashMap<>();

    public static void startAnimatedLoading(Label label) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), event -> {
            String current = label.getText();
            int length = current == null ? 0 : current.length();
            length = (length + 1) % (TEXT.length() + 1);
            label.setText(TEXT.substring(0, length));
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        activeTimelines.put(label, timeline);
    }

    public static void stop() {
        for (Map.Entry<Label, Timeline> entry : activeTimelines.entrySet()) {
            entry.getValue().stop();
        }
        activeTimelines.clear();
    }





}





