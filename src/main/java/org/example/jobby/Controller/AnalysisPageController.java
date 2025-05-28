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
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.jobby.Model.DisplayUIModel;
import org.example.jobby.Model.ModulModel;
import org.example.jobby.Model.PositionDescFileDao;
import org.example.jobby.Model.ShardResponseData;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.Cursor;

import java.util.List;

public class AnalysisPageController {


    @FXML
    private Label PersonalityContent;

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
    @FXML private VBox positionBox;
    @FXML private Label CGPAContent;
    @FXML
    private ComboBox<String> positionComboBox;

    private final String filePath = "src/main/resources/Text/PositionDesc.txt";
    private final PositionDescFileDao dao = new PositionDescFileDao();








    @FXML
    public void initialize() {
        gearButton.setOnAction(event -> showOptionsPopup());
        try {
            List<String> posLines = dao.loadPositionLines(filePath);
            positionComboBox.getItems().setAll(posLines);
        } catch (Exception e) {
            e.printStackTrace();
            }

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



                },
                () -> {
                    if (DeepSeekChat.FaildConnection){

                        ModulModel.WarningPopup();
                        DeepSeekChat.FaildConnection=false;
                        UploadFile.setDisable(false);
                        hideLoadingState(contentVBox);
                    }else {

                        UploadFile.setDisable(false);
                        hideLoadingState(contentVBox);



                        mainPane.getChildren().clear();
                        String skills=ShardResponseData.responseModel.getSkillMatchingResponse();
                        FlowPane skillBox = DisplayUIModel.buildSkillLabels(skills);
                        mainPane.getChildren().add(skillBox);


                        experienceUI.getChildren().clear();
                        String aiResponse = ShardResponseData.responseModel.getWorkExperienceResponse(); // 得到结构化文本
                        VBox maincontent = DisplayUIModel.CategorizedExperienceUI(aiResponse);
                        experienceUI.getChildren().add(maincontent);



                        PersonalityContent.setText(ShardResponseData.responseModel.getPersonalityResponse());
                        System.out.println("PersonalityContent is " + ShardResponseData.responseModel.getPersonalityResponse());


                        String aiReply = ShardResponseData.responseModel.getSoftSkillsResponse();
                        DisplayUIModel.displayLanguageAndSoftSkills(aiReply,LanguageVbox,SoftSkillsVbox);


                        aiResponse = ShardResponseData.responseModel.getInformationResponse();// AI 回复字符串
                        DisplayUIModel.updateBasicInfoUI(aiResponse,locationLabel,majorLabel,certificateBox);



                        positionBox.getChildren().clear();
                        aiResponse = ShardResponseData.responseModel.getPositionResponse(); // 得到结构化文本
                        maincontent = DisplayUIModel.CategorizedPositionUI(aiResponse);
                        positionBox.getChildren().add(maincontent);
                        System.out.println(DisplayUIModel.CategorizedPositionUI(aiResponse));

                        aiReply=ShardResponseData.responseModel.getSalaryResponse();
                        DisplayUIModel.CategorizedSalary(aiReply, SalaryContent);

                        aiReply=ShardResponseData.responseModel.getScoreResponse();
                        DisplayUIModel.CategorizedScore(aiReply,ScoreContent);

                        NameContent.setText(ShardResponseData.responseModel.getNameResponse());
                        System.out.println("NameContent is " + ShardResponseData.responseModel.getNameResponse());


                        aiReply=ShardResponseData.responseModel.getGraduatedResponse();
                        DisplayUIModel.CategorizedUni(aiReply,GraduatedContent);

                        CGPAContent.setText(ShardResponseData.responseModel.getCgpaResponse());







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