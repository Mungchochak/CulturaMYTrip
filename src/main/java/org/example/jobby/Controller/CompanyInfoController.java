package org.example.jobby.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.jobby.Model.Data;
import org.example.jobby.Model.DataDao;
import org.example.jobby.Model.FileData;


public class CompanyInfoController {

    @FXML private Button saveCompanyButton;
    @FXML private Button editCompanyButton;
    @FXML private TextField nameField, emailField, industryField, ssmField, addressField, contactField;
    @FXML private TextArea additionalInfoArea;
    private final DataDao dao = new FileData();
    private final String filePath = "src/main/resources/Text/Company_Info.txt";

    @FXML
    public void initialize() {
        // Start in "edit" mode if new, or "locked" mode if loaded
        setEditable(true);
        saveCompanyButton.setDisable(false);
        editCompanyButton.setDisable(true);}
    @FXML
    private void GoBack(ActionEvent event) {
        // Close the current stage/window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    private void handleSaveCompanyInfo() {
        try {
            if (!validateFields()) {
                return;}

                Data data = new Data(
                        nameField.getText(),
                        emailField.getText(),
                        industryField.getText(),
                        ssmField.getText(),
                        addressField.getText(),
                        contactField.getText(),
                        additionalInfoArea.getText()
                );
                dao.save(data, filePath);
                System.out.println("Saved successfully!");
                //Locking feilds after saving company Info
                setEditable(false);
                saveCompanyButton.setDisable(true);
                editCompanyButton.setDisable(false);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        private boolean validateFields() {
            String email = emailField.getText();
            String ssm = ssmField.getText();
            String contact = contactField.getText();
            String name = nameField.getText();
            String industry = industryField.getText();
            String address = addressField.getText();
            String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            String ssmPattern = "^\\d{12}$";
            String contactPattern = "^01[0-46-9]-?\\d{7,8}$";

            if (name == null || name.trim().isEmpty()) {
                showAlert("Name is required", "Name cannot be empty");
                return false;
            }
            if (industry == null || industry.trim().isEmpty()) {
                showAlert("Industry is required.", "Industry Name cannot be empty");
                return false;
            }
            if (address == null || address.trim().isEmpty()) {
                showAlert("Address is required.", "Please input your address");
                return false;
            }
            if (email.isEmpty() || !email.matches(emailPattern)) {
                showAlert("Invalid Email", "Please enter a valid email address.");
                return false;
            }
            if (ssm.isEmpty() || !ssm.matches(ssmPattern)) {
                showAlert("Invalid SSM No", "Please enter a valid 12-digit SSM Number.");
                return false;
            }
            if (contact.isEmpty() || !contact.matches(contactPattern)) {
                showAlert("Invalid Contact", "Please enter a valid Malaysian contact number (e.g., 012-3456789).");
                return false;
            }
            return true;
        }
    private void setEditable(boolean editable) {
        nameField.setEditable(editable);
        emailField.setEditable(editable);
        industryField.setEditable(editable);
        ssmField.setEditable(editable);
        addressField.setEditable(editable);
        contactField.setEditable(editable);
        additionalInfoArea.setEditable(editable);
    }

    public void handleEditCompanyInfo() {
        setEditable(true);
        saveCompanyButton.setDisable(false);
        editCompanyButton.setDisable(true);
    }
        private void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
}
