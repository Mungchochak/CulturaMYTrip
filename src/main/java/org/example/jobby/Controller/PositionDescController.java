package org.example.jobby.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



import java.io.IOException;


public class PositionDescController {

    @FXML
    public void handleDelete(ActionEvent event) {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("/org/example/jobby/DeletePosition.fxml"));
            // Get current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void GoBack(ActionEvent event) {
        // Close the current stage/window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}




