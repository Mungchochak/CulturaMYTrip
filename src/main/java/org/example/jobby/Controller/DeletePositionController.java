package org.example.jobby.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.jobby.Model.PositionDescFileDao;
import java.util.List;
import java.util.ArrayList;

public class DeletePositionController {

    @FXML
    private VBox positionsVBox;

    private final String filePath = "src/main/resources/Text/PositionDesc.txt";
    private final PositionDescFileDao dao = new PositionDescFileDao(); // Not DataDao<...> for custom methods

    private AnalysisPageController analysisPageController;

    public void setAnalysisPageController(AnalysisPageController controller) {
        this.analysisPageController = controller;
    }

    @FXML
    public void initialize() {
        loadPositionCheckboxes();

        positionsVBox.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getStylesheets().add(getClass()
                        .getResource("/CSS/delete_position.css")
                        .toExternalForm());
            }
        });
    }

    private void loadPositionCheckboxes() {
        positionsVBox.getChildren().clear();
        try {
            List<String> all = dao.loadPositionLines(filePath);
            for (String posLine : all) {
                String display = posLine.replace("Position:", ""); // or use extractPositionName(posLine)
                CheckBox cb = new CheckBox(display);
                cb.setStyle("-fx-font-size: 15px; -fx-font-family: 'Arial'; -fx-font-weight: bold; -fx-text-fill: #222;");
                cb.setUserData(posLine); // Store the unique full line for deletion
                positionsVBox.getChildren().add(cb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void GoBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/jobby/PositionDesc.fxml"));
            Parent root = loader.load();

            // 获取控制器并传入你需要的值（比如 analysisPageController）
            PositionDescController controller = loader.getController();
            controller.setAnalysisPageController(this.analysisPageController); // 如果你要回传

            // 切换场景
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        List<String> toDelete = new ArrayList<>();
        for (Node node : positionsVBox.getChildren()) {
            if (node instanceof CheckBox cb && cb.isSelected()) {
                toDelete.add((String) cb.getUserData()); // The exact line, not just name
            }
        }
        if (toDelete.isEmpty()) {
            showErrorAlert("Please select at least one position to delete.");
            return;
        }
        try {
            for (String fullLine : toDelete) {
                dao.deleteByFullLine(filePath, fullLine); // Deletes only the exact match!
                analysisPageController.RefreshpositionComboBox();
                showSuccessAlert("Successfully deleted position");
            }
            loadPositionCheckboxes();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorAlert("Error while deleting position(s): " + e.getMessage());
        }
    }




    private void showErrorAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void showSuccessAlert(String msg) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
