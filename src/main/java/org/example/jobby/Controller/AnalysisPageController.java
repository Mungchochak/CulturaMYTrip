package org.example.jobby.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.example.jobby.Model.ShardResponseData;

import java.awt.Desktop;
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
    protected void UploadFileClick() {
        PDFScanner scanner = new PDFScanner();

        scanner.PDFScanner(
                () -> {
                    SkillMatchingContent.setText("  Loading...");
                    WorkingExperienceContent.setText("  Loading...");
                    PersonalityContent.setText("  Loading...");
                    SoftSkillsContent.setText("  Loading...");
                    PositionContent.setText("  Loading...");
                    InformationContent.setText("  Loading...");
                    SalaryContent.setText("  Loading...");
                    ScoreContent.setText("  Loading...");
                },
                () -> {

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




                }
        );
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