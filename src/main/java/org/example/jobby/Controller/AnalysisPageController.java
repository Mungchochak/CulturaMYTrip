package org.example.jobby.Controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import org.example.jobby.Model.DeepSeekPromptModel;
import org.example.jobby.Model.ModulModel;
import org.example.jobby.Model.ShardResponseData;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
    private void initialize() {

        ModulModel modulModel = new ModulModel();
        modulModel.ClickDisplayFullcontent(SkillMatchingContent);
        modulModel.ClickDisplayFullcontent(WorkingExperienceContent);
        modulModel.ClickDisplayFullcontent(PersonalityContent);
        modulModel.ClickDisplayFullcontent(SoftSkillsContent);
        modulModel.ClickDisplayFullcontent(PositionContent);
        modulModel.ClickDisplayFullcontent(InformationContent);
        modulModel.ClickDisplayFullcontent(NameContent);
        modulModel.ClickDisplayFullcontent(GraduatedContent);

    }



    @FXML
    protected void UploadFileClick() {
        PDFScanner scanner = new PDFScanner();


        scanner.PDFScanner(
                () -> {
                    ClearText();

                    StartLoadingAnimation();


                },
                () -> {
                    if (DeepSeekChat.FaildConnection==true){
                        StopLoadingAnimation();
                        ModulModel.WarningPopup();
                        DeepSeekChat.FaildConnection=false;
                    }else {


                        StopLoadingAnimation();


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

                    }




                }
        );
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
        SkillMatchingContent.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: Regular;");
        WorkingExperienceContent.setStyle("-fx-font-size: 15px;" + "-fx-font-weight: Regular;");
        SoftSkillsContent.setStyle("-fx-font-size: 15px;" + "-fx-font-weight: Regular;");
        PositionContent.setStyle("-fx-font-size: 15px;" + "-fx-font-weight: Regular;");
        InformationContent.setStyle("-fx-font-size: 15px;" + "-fx-font-weight: Regular;");
        NameContent.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: Regular;");
        GraduatedContent.setStyle("-fx-font-size: 20px;" + "-fx-font-weight: Regular;");
        SalaryContent.setStyle("-fx-font-size: 30px;" + "-fx-font-weight: Regular;");
        ScoreContent.setStyle("-fx-font-size: 28px;" + "-fx-font-weight: Regular;");
        PersonalityContent.setStyle("-fx-font-size: 15px;" + "-fx-font-weight: Regular;");
        ModulModel.stop();
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
}