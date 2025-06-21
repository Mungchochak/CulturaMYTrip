package org.example.CulturaMYTrip;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Analysis-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setTitle("JOBBY");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/CSS/ButtonPress.css").toExternalForm());



        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreen(false);
        stage.setFullScreenExitHint("");
        stage.setResizable(false);


        stage.setOnCloseRequest(e -> Platform.exit());

        stage.show();

    }

    public static void main(String[] args) {

        launch();
    }
}