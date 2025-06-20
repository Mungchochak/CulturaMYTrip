package org.example.CulturaMYTrip.Controller;

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
import org.example.CulturaMYTrip.Model.PositionDesc;
import org.example.CulturaMYTrip.Model.PositionDescFileDao;
import org.example.CulturaMYTrip.Model.DataDao;
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
    @FXML
    private TextField SkillMatch;
    @FXML
    private TextField Educationalbackground;
    @FXML
    private TextField Workexperience;

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
    String skillMatch = SkillMatch.getText();
    String educationalbackground = Educationalbackground.getText();
    String workexperience = Workexperience.getText();

    // 验证格式正确且总和不超过 100
    int skill = 0, edu = 0, work = 0;
    boolean validNumbers = true;

    //Validation
    StringBuilder errorMsg = new StringBuilder();

    try {
        skill = Integer.parseInt(skillMatch.trim());
        edu = Integer.parseInt(educationalbackground.trim());
        work = Integer.parseInt(workexperience.trim());
    } catch (NumberFormatException e) {
        errorMsg.append("Scoring Weights Configuration must be valid numbers.\n");
        validNumbers = false;
    }

    if (validNumbers) {
        int total = skill + edu + work;
        if (total != 100) {
            errorMsg.append("Total percentage must be exactly 100%\n");
        }
    }

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
    PositionDesc data = new PositionDesc(position, workingMode, minSalary, maxSalary, description, skillMatch, educationalbackground, workexperience);
    try {
        dao.save(data, filePath);
        analysisPageController.RefreshpositionComboBox();
        showSuccessAlert("Position Description saved!");
        clearFields();
        System.out.println("Position Description saved!");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private void clearFields() {
        positionField.clear();
        workingModeCombo.getSelectionModel().clearSelection();
        minSalaryField.clear();
        maxSalaryField.clear();
        descriptionArea.clear();
        SkillMatch.clear();
        Educationalbackground.clear();
        Workexperience.clear();
    }

    // Simple error dialog (replace with fancier one if you want)
    private void showErrorAlert(String msg) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
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




    @FXML
    public void handleDelete(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/CulturaMYTrip/DeletePosition.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        DeletePositionController controller = loader.getController();


        controller.setAnalysisPageController(analysisPageController);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);


        stage.show();


        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

    }



}




