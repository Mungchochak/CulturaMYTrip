package org.example.jobby.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.jobby.Model.PositionDesc;
import org.example.jobby.Model.PositionDescFileDao;
import org.example.jobby.Model.DataDao;
import java.io.IOException;



public class PositionDescController {
    @FXML private TextField positionField;
    @FXML
    private VBox positionsVBox;

    private final String filePath = "src/main/resources/Text/PositionDesc.txt";
    private final DataDao<PositionDesc> dao = new PositionDescFileDao();

    @FXML
    private TextField minSalaryField,maxSalaryField;
    @FXML
    private ComboBox<String> workingModeCombo;
    @FXML
    private TextArea descriptionArea;

    private AnalysisPageController analysisPageController;

    public void setAnalysisPageController(AnalysisPageController controller) {
        this.analysisPageController = controller;
    }



    @FXML
    public void initialize() {


        workingModeCombo.getItems().addAll("Intern", "Part Time", "Full Time");
        // Min salary - allow max 5 digits
        minSalaryField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("\\d*")) {
                minSalaryField.setText(newText.replaceAll("[^\\d]", ""));
            } else if (newText.length() > 5) {
                minSalaryField.setText(oldText);
            }
        });

        // Max salary - allow max 6 digits
        maxSalaryField.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("\\d*")) {
                maxSalaryField.setText(newText.replaceAll("[^\\d]", ""));
            } else if (newText.length() > 6) {
                maxSalaryField.setText(oldText);
            }
        });
        positionsVBox.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.getStylesheets().add(getClass()
                        .getResource("/CSS/delete_position.css")
                        .toExternalForm());
            }
        });
    }

@FXML
private void handlesave(){
    //Gather user input
    String position = positionField.getText();
    String workingMode = workingModeCombo.getValue();
    String minSalary = minSalaryField.getText();
    String maxSalary = maxSalaryField.getText();
    String description = descriptionArea.getText();


    //Validation
    StringBuilder errorMsg = new StringBuilder();
    if (position == null || position.isEmpty()) errorMsg.append("Position is required.\n");
    if (workingMode == null || workingMode.isEmpty()) errorMsg.append("Select a working mode.\n");
    if (minSalary == null || minSalary.isEmpty() || maxSalary == null || maxSalary.isEmpty())
        errorMsg.append("Salary range is required.\n");
    if (minSalary.length() > 5) errorMsg.append("Minimum Salary cannot exceed 5 digits (Max: 99999).\n");
    if (maxSalary.length() > 6) errorMsg.append("Maximum Salary cannot exceed 6 digits (Max: 999999).\n");

    int min = 0, max = 0;
    try {
        min = Integer.parseInt(minSalary);
        max = Integer.parseInt(maxSalary);
        if (min < 0 || max < 0) errorMsg.append("Salary cannot be negative.\n");
        if (min > max) errorMsg.append("Min salary cannot be more than max salary.\n");
    } catch (NumberFormatException e) {
        errorMsg.append("Salary must be valid numbers.\n");
    }

    if (errorMsg.length() > 0) {
        // Show an alert (or your preferred way)
        showErrorAlert(errorMsg.toString());
        return;
    }
    String displayString = position + " (" + workingMode + ") - Min Salary: " + minSalary + " Max Salary: " + maxSalary;
    PositionDesc data = new PositionDesc(position, workingMode, minSalary, maxSalary, description);
    try {
        dao.save(data, filePath);
        analysisPageController.RefreshpositionComboBox();
        System.out.println("Position Description saved!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // Simple error dialog (replace with fancier one if you want)
    private void showErrorAlert(String msg) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
}

//    @FXML
//    public void handleDelete(ActionEvent event) {
//        try {
//            Parent newRoot = FXMLLoader.load(getClass().getResource("/org/example/jobby/DeletePosition.fxml"));
//            // Get current stage
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            // Set the new scene
//            stage.setScene(new Scene(newRoot));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    @FXML
    public void handleDelete(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/jobby/DeletePosition.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 注入 AnalysisPageController
        DeletePositionController controller = loader.getController();

        // 这里传入 analysisPageController 的实例！你得先有它
        controller.setAnalysisPageController(analysisPageController);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }



    @FXML
    private void GoBack(ActionEvent event) {
        // Close the current stage/window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}




