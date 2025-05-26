package org.example.jobby.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import org.example.jobby.Model.DeepSeekPromptFollowupModel;
import org.example.jobby.Model.ModulModel;
import org.example.jobby.Model.ShardResponseData;


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.Cursor;

public class AnalysisPageController {


    @FXML
    private Label SkillMatchingContent;
    @FXML
    private Label WorkingExperienceContent;
    @FXML
    private Label PersonalityContent;
    @FXML
    private Label SoftSkillsContent;
    @FXML
    private Label PositionContent;
    @FXML
    private Label InformationContent;
    @FXML
    private Label SalaryContent;
    @FXML
    private Label ScoreContent;
    @FXML
    private Label NameContent;
    @FXML
    private Label GraduatedContent;
    @FXML
    private Button UploadFile;
    @FXML
    private Button gearButton;
    @FXML
    private Popup optionsPopup; // Store the popup so you can hide/show as needed







    @FXML
    protected void UploadFileClick() {


        PDFScanner scanner = new PDFScanner();


        scanner.PDFScanner(
                () -> {
                    ClearText();
                    OffEnlargeContent();
                    UploadFile.setDisable(true);
                    StartLoadingAnimation();


                },
                () -> {
                    if (DeepSeekChat.FaildConnection){
                        StopLoadingAnimation();
                        ModulModel.WarningPopup();
                        DeepSeekChat.FaildConnection=false;
                        UploadFile.setDisable(false);
                    }else {


                        StopLoadingAnimation();
                        UploadFile.setDisable(false);


                        SkillMatchingContent.setText(ShardResponseData.responseModel.getSkillMatchingResponse());
                        System.out.println("SkillMatchingContent is " + ShardResponseData.responseModel.getSkillMatchingResponse());


                        WorkingExperienceContent.setText(ShardResponseData.responseModel.getWorkExperienceResponse());
                        System.out.println("WorkingExperienceContent is " + ShardResponseData.responseModel.getWorkExperienceResponse());

                        PersonalityContent.setText(ShardResponseData.responseModel.getPersonalityResponse());
                        System.out.println("PersonalityContent is " + ShardResponseData.responseModel.getPersonalityResponse());

                        SoftSkillsContent.setText(ShardResponseData.responseModel.getSoftSkillsResponse());
                        System.out.println("SoftSkillsContent is " + ShardResponseData.responseModel.getSoftSkillsResponse());

                        PositionContent.setText(ShardResponseData.responseModel.getPositionResponse());
                        System.out.println("PositionContent is " + ShardResponseData.responseModel.getPositionResponse());

                        InformationContent.setText(ShardResponseData.responseModel.getInformationResponse());
                        System.out.println("InformationContent is " + ShardResponseData.responseModel.getInformationResponse());

                        SalaryContent.setText(ShardResponseData.responseModel.getSalaryResponse());
                        System.out.println("SalaryContent is " + ShardResponseData.responseModel.getSalaryResponse());

                        ScoreContent.setText(ShardResponseData.responseModel.getScoreResponse());
                        System.out.println("ScoreContent is " + ShardResponseData.responseModel.getScoreResponse());

                        NameContent.setText(ShardResponseData.responseModel.getNameResponse());
                        System.out.println("NameContent is " + ShardResponseData.responseModel.getNameResponse());

                        GraduatedContent.setText(ShardResponseData.responseModel.getGraduatedResponse());
                        System.out.println("GraduatedContent is " + ShardResponseData.responseModel.getGraduatedResponse());





                        EnlargeContent();

                    }




                }
        );
    }

    @FXML
    private void EnlargeContent() {


        ModulModel modulModel = new ModulModel();

        ShardResponseData.promptFollowupModel = new DeepSeekPromptFollowupModel();
        System.out.println(ShardResponseData.responseModel.getSecondSkillMatchingResponse());

        modulModel.ClickDisplayFullcontent(SkillMatchingContent, ShardResponseData.responseModel.getSecondSkillMatchingResponse());
        modulModel.ClickDisplayFullcontent(WorkingExperienceContent,ShardResponseData.responseModel.getSecondWorkExperienceResponse());
        modulModel.ClickDisplayFullcontent(PersonalityContent,ShardResponseData.responseModel.getSecondPersonalityResponse());
        modulModel.ClickDisplayFullcontent(SoftSkillsContent,ShardResponseData.responseModel.getSecondSoftSkillsResponse());
        modulModel.ClickDisplayFullcontent(PositionContent,ShardResponseData.responseModel.getSecondPositionResponse());
        modulModel.ClickDisplayFullcontent(InformationContent,ShardResponseData.responseModel.getSecondInformationResponse());
        modulModel.ClickDisplayFullcontent(NameContent,ShardResponseData.responseModel.getSecondNameResponse());
        modulModel.ClickDisplayFullcontent(GraduatedContent,ShardResponseData.responseModel.getSecondGraduatedResponse());

    }

    @FXML
    private void OffEnlargeContent(){
        ModulModel modulModel = new ModulModel();
        modulModel.OffClickDisplayFullcontent(SkillMatchingContent);
        modulModel.OffClickDisplayFullcontent(WorkingExperienceContent);
        modulModel.OffClickDisplayFullcontent(PersonalityContent);
        modulModel.OffClickDisplayFullcontent(SoftSkillsContent);
        modulModel.OffClickDisplayFullcontent(PositionContent);
        modulModel.OffClickDisplayFullcontent(InformationContent);
        modulModel.OffClickDisplayFullcontent(NameContent);
        modulModel.OffClickDisplayFullcontent(GraduatedContent);
    }



    @FXML
    private void StartLoadingAnimation() {
        Label[] labels = {
                SkillMatchingContent,
                WorkingExperienceContent,
                SoftSkillsContent,
                PositionContent,
                InformationContent,
                NameContent,
                GraduatedContent,
                SalaryContent,
                ScoreContent,
                PersonalityContent
        };

        for (Label label : labels) {
            ModulModel.startAnimatedLoading(label); // 启动动画
            label.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");
            label.setAlignment(Pos.CENTER); // 水平居中
        }
    }

    @FXML
    private void StopLoadingAnimation(){
        WorkingExperienceContent.setAlignment(Pos.CENTER_LEFT);
        SoftSkillsContent.setAlignment(Pos.CENTER_LEFT);
        PositionContent.setAlignment(Pos.CENTER_LEFT);
        InformationContent.setAlignment(Pos.CENTER_LEFT);
        PersonalityContent.setAlignment(Pos.CENTER_LEFT);
        SkillMatchingContent.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: normal;");
        WorkingExperienceContent.setStyle("-fx-font-size: 15px;" + "-fx-font-weight: normal;");
        SoftSkillsContent.setStyle("-fx-font-size: 15px;" + "-fx-font-weight: normal;");
        PositionContent.setStyle("-fx-font-size: 15px;" + "-fx-font-weight: normal;");
        InformationContent.setStyle("-fx-font-size: 15px;" + "-fx-font-weight: norma;");
        NameContent.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: normal;");
        GraduatedContent.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: normal;");
        SalaryContent.setStyle("-fx-font-size: 30px;" + "-fx-font-weight: normal;");
        ScoreContent.setStyle("-fx-font-size: 28px;" + "-fx-font-weight: normal;");
        PersonalityContent.setStyle("-fx-font-size: 15px;" + "-fx-font-weight: normal;");
        ModulModel.stopAnimatedLoading();
    }

    @FXML
    private void ClearText(){
        Label[] labels = {
                SkillMatchingContent,
                WorkingExperienceContent,
                SoftSkillsContent,
                PositionContent,
                InformationContent,
                NameContent,
                GraduatedContent,
                SalaryContent,
                ScoreContent,
                PersonalityContent
        };

        for (Label label : labels) {

            label.setText("");

        }

    }


    @FXML
    protected void handleOpenStreamlit() {
        try {
            Desktop.getDesktop().browse(new URI("https://jobbybobby.streamlit.app/"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void initialize() {
        gearButton.setOnAction(event -> showOptionsPopup());
    }
    private void switchScene(String sceneName) {
        try {
            Parent newRoot = null;
            switch (sceneName) {
                case "Company_Info":
                    newRoot = FXMLLoader.load(getClass().getResource("/org/example/jobby/Company_Info.fxml"));
                    break;
                case "PositionDesc":
                    newRoot = FXMLLoader.load(getClass().getResource("/org/example/jobby/PositionDesc.fxml"));
                    break;
                default:
                    System.err.println("Unknown scene: " + sceneName);
                    return;
            }
            Stage newStage = new Stage();
            newStage.setTitle(sceneName); // 可选：设置窗口标题
            newStage.setScene(new Scene(newRoot));
            newStage.show(); // 显示新窗口
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showOptionsPopup() {
        if (optionsPopup != null && optionsPopup.isShowing()) {
            optionsPopup.hide();
            return;
        }

        // VBox for popup content
        VBox popupContent = new VBox(8);
        popupContent.setStyle("-fx-background-color: white; -fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 10; -fx-effect: dropshadow(gaussian, #33333355, 10,0,2,4);");
        popupContent.setPrefWidth(250);

        // Option 1: Company_Info
        HBox companyInfoOption = makePopupOption(
                "/org/example/jobby/Company_Info.png",
                "Company Information",
                () -> {
                    switchScene("Company_Info");
                    optionsPopup.hide();
                }
        );

        // Option 2: PositionDesc
        HBox positionDescOption = makePopupOption(
                "/org/example/jobby/PositionDesc.png",
                "Position Description",
                () -> {
                    switchScene("PositionDesc");
                    optionsPopup.hide();
                }
        );

        popupContent.getChildren().addAll(companyInfoOption, positionDescOption);

        // Create or reuse the Popup
        optionsPopup = new Popup();
        optionsPopup.getContent().add(popupContent);
        optionsPopup.setAutoHide(true);

        // Show near gearIcon
        // Convert gearIcon to screen coordinates
        double x = gearButton.localToScreen(gearButton.getBoundsInLocal()).getMinX();
        double y = gearButton.localToScreen(gearButton.getBoundsInLocal()).getMaxY() + 4;
        optionsPopup.show(gearButton.getScene().getWindow(), x, y);
    }

    // Utility method to create an option with image + label + click event
    private HBox makePopupOption(String imagePath, String label, Runnable onClick) {
        ImageView optionIcon = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        optionIcon.setFitWidth(28);
        optionIcon.setFitHeight(28);
        Label optionLabel = new Label(label);
        optionLabel.setStyle("-fx-font-size: 15px; -fx-padding: 0 0 0 10px;");

        HBox box = new HBox(optionIcon, optionLabel);
        box.setSpacing(10);
        box.setAlignment(Pos.CENTER_LEFT);
        box.setStyle("-fx-padding: 8 12 8 8; -fx-background-radius: 8;");
        box.setOnMouseEntered(e -> box.setStyle("-fx-background-color: #E7F0FB; -fx-padding: 8 12 8 8; -fx-background-radius: 8;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-background-color: white; -fx-padding: 8 12 8 8; -fx-background-radius: 8;"));
        box.setOnMouseClicked(e -> onClick.run());
        box.setCursor(Cursor.HAND);
        return box;
    }

}