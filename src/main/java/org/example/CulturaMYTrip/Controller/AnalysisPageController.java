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





    @FXML private VBox contentVBox;

    @FXML
    private  ComboBox<String> positionComboBox;



    private Label loadingLabel;
    private Timeline loadingAnimation;
    private final List<Node> cachedContent = new ArrayList<>();



    @FXML
    public void initialize() {




    }



    @FXML
    protected void ComfirmClick() {
        String content = "KLCC,SUBANG JAYA";
        String CultureContent ="Malaysia Chinese";

        ShardResponseData.responseModel = deepSeekResponseModel;

        UploadFile.setDisable(true);
        setupLoadingLabel(contentVBox);
        showLoadingState(contentVBox);


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





//    public void RefreshpositionComboBox() {
//        try {
//            List<String> posLines = dao.loadPositionLines(filePath);
//            posLines.add(0, "No specified analysis");
//            positionComboBox.getItems().setAll(posLines);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


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







}