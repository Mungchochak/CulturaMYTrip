package org.example.CulturaMYTrip.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.CulturaMYTrip.Model.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.Cursor;

import java.util.List;

public class AnalysisPageController {

    public DeepSeekResponseModel deepSeekResponseModel = new DeepSeekResponseModel();
    public DeepSeekPromptModel deepSeekPromptModel = new DeepSeekPromptModel();



    @FXML
    private Button UploadFile;
    @FXML
    private VBox experienceUI,FoodBox,HotelBox,CitiesBox;

    @FXML
    private Label CulturalContent;

    @FXML
    private FlowPane attractionPane, SouvenirPane;




    @FXML private Label locationLabel;
    @FXML private Label majorLabel;
    @FXML private FlowPane certificateBox;
    @FXML private VBox contentVBox;
    @FXML private VBox positionBox;
    @FXML private Label CGPAContent;
    @FXML
    private  ComboBox<String> positionComboBox;

    private final String filePath = "src/main/resources/Text/PositionDesc.txt";
    private final PositionDescFileDao dao = new PositionDescFileDao();
    private Label loadingLabel;
    private Timeline loadingAnimation;
    private final List<Node> cachedContent = new ArrayList<>();



    @FXML
    public void initialize() {


        gearButton.setOnAction(event -> showOptionsPopup());
        try {
            List<String> posLines = dao.loadPositionLines(filePath);

            posLines.add(0, "No specified analysis");


            positionComboBox.getItems().setAll(posLines);


            positionComboBox.setValue("No specified analysis");
        } catch (Exception e) {
            e.printStackTrace();
            }


    }



    @FXML
    protected void ComfirmClick() {
        String content = "KLCC,SUBANG JAYA";
        String CultureContent ="Malaysia Chinese";

        ShardResponseData.responseModel = deepSeekResponseModel;

        UploadFile.setDisable(true);
        setupLoadingLabel(contentVBox);
        showLoadingState(contentVBox);
        LoadPersonalInformation();

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                if (DeepSeekChat.FaildConnection) {
                    Platform.runLater(() -> {
                        ModulModel.WarningPopup();
                        DeepSeekChat.FaildConnection = false;
                        UploadFile.setDisable(false);
                        hideLoadingState(contentVBox);
                    });
                    return null;
                }

                // === 后台线程中做 API 调用 ===
                deepSeekResponseModel.setTravelPlanResponse(
                        DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getTravelPlanprompt(content))
                );
                deepSeekResponseModel.setAttractionResponse(
                        DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getAttractionprompt(content))
                );
                deepSeekResponseModel.setSouvenirResponse(
                        DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getSouvenirprompt())
                );
                deepSeekResponseModel.setFoodResponse(
                        DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getFoodprompt())
                );
                deepSeekResponseModel.setHotelResponse(
                        DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getHotelprompt())
                );
                deepSeekResponseModel.setBudgetResponse(
                        DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getBudgetprompt())
                );

                deepSeekResponseModel.setCultureResponse(
                        DeepSeekChat.callDeepSeekAPI(CultureContent, deepSeekPromptModel.getCultureprompt(CultureContent))
                );

                String TravelPlan = ShardResponseData.responseModel.getTravelPlanResponse();
                deepSeekResponseModel.setCitiesResponse(DeepSeekChat.callDeepSeekAPI(content, deepSeekPromptModel.getCityExtractionPrompt(TravelPlan)));

                // === 回到主线程更新界面 ===
                Platform.runLater(() -> {
                    UploadFile.setDisable(false);
                    hideLoadingState(contentVBox);

                    attractionPane.getChildren().clear();
                    String aiResponse = ShardResponseData.responseModel.getAttractionResponse();
                    DisplayUIModel.updateAttractionTagsOnlyUI(aiResponse, attractionPane);

                    experienceUI.getChildren().clear();
                    aiResponse = ShardResponseData.responseModel.getTravelPlanResponse();
                    VBox maincontent = DisplayUIModel.CategorizedTravelUI(aiResponse);
                    experienceUI.getChildren().add(maincontent);

                    SouvenirPane.getChildren().clear();
                    aiResponse = ShardResponseData.responseModel.getSouvenirResponse();
                    DisplayUIModel.SouvenirUI(aiResponse, SouvenirPane);

                    aiResponse= ShardResponseData.responseModel.getCultureResponse();
                    CulturalContent.setText(aiResponse);

                    // 1. 处理美食显示
                    aiResponse = ShardResponseData.responseModel.getFoodResponse();
                    VBox root = DisplayUIModel.CategorizedFoodUI(aiResponse);
                    FoodBox.getChildren().clear();
                    FoodBox.getChildren().add(root);

                    // 2. 处理酒店显示
                    aiResponse = ShardResponseData.responseModel.getHotelResponse();
                    VBox root2 = DisplayUIModel.CategorizedHotelUI(aiResponse);
                    HotelBox.getChildren().clear(); // hotelPane 同上
                    HotelBox.getChildren().add(root2);


                    aiResponse= ShardResponseData.responseModel.getCitiesResponse();
                    VBox cityLabelBox = DisplayUIModel.CategorizedCityLabels(aiResponse);
                    CitiesBox.getChildren().clear();
                    CitiesBox.getChildren().add(cityLabelBox);



                    System.out.println("Finished Analysis");
                });

                return null;
            }
        };

        new Thread(task).start(); // 启动后台线程
    }





    public void RefreshpositionComboBox() {
        try {
            List<String> posLines = dao.loadPositionLines(filePath);
            posLines.add(0, "No specified analysis");
            positionComboBox.getItems().setAll(posLines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


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


    public void hideLoadingState(VBox containerVBox) {
        loadingAnimation.stop();
        loadingLabel.setVisible(false);
        containerVBox.getChildren().remove(loadingLabel);
        containerVBox.getChildren().addAll(0, cachedContent);
        containerVBox.setAlignment(Pos.TOP_LEFT);
    }




    @FXML
    protected void handleOpenStreamlit() {
        try {
            Desktop.getDesktop().browse(new URI("https://bobbyjobby.streamlit.app/"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button gearButton;


    private Popup optionsPopup; // Store the popup so you can hide/show as needed



    private void switchScene(String sceneName) {
        try {
            Parent newRoot;
            FXMLLoader loader;

            switch (sceneName) {
                case "Company_Info":
                    loader = new FXMLLoader(getClass().getResource("/org/example/CulturaMYTrip/Company_Info.fxml"));
                    newRoot = loader.load();
                    break;

                case "PositionDesc":
                    loader = new FXMLLoader(getClass().getResource("/org/example/CulturaMYTrip/PositionDesc.fxml"));
                    newRoot = loader.load();


                    PositionDescController controller = loader.getController();
                    controller.setAnalysisPageController(this);
                    break;

                default:
                    System.err.println("Unknown scene: " + sceneName);
                    return;
            }

            Stage newStage = new Stage();
            newStage.setTitle(sceneName);
            newStage.setScene(new Scene(newRoot));
            newStage.setResizable(false);
            newStage.show();

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
                "/org/example/CulturaMYTrip/Company_Info.png",
                "Company Information",
                () -> {
                    switchScene("Company_Info");
                    optionsPopup.hide();
                }
        );

        // Option 2: PositionDesc
        HBox positionDescOption = makePopupOption(
                "/org/example/CulturaMYTrip/PositionDesc.png",
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

    public String loadComAsString() throws Exception {
        StringBuilder sb = new StringBuilder();
        String filePath = "src/main/resources/Text/Company_Info.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    public static String selectedPosition;
    public static String CompanyInfo;
    public static boolean SpecialAnalysis;

    public void LoadPersonalInformation(){

        selectedPosition = positionComboBox.getSelectionModel().getSelectedItem();

        try {
            CompanyInfo = loadComAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    public static String PrintPersonalInformation(){


        String prompt =
                "Company Information:\n" + CompanyInfo + "\n\n" +
                        "Seeking Position Information:\n" +
                        "Selected Position: " + selectedPosition;

        return prompt;

    }





}