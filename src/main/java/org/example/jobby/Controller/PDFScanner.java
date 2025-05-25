package org.example.jobby.Controller;

import javafx.application.Platform;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.example.jobby.Model.DeepSeekPromptFollowupModel;
import org.example.jobby.Model.DeepSeekPromptModel;
import org.example.jobby.Model.DeepSeekResponseModel;
import org.example.jobby.Model.ShardResponseData;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class PDFScanner {

    public DeepSeekPromptModel deepSeekPromptModel = new DeepSeekPromptModel();
    public DeepSeekResponseModel deepSeekResponseModel = new DeepSeekResponseModel();

    private static final JFileChooser fileChooser = new JFileChooser();

    public void PDFScanner(Runnable onStart, Runnable onFinish) {
        ShardResponseData.promptFollowupModel = new DeepSeekPromptFollowupModel();


        SwingUtilities.invokeLater(() -> {

            fileChooser.setDialogTitle("Select a PDF file");
            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);  // ✅ 设置始终在前
            dialog.setModal(true);        // 模态，阻止其他窗口操作


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
                // ✅ 用户确认后才显示“Loading...”
                Platform.runLater(onStart);  // JavaFX UI线程执行

                File selectedFile = fileChooser.getSelectedFile();
                try {

                    System.out.println("Running");
                    String content = extractTextFromPDF(selectedFile.getAbsolutePath());

                    deepSeekResponseModel.setSkillMatchingResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getSkillMatchingprompt()));
//                    deepSeekResponseModel.setSecondSkillMatchingResponse(DeepSeekChat.CallDeepSeekAPIWithHistory(ShardResponseData.promptFollowupModel.getSkillMatchingprompt()));

                    deepSeekResponseModel.setWorkExperienceResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getWorkingExperienceprompt()));
//                    deepSeekResponseModel.setSecondWorkExperienceResponse(DeepSeekChat.CallDeepSeekAPIWithHistory(ShardResponseData.promptFollowupModel.getWorkingExperienceprompt()));

                    deepSeekResponseModel.setPersonalityResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getPersonalityprompt()));
//                    deepSeekResponseModel.setSecondPersonalityResponse(DeepSeekChat.CallDeepSeekAPIWithHistory(ShardResponseData.promptFollowupModel.getPersonalityprompt()));

                    deepSeekResponseModel.setSoftSkillsResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getSoftSkillsprompt()));
//                    deepSeekResponseModel.setSecondSoftSkillsResponse(DeepSeekChat.CallDeepSeekAPIWithHistory(ShardResponseData.promptFollowupModel.getSoftSkillsprompt()));

                    deepSeekResponseModel.setPositionResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getPositionprompt()));
//                    deepSeekResponseModel.setSecondPositionResponse(DeepSeekChat.CallDeepSeekAPIWithHistory(ShardResponseData.promptFollowupModel.getPositionprompt()));

                    deepSeekResponseModel.setInformationResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getInformationprompt()));
//                    deepSeekResponseModel.setSecondInformationResponse(DeepSeekChat.CallDeepSeekAPIWithHistory(ShardResponseData.promptFollowupModel.getInformationprompt()));

                    deepSeekResponseModel.setSalaryResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getSalaryprompt()));
//                    deepSeekResponseModel.setSecondSalaryResponse(DeepSeekChat.CallDeepSeekAPIWithHistory(ShardResponseData.promptFollowupModel.getSalaryprompt()));

                    deepSeekResponseModel.setScoreResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getScoreprompt()));
//                    deepSeekResponseModel.setSecondScoreResponse(DeepSeekChat.CallDeepSeekAPIWithHistory(ShardResponseData.promptFollowupModel.getScoreprompt()));

                    deepSeekResponseModel.setNameResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getNameprompt()));
//                    deepSeekResponseModel.setSecondNameResponse(DeepSeekChat.CallDeepSeekAPIWithHistory(ShardResponseData.promptFollowupModel.getNameprompt()));

                    deepSeekResponseModel.setGraduatedResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getGraduatedprompt()));
//                    deepSeekResponseModel.setSecondGraduatedResponse(DeepSeekChat.CallDeepSeekAPIWithHistory(ShardResponseData.promptFollowupModel.getGraduatedprompt()));


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
            System.out.println("Got PDF Content");
            return stripper.getText(document);
        }
    }
}