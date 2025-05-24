package org.example.jobby.Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.control.*;
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



    public void ClickDisplayFullcontent(Label label,String Content) {

        // click
        label.setCursor(Cursor.HAND);
        System.out.println(Content);


        label.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("Jobby");

            Label contentLabel = new Label(Content);
            contentLabel.setWrapText(true);
            contentLabel.setStyle("-fx-font-size: 18px; -fx-alignment: center; -fx-text-alignment: left;");
            contentLabel.setPrefWidth(780);

            ScrollPane scrollPane = new ScrollPane(contentLabel);
            scrollPane.setFitToWidth(true);
            scrollPane.setPrefSize(780, 360);

            alert.getDialogPane().setContent(scrollPane);
            alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
            alert.getDialogPane().setPrefSize(800, 400);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.setResizable(true);

            alert.showAndWait();
        });
    }

    public void OffClickDisplayFullcontent(Label label) {
        label.setOnMouseClicked(null);
        label.setCursor(Cursor.DEFAULT);
    }

    private static final String TEXT = ". . . . . .";
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

    public static void stopAnimatedLoading() {
        for (Map.Entry<Label, Timeline> entry : activeTimelines.entrySet()) {
            entry.getValue().stop();
        }
        activeTimelines.clear();
    }





}





