package org.example.jobby.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;



public class DisplayUIModel {



    public static void displayLanguageAndSoftSkills(String aiResponse, VBox languageBox, VBox softSkillsBox) {
        String languageLine = null;
        String softSkillsLine = null;
        languageBox.getChildren().clear();
        softSkillsBox.getChildren().clear();

        for (String line : aiResponse.split("\n")) {
            if (line.toLowerCase().startsWith("language:")) {
                languageLine = line.replace("Language:", "").trim();
            } else if (line.toLowerCase().startsWith("soft skills:")) {
                softSkillsLine = line.replace("Soft Skills:", "").trim();
            }
        }

        if (languageLine != null && !languageLine.equalsIgnoreCase("insufficient data")) {
            languageBox.getChildren().add(buildSkillLabels(languageLine));
        } else {
            Label noLang = new Label("No language data found.");
            noLang.setStyle("-fx-text-fill: #999; -fx-font-style: italic;");
            languageBox.getChildren().add(noLang);
        }

        if (softSkillsLine != null && !softSkillsLine.equalsIgnoreCase("insufficient data")) {
            softSkillsBox.getChildren().add(buildSkillLabels(softSkillsLine));
        } else {
            Label noSkill = new Label("No soft skills data found.");
            noSkill.setStyle("-fx-text-fill: #999; -fx-font-style: italic;");
            softSkillsBox.getChildren().add(noSkill);
        }
    }

    public static void updateBasicInfoUI(String aiReplyText, Label locationLabel, Label majorLabel, FlowPane certificatePane) {
        locationLabel.setText("N/A");
        majorLabel.setText("N/A");
        certificatePane.getChildren().clear();

        String[] lines = aiReplyText.split("\n");
        boolean collectingCertificates = false;

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith("Location:")) {
                locationLabel.setText(line.substring("Location:".length()).trim());

            } else if (line.startsWith("Major:")) {
                majorLabel.setText(line.substring("Major:".length()).trim());

            } else if (line.startsWith("Certificates:")) {
                collectingCertificates = true;

            } else if (collectingCertificates && line.startsWith("-")) {
                String certLine = line.substring(1).trim(); // Remove dash
                String[] certParts = certLine.split(":", 2); // Split by colon
                String certName = certParts[0].trim();
                String certDesc = certParts.length > 1 ? certParts[1].trim() : "No additional information.";

                Label certLabel = new Label(certName);
                certLabel.setStyle(
                        "-fx-background-color: #FFFFFF;" +
                                "-fx-padding: 8 12;" +
                                "-fx-background-radius: 10;" +
                                "-fx-font-size: 13px;"
                );

                FlowPane.setMargin(certLabel, new Insets(5)); // ✅ 添加标签间距
                ClickDisplayFullcontent(certName,  certDesc, "certificate explanation", certLabel);
                certificatePane.getChildren().add(certLabel);


            }
        }

        certificatePane.setHgap(10);
        certificatePane.setVgap(10);
        certificatePane.setPrefWrapLength(300);
    }

    private static void showCertExplanation(String certName, String explanation) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Certificate Info");
        alert.setHeaderText(certName);
        alert.setContentText(explanation);
        alert.getButtonTypes().add(ButtonType.OK);

        // 移除默认窗口图标
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().clear();

        alert.showAndWait();
    }

    public static FlowPane buildSkillLabels(String skills) {
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(8); // 横向间距
        flowPane.setVgap(8); // 纵向间距
        flowPane.setPadding(new javafx.geometry.Insets(10));
        flowPane.setPrefWrapLength(500); // 自动换行


        String[] skillList = skills.split("\\|");

        // 定义一组颜色
        String[] colors = {
                "#E3F2FD", "#FCE4EC", "#FFF3E0", "#E8F5E9", "#EDE7F6",
                "#FFF8E1", "#E0F7FA", "#F3E5F5", "#FFEBEE", "#F1F8E9"
        };

        Random rand = new Random();

        for (String skill : skillList) {
            String trimmed = skill.trim();
            Label label = new Label(trimmed);
            label.setPadding(new Insets(5, 10, 5, 10));
            label.setStyle("-fx-background-color: " + colors[rand.nextInt(colors.length)] + ";"
                    + "-fx-background-radius: 10;"
                    + "-fx-font-size: 13px;"
                    + "-fx-border-color: #ccc;"
                    + "-fx-border-radius: 10;"
                    + "-fx-text-fill: #333;");

            flowPane.getChildren().add(label);
        }

        return flowPane;
    }



    public static VBox CategorizedExperienceUI(String aiOutput) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));


        Map<String, VBox> groupedMap = new LinkedHashMap<>();

        String[] blocks = aiOutput.split("Category:");
        for (String block : blocks) {
            if (block.trim().isEmpty()) continue;

            String[] lines = block.trim().split("\n");
            if (lines.length < 5) continue;

            String category = lines[0].trim();
            String role = lines[1].replace("→ Role:", "").trim();
            String org = lines[2].replace("→ Organization:", "").trim();
            String year = lines[3].replace("→ Year:", "").trim();
            String desc = lines[4].replace("→ Description:", "").trim();

            VBox groupBox = groupedMap.computeIfAbsent(category, k -> {
                VBox box = new VBox(8);
                Label title = new Label("📁 " + k);
                title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
                box.setPrefWidth(450); // 控制整个 group 的宽度
                box.setMaxWidth(450);  // 防止它自动撑开
                box.getChildren().add(title);
                box.setPadding(new Insets(5));
                return box;
            });

            String previewText = "( " + role + " ) - " + org + " ( " + year + " )";

            Label item = new Label(previewText);
            item.setPrefWidth(450);  // 优先使用的宽度
            item.setMinWidth(450);   // 最小宽度，避免被压缩
            item.setMaxWidth(450);   // 最大宽度，避免被拉伸
            item.setWrapText(true);
            item.setStyle("-fx-background-color: #F8F8F8; -fx-padding: 10px; -fx-background-radius: 8px; -fx-font-size: 13px;");
            ClickDisplayFullcontent(previewText, desc, "Working experience details", item);

            groupBox.getChildren().add(item);

        }

        root.getChildren().addAll(groupedMap.values());
        return root;
    }

    public static VBox CategorizedPositionUI(String aiOutput) {
        VBox root = new VBox(0);
        root.setAlignment(Pos.CENTER);

        Map<String, VBox> groupedMap = new LinkedHashMap<>();

        String[] blocks = aiOutput.split("Category:");
        for (String block : blocks) {
            if (block.trim().isEmpty()) continue;

            String[] lines = block.trim().split("\n");
            if (lines.length < 4) continue;

            String category = lines[0].trim();
            String Position = lines[1].replace("→ Position:", "").trim();
            String Match = lines[2].replace("→ Match:", "").trim();
            String desc = lines[3].replace("→ Description:", "").trim();

            VBox groupBox = groupedMap.computeIfAbsent(category, k -> {
                VBox box = new VBox(5);
                VBox.setMargin(box, new Insets(3, 10, 3, 10));
                return box;
            });

            String previewText = "(" + Position + ") --- " + "Matching: [ " + Match + " ]";

            Label item = new Label(previewText);
            item.setPrefWidth(300);
            item.setMaxWidth(300);
            item.setWrapText(true);
            item.setStyle("-fx-background-color: #F8F8F8; -fx-padding: 5px; -fx-background-radius: 10px; -fx-font-size: 13px;");
            ClickDisplayFullcontent(previewText, desc, "Position matching details", item);

            groupBox.getChildren().add(item);

        }

        root.getChildren().addAll(groupedMap.values());
        return root;
    }

    public static void CategorizedSalary(String aiOutput,Label SalaryLabel) {

        String salary = "N/A";
        String explanation = "No explanation provided.";

        String[] lines = aiOutput.split("\n");
        for (String line : lines) {
            line = line.trim();
            if (line.toLowerCase().startsWith("recommended salary:")) {
                salary = line.substring("Recommended Salary:".length()).trim();
            } else if (line.toLowerCase().startsWith("explanation:")) {
                explanation = line.substring("Explanation:".length()).trim();
            }
        }

        SalaryLabel.setText(salary);
        SalaryLabel.setStyle("-fx-background-color: #F8F8F8; -fx-padding: 10px; -fx-background-radius: 15px; -fx-font-size: 25px;");

        ClickDisplayFullcontent(salary, explanation,"Salary analysis explanation",SalaryLabel);


    }

    public static void CategorizedUni(String aiOutput, Label uniLabel) {
        String uni = "N/A";
        StringBuilder explanationBuilder = new StringBuilder();

        String[] lines = aiOutput.split("\n");
        boolean readingExplanation = false;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();

            if (i == 0 && !line.toLowerCase().startsWith("explanation")) {
                uni = line; // 默认第一行为学校名
            } else if (line.toLowerCase().startsWith("explanation:")) {
                readingExplanation = true;
            } else if (readingExplanation && !line.isEmpty()) {
                explanationBuilder.append(line).append("\n\n"); // 每行解释后多加一行空行
            }
        }

        uniLabel.setText(uni);
        uniLabel.setStyle("-fx-background-color: #F8F8F8; -fx-padding: 10px; -fx-background-radius: 15px; -fx-font-size: 15px;");

        String explanation = explanationBuilder.toString().trim();
        ClickDisplayFullcontent(uni, explanation, "University Details", uniLabel);
    }

    public static void CategorizedScore(String aiOutput, Label ScoreLabel) {
        String score = "N/A";
        StringBuilder explanationBuilder = new StringBuilder();

        String[] lines = aiOutput.split("\n");
        boolean readingExplanation = false;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();

            if (i == 0 && line.matches("^\\d{2,3}/100$")) {
                score = line;
            } else if (line.toLowerCase().startsWith("explanation:")) {
                readingExplanation = true;
            } else if (readingExplanation && !line.isEmpty()) {
                explanationBuilder.append(line).append("\n\n"); // 每段解释后加空行
            }
        }

        ScoreLabel.setText(score);
        ScoreLabel.setStyle("-fx-background-color: #F8F8F8; -fx-padding: 10px; -fx-background-radius: 15px; -fx-font-size: 18px;");

        String explanation = explanationBuilder.toString().trim();
        ClickDisplayFullcontent(score, explanation, "Resume Score Explanation", ScoreLabel);
    }





    public static void ClickDisplayFullcontent(String title, String explanation, String WindowTitle, Label label) {

        label.setCursor(Cursor.HAND);

        label.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle(WindowTitle);
            alert.setHeaderText(title);

            // ✅ 设置内容为 TextArea
            TextArea content = new TextArea(explanation);
            content.setWrapText(true);
            content.setEditable(false);
            content.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial';");
            content.setPrefWidth(600);
            content.setPrefHeight(300);
            alert.getDialogPane().setContent(content);

            // ✅ 添加 OK 按钮前先清空，防止重复添加
            alert.getButtonTypes().clear();
            alert.getButtonTypes().add(ButtonType.OK);

            // ✅ 清除窗口图标（可选）
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().clear();

            alert.showAndWait();
        });

    }




}
