package org.example.CulturaMYTrip.Controller;

import javafx.application.Platform;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.example.CulturaMYTrip.Model.DeepSeekcustomizingPromptModel;
import org.example.CulturaMYTrip.Model.DeepSeekPromptModel;
import org.example.CulturaMYTrip.Model.DeepSeekResponseModel;
import org.example.CulturaMYTrip.Model.ShardResponseData;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class PDFScanner {

    public DeepSeekPromptModel deepSeekPromptModel = new DeepSeekPromptModel();
    public DeepSeekcustomizingPromptModel DeepSeekcustomizingPromptModel = new DeepSeekcustomizingPromptModel();
    public DeepSeekResponseModel deepSeekResponseModel = new DeepSeekResponseModel();

    private static final JFileChooser fileChooser = new JFileChooser();

    public void PDFScanner(Runnable onStart, Runnable onFinish) {





        SwingUtilities.invokeLater(() -> {

            fileChooser.setDialogTitle("Select a PDF file");
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            dialog.setModal(true);


            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".pdf");
                }

                public String getDescription() {
                    return "PDF Files (*.pdf)";
                }
            });

            int result = fileChooser.showOpenDialog(dialog);

            if (result == JFileChooser.APPROVE_OPTION) {

                Platform.runLater(onStart);

                File selectedFile = fileChooser.getSelectedFile();
                try {

                    System.out.println("Running");
                    String content = extractTextFromPDF(selectedFile.getAbsolutePath());

                    String AditionalInfo = AnalysisPageController.PrintPersonalInformation();


                    boolean SpecialAnalysis=AnalysisPageController.BoolSpecialAnalysis();




                    if(SpecialAnalysis == true){
                        deepSeekResponseModel.setSkillMatchingResponse(DeepSeekChat.callDeepSeekAPI(content, DeepSeekcustomizingPromptModel.getSkillMatchingprompt(AditionalInfo)));
                        deepSeekResponseModel.setWorkExperienceResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getWorkingExperienceprompt()));
                        deepSeekResponseModel.setPersonalityResponse(DeepSeekChat.callDeepSeekAPI(content, DeepSeekcustomizingPromptModel.getPersonalityprompt(AditionalInfo)));
                        deepSeekResponseModel.setSoftSkillsResponse(DeepSeekChat.callDeepSeekAPI(content, DeepSeekcustomizingPromptModel.getSoftSkillsprompt()));
                        deepSeekResponseModel.setPositionResponse(DeepSeekChat.callDeepSeekAPI(content, DeepSeekcustomizingPromptModel.getPositionprompt(AditionalInfo)));
                        deepSeekResponseModel.setInformationResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getInformationprompt()));
                        deepSeekResponseModel.setSalaryResponse(DeepSeekChat.callDeepSeekAPI(content, DeepSeekcustomizingPromptModel.getSalaryprompt(AditionalInfo)));
                        deepSeekResponseModel.setScoreResponse(DeepSeekChat.callDeepSeekAPI(content, DeepSeekcustomizingPromptModel.getScoreprompt(AditionalInfo)));
                        deepSeekResponseModel.setNameResponse(DeepSeekChat.callDeepSeekAPI(content, DeepSeekcustomizingPromptModel.getNameprompt()));
                        deepSeekResponseModel.setGraduatedResponse(DeepSeekChat.callDeepSeekAPI(content, DeepSeekcustomizingPromptModel.getGraduatedprompt()));
                        deepSeekResponseModel.setCgpaResponse(DeepSeekChat.callDeepSeekAPI(content, DeepSeekcustomizingPromptModel.getCgpaprompt()));


                    }else {

                        deepSeekResponseModel.setSkillMatchingResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getSkillMatchingprompt()));
                        deepSeekResponseModel.setWorkExperienceResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getWorkingExperienceprompt()));
                        deepSeekResponseModel.setPersonalityResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getPersonalityprompt()));
                        deepSeekResponseModel.setSoftSkillsResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getSoftSkillsprompt()));
                        deepSeekResponseModel.setPositionResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getPositionprompt()));
                        deepSeekResponseModel.setInformationResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getInformationprompt()));
                        deepSeekResponseModel.setSalaryResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getSalaryprompt()));
                        deepSeekResponseModel.setScoreResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getScoreprompt()));
                        deepSeekResponseModel.setNameResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getNameprompt()));
                        deepSeekResponseModel.setGraduatedResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getGraduatedprompt()));
                        deepSeekResponseModel.setCgpaResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getCgpaprompt()));
                    }

                    ShardResponseData.responseModel = deepSeekResponseModel;



                    Platform.runLater(onFinish);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static String extractTextFromPDF(String filePath) throws IOException {
        try (PDDocument document = Loader.loadPDF(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            System.out.println("Got PDF Content");
            return stripper.getText(document);
        }
    }
}