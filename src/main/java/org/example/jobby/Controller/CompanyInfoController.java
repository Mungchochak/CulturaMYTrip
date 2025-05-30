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

import java.io.IOException;
import java.util.List;


public class CompanyInfoController {

    @FXML private Button saveCompanyButton;
    @FXML private Button editCompanyButton;
    @FXML private TextField nameField, emailField, industryField, ssmField, addressField, contactField;
    @FXML private TextArea additionalInfoArea;
    @FXML private TextArea addressArea;
    private final DataDao dao = new FileData();
    private final String filePath = "src/main/resources/Text/Company_Info.txt";

    private String name;
    private String email;
    private String industry;
    private String ssmNo;
    private String address;
    private String contact;
    private String additionalInfo;



    @FXML
    public void initialize() {


        getStringComInfo();
        DisplayField();
        System.out.println(isHasInfoData());

        if (isHasInfoData()==true){
            setEditable(false);
            saveCompanyButton.setDisable(true);
            editCompanyButton.setDisable(false);
        }else {
            setEditable(true);
            saveCompanyButton.setDisable(false);
            editCompanyButton.setDisable(true);
        }

// Start in "edit" mode if new, or "locked" mode if loaded
    }

    public void DisplayField(){
        nameField.setText(name == null ? "" : name);
        emailField.setText(email == null ? "" : email);
        industryField.setText(industry == null ? "" : industry);
        ssmField.setText(ssmNo == null ? "" : ssmNo);
        addressArea.setText(address == null ? "" : address);
        contactField.setText(contact == null ? "" : contact);
        additionalInfoArea.setText(additionalInfo == null ? "" : additionalInfo);
    }


    public void getStringComInfo(){
        List<String> values = null;

        try {
            values = FileData.extractCompanyValues();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (values == null || values.isEmpty()) {
            System.out.println("⚠️ 公司信息文件为空，跳过处理");
            return;
        }
        for (String val : values) {
            System.out.println(val);
        }

        name = values.get(0);
        email = values.get(1);
        industry = values.get(2);
        ssmNo = values.get(3);
        address = values.get(4);
        contact = values.get(5);
        additionalInfo = values.get(6);
    }


    public boolean isHasInfoData() {
        List<String> values;
        try {
            values = FileData.extractCompanyValues();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) {
                return true;
            }
        }

        return false;
    }









//    @FXML
//    private void GoBack(ActionEvent event) {
//        // Close the current stage/window
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.close();
//    }

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
                        addressArea.getText(),
                        contactField.getText(),
                        additionalInfoArea.getText()
                );
                dao.save(data, filePath);
                System.out.println("Saved successfully!");
                showSuccessAlert("Saved successfully!");
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
            String address = addressArea.getText();
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
//            if (email.isEmpty() || !email.matches(emailPattern))
            if (email.isEmpty()) {
                showAlert("Invalid Email", "Please enter a valid email address.");
                return false;
            }
            if (ssm.isEmpty()) {
                showAlert("Invalid SSM No", "Please enter a valid 12-digit SSM Number.");
                return false;
            }
            if (contact.isEmpty()) {
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
        addressArea.setEditable(editable);
        contactField.setEditable(editable);
        additionalInfoArea.setEditable(editable);
    }

    public void handleEditCompanyInfo() {
        setEditable(true);
        saveCompanyButton.setDisable(false);
        editCompanyButton.setDisable(true);
        showSuccessAlert("You can start editing your information now!");
    }
        private void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
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
