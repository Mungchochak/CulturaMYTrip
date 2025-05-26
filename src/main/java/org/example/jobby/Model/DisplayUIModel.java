package org.example.jobby.Model;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
                certLabel.setOnMouseClicked(e -> showCertExplanation(certName, certDesc));
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



    public static VBox buildCategorizedExperienceUI(String aiOutput) {
        VBox root = new VBox(15);
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
                box.getChildren().add(title);
                box.setPadding(new Insets(5));
                return box;
            });

            String previewText = "(" + role + ") --- " + org + " (" + year + ")";

            Label item = new Label(previewText);
            item.setPrefWidth(600);
            item.setMaxWidth(600);
            item.setWrapText(true);
            item.setStyle("-fx-background-color: #F8F8F8; -fx-padding: 10px; -fx-background-radius: 8px; -fx-font-size: 13px;");
            item.setOnMouseClicked(e -> showExpandedDialog(previewText, desc,"Working experience details"));

            groupBox.getChildren().add(item);

        }

        root.getChildren().addAll(groupedMap.values());
        return root;
    }

//    public static VBox CategorizedPositionUI(String aiOutput) {
//        VBox root = new VBox(15);
//        root.setPadding(new Insets(10));
//
//        Map<String, VBox> groupedMap = new LinkedHashMap<>();
//
//        String[] blocks = aiOutput.split("Category:");
//        for (String block : blocks) {
//            if (block.trim().isEmpty()) continue;
//
//            String[] lines = block.trim().split("\n");
//            if (lines.length < 5) continue;
//
//            String category = lines[0].trim();
//            String Position = lines[1].replace("→ Position:", "").trim();
//            String Match = lines[2].replace("→ Match:", "").trim();
//            String desc = lines[3].replace("→ Description:", "").trim();
//
//            VBox groupBox = groupedMap.computeIfAbsent(category, k -> {
//                VBox box = new VBox(8);
//                Label title = new Label("📁 " + k);
//                title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
//                box.getChildren().add(title);
//                box.setPadding(new Insets(5));
//                return box;
//            });
//
//            String previewText = "(" + Position + ") --- " + Match + " (" + year + ")";
//
//            Label item = new Label(previewText);
//            item.setPrefWidth(600);
//            item.setMaxWidth(600);
//            item.setWrapText(true);
//            item.setStyle("-fx-background-color: #F8F8F8; -fx-padding: 10px; -fx-background-radius: 8px; -fx-font-size: 13px;");
//            item.setOnMouseClicked(e -> showExpandedDialog(previewText, desc,"Working experience details"));
//
//            groupBox.getChildren().add(item);
//
//        }
//
//        root.getChildren().addAll(groupedMap.values());
//        return root;
//    }





    private static void showExpandedDialog(String title, String explanation,String WindowTitle) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("WindowTitle");
        alert.setHeaderText(title);
        alert.setContentText(explanation);
        alert.getButtonTypes().add(ButtonType.OK);

        // 移除默认窗口图标
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().clear();

        alert.showAndWait();
    }




}
