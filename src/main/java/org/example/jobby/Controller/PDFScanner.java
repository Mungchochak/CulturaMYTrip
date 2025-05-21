package org.example.jobby.Controller;

import javafx.application.Platform;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.example.jobby.Model.DeepSeekPromptModel;
import org.example.jobby.Model.DeepSeekResponseModel;
import org.example.jobby.Model.ShardResponseData;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class PDFScanner {

    public DeepSeekPromptModel deepSeekPromptModel = new DeepSeekPromptModel();
    public DeepSeekResponseModel deepSeekResponseModel = new DeepSeekResponseModel();

    public void PDFScanner(Runnable onStart, Runnable onFinish) {
        SwingUtilities.invokeLater(() -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select a PDF file");

            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".pdf");
                }

                public String getDescription() {
                    return "PDF Files (*.pdf)";
                }
            });

            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                // ✅ 用户确认后才显示“Loading...”
                Platform.runLater(onStart);  // JavaFX UI线程执行

                File selectedFile = fileChooser.getSelectedFile();
                try {
                    String content = extractTextFromPDF(selectedFile.getAbsolutePath());

                    deepSeekResponseModel.setSkillMatchingResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getSkillMatchingprompt()));
                    deepSeekResponseModel.setWorkExperienceResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getWorkingExperienceprompt()));
                    deepSeekResponseModel.setPersonalityResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getPersonalityprompt()));
                    deepSeekResponseModel.setSoftSkillsResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getSoftSkillsprompt()));
                    deepSeekResponseModel.setPositionResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getPositionprompt()));
                    deepSeekResponseModel.setInformationResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getInformationprompt()));
                    deepSeekResponseModel.setSalaryResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getSalaryprompt()));
                    deepSeekResponseModel.setScoreResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getScoreprompt()));


                    ShardResponseData.responseModel = deepSeekResponseModel;



                    // ✅ 处理完再执行更新 UI 的回调
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
            return stripper.getText(document);
        }
    }
}