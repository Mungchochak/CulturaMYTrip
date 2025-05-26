package org.example.jobby.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.jobby.Model.DeepSeekPromptFollowupModel;
import org.example.jobby.Model.DisplayUIModel;
import org.example.jobby.Model.ModulModel;
import org.example.jobby.Model.ShardResponseData;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.Cursor;
import javafx.scene.control.ScrollPane;
import java.util.List;

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
    private VBox mainPane;

    @FXML
    private VBox experienceUI;

    @FXML
    private VBox LanguageVbox;

    @FXML
    private VBox SoftSkillsVbox;

    @FXML private Label locationLabel;
    @FXML private Label majorLabel;
    @FXML private FlowPane certificateBox;
    @FXML private VBox contentVBox;







    @FXML
    public void initialize() {
        gearButton.setOnAction(event -> showOptionsPopup());

    }

    private Label loadingLabel;
    private Timeline loadingAnimation;
    private final List<Node> cachedContent = new ArrayList<>();












    @FXML
    protected void UploadFileClick() {


        PDFScanner scanner = new PDFScanner();


        scanner.PDFScanner(
                () -> {
                    UploadFile.setDisable(true);
                    setupLoadingLabel(contentVBox);
                    showLoadingState(contentVBox);
//                  ClearText();
//                  OffEnlargeContent();
//                  StartLoadingAnimation();


                },
                () -> {
                    if (DeepSeekChat.FaildConnection){
//                        StopLoadingAnimation();
                        ModulModel.WarningPopup();
                        DeepSeekChat.FaildConnection=false;
                        UploadFile.setDisable(false);
                        hideLoadingState(contentVBox);
                    }else {

                        UploadFile.setDisable(false);
                        hideLoadingState(contentVBox);



//                        StopLoadingAnimation();


//                        SkillMatchingContent.setText(ShardResponseData.responseModel.getSkillMatchingResponse());
//                        System.out.println("SkillMatchingContent is " + ShardResponseData.responseModel.getSkillMatchingResponse());

                        mainPane.getChildren().clear();
                        String skills=ShardResponseData.responseModel.getSkillMatchingResponse();
                        FlowPane skillBox = DisplayUIModel.buildSkillLabels(skills);
                        mainPane.getChildren().add(skillBox);


//                        WorkingExperienceContent.setText(ShardResponseData.responseModel.getWorkExperienceResponse());
//                        System.out.println("WorkingExperienceContent is " + ShardResponseData.responseModel.getWorkExperienceResponse());

                        experienceUI.getChildren().clear();
                        String aiResponse = ShardResponseData.responseModel.getWorkExperienceResponse(); // 得到结构化文本
                        VBox maincontent = DisplayUIModel.buildCategorizedExperienceUI(aiResponse);
                        experienceUI.getChildren().add(maincontent);



                        PersonalityContent.setText(ShardResponseData.responseModel.getPersonalityResponse());
                        System.out.println("PersonalityContent is " + ShardResponseData.responseModel.getPersonalityResponse());

//                        SoftSkillsContent.setText(ShardResponseData.responseModel.getSoftSkillsResponse());
//                        System.out.println("SoftSkillsContent is " + ShardResponseData.responseModel.getSoftSkillsResponse());



                        String aiReply = ShardResponseData.responseModel.getSoftSkillsResponse();
                        DisplayUIModel.displayLanguageAndSoftSkills(aiReply,LanguageVbox,SoftSkillsVbox);

                        aiResponse = ShardResponseData.responseModel.getInformationResponse();// AI 回复字符串
                        DisplayUIModel.updateBasicInfoUI(aiResponse,locationLabel,majorLabel,certificateBox);


                        PositionContent.setText(ShardResponseData.responseModel.getPositionResponse());
                        System.out.println("PositionContent is " + ShardResponseData.responseModel.getPositionResponse());

//                        InformationContent.setText(ShardResponseData.responseModel.getInformationResponse());
//                        System.out.println("InformationContent is " + ShardResponseData.responseModel.getInformationResponse());



                        SalaryContent.setText(ShardResponseData.responseModel.getSalaryResponse());
                        System.out.println("SalaryContent is " + ShardResponseData.responseModel.getSalaryResponse());

                        ScoreContent.setText(ShardResponseData.responseModel.getScoreResponse());
                        System.out.println("ScoreContent is " + ShardResponseData.responseModel.getScoreResponse());

                        NameContent.setText(ShardResponseData.responseModel.getNameResponse());
                        System.out.println("NameContent is " + ShardResponseData.responseModel.getNameResponse());

                        GraduatedContent.setText(ShardResponseData.responseModel.getGraduatedResponse());
                        System.out.println("GraduatedContent is " + ShardResponseData.responseModel.getGraduatedResponse());







//                        EnlargeContent();

                    }




                }
        );


    }

    // 初始化 Loading Label 并加到 VBox 最底部，但默认隐藏
    public void setupLoadingLabel(VBox containerVBox) {
        loadingLabel = new Label("Loading");
        loadingLabel.setStyle("-fx-font-size: 80px; -fx-text-fill: #000000;");
        loadingLabel.setVisible(false);
        loadingLabel.setMaxWidth(Double.MAX_VALUE);
        loadingLabel.setAlignment(Pos.CENTER);

        containerVBox.getChildren().add(loadingLabel);

        loadingAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> loadingLabel.setText("Loading")),
                new KeyFrame(Duration.seconds(0.5), e -> loadingLabel.setText("Loading.")),
                new KeyFrame(Duration.seconds(1.0), e -> loadingLabel.setText("Loading..")),
                new KeyFrame(Duration.seconds(1.5), e -> loadingLabel.setText("Loading...")),
                new KeyFrame(Duration.seconds(2.0), e -> loadingLabel.setText("Loading....")),
                new KeyFrame(Duration.seconds(2.5), e -> loadingLabel.setText("Loading.....")),
                new KeyFrame(Duration.seconds(3.0), e -> loadingLabel.setText("Loading....."))
        );
        loadingAnimation.setCycleCount(Animation.INDEFINITE);
    }

    // 调用此方法显示 Loading Label，隐藏其他 HBox
    public void showLoadingState(VBox containerVBox) {
        cachedContent.clear();
        for (Node node : containerVBox.getChildren()) {
            if (node instanceof HBox) {
                cachedContent.add(node);
            }
        }
        containerVBox.getChildren().removeAll(cachedContent);
        loadingLabel.setVisible(true);
        loadingAnimation.play();
        containerVBox.setAlignment(Pos.CENTER);
    }

    // 调用此方法恢复原来的内容并隐藏 Loading Label
    public void hideLoadingState(VBox containerVBox) {
        loadingAnimation.stop();
        loadingLabel.setVisible(false);
        containerVBox.getChildren().remove(loadingLabel);
        containerVBox.getChildren().addAll(0, cachedContent); // 插回原位
        containerVBox.setAlignment(Pos.TOP_LEFT); // 或根据你的布局微调
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
        modulModel.ClickDisplayFullcontent(SalaryContent,ShardResponseData.responseModel.getSecondSalaryResponse());
        modulModel.ClickDisplayFullcontent(ScoreContent,ShardResponseData.responseModel.getSecondScoreResponse());

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
        modulModel.OffClickDisplayFullcontent(SalaryContent);
        modulModel.OffClickDisplayFullcontent(ScoreContent);
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
            ModulModel.startAnimatedLoading(label);
            label.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");
            label.setAlignment(Pos.CENTER);
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
    private Button gearButton;


    private Popup optionsPopup; // Store the popup so you can hide/show as needed


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