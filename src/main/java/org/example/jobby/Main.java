package org.example.jobby;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.example.jobby.Model.FileData;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Analysis-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1500, 800);
        stage.setTitle("JOBBY");
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/CSS/ButtonPress.css").toExternalForm());


        // 禁用全屏快捷键（如 F11、Ctrl+Cmd+F、Esc）
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); // 不允许退出
        stage.setFullScreen(false);                                     // 不启用全屏
        stage.setFullScreenExitHint("");                                // 不提示信息
        stage.setResizable(false);


        stage.setOnCloseRequest(e -> Platform.exit());

        stage.show();

    }

    public static void main(String[] args) {

        String[] fileNames = {
                "Company_Info.txt",
                "PositionDesc.txt"
        };
        FileData.initializeFiles(fileNames);


        launch();
    }
}