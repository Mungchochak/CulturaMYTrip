package org.example.CulturaMYTrip.Controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.util.Duration;
import org.example.CulturaMYTrip.Model.*;

import java.awt.*;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;


import java.util.List;

import static org.example.CulturaMYTrip.Model.DisplayUIModel.bindBudgetLabelWithExplanation;


public class AnalysisPageController {

    public DeepSeekResponseModel deepSeekResponseModel = new DeepSeekResponseModel();
    public DeepSeekPromptModel deepSeekPromptModel = new DeepSeekPromptModel();



    @FXML
    private Button UploadFile;
    @FXML
    private VBox experienceUI,FoodBox,HotelBox;

    @FXML
    private Label CulturalContent;

    @FXML
    private FlowPane attractionPane, SouvenirPane,CitiesBox;

    @FXML
    private TextField DeparturePoint,TravelDays,Budget;

    @FXML
    private TextArea Requirement;

    @FXML
    private  Label FoodLabel,LiveLabel,TransportLabel,TotalLabel;



    @FXML private VBox contentVBox;




    private Label loadingLabel;
    private Timeline loadingAnimation;
    private final List<Node> cachedContent = new ArrayList<>();



    @FXML
    public void initialize() {




    }



    @FXML
    protected void ComfirmClick() {
        String CultureContent ="Malaysia Chinese";

        String departure = DeparturePoint.getText();
        String days = TravelDays.getText();
        String budget = Budget.getText();
        String requirement = Requirement.getText();

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



                deepSeekResponseModel.setTravelPlanResponse(
                        DeepSeekChat.callDeepSeekAPI(deepSeekPromptModel.getTravelPlanprompt(requirement,budget,days,departure,CultureContent))
                );

                String fullUserInput =
                                "User Requirements: " + requirement + "\n" +
                                "Budget: " + budget + "\n" +
                                "Travel Days: " + days + "\n" +
                                "Departure Point: " + departure + "\n" +
                                "Cultural Interests: " + CultureContent;

                System.out.println(fullUserInput);

                String TravelPlan = ShardResponseData.responseModel.getTravelPlanResponse();

                deepSeekResponseModel.setAttractionResponse(
                        DeepSeekChat.callDeepSeekAPI(deepSeekPromptModel.getAttractionprompt(TravelPlan,requirement,budget,days,departure,CultureContent))
                );
                deepSeekResponseModel.setSouvenirResponse(
                        DeepSeekChat.callDeepSeekAPI(deepSeekPromptModel.getSouvenirprompt(TravelPlan,requirement,budget,days,departure,CultureContent))
                );
                deepSeekResponseModel.setFoodResponse(
                        DeepSeekChat.callDeepSeekAPI(deepSeekPromptModel.getFoodprompt(TravelPlan,requirement,budget,days,departure,CultureContent))
                );
                deepSeekResponseModel.setHotelResponse(
                        DeepSeekChat.callDeepSeekAPI(deepSeekPromptModel.getHotelprompt(TravelPlan,requirement,budget,days,departure))
                );
                deepSeekResponseModel.setBudgetResponse(
                        DeepSeekChat.callDeepSeekAPI(deepSeekPromptModel.getBudgetprompt(TravelPlan,requirement,budget,days,departure))
                );

                deepSeekResponseModel.setCultureResponse(
                        DeepSeekChat.callDeepSeekAPI(deepSeekPromptModel.getCultureprompt(CultureContent))
                );


                deepSeekResponseModel.setCitiesResponse(DeepSeekChat.callDeepSeekAPI(deepSeekPromptModel.getCityExtractionPrompt(TravelPlan)));


                deepSeekResponseModel.setBudgetResponse(
                        DeepSeekChat.callDeepSeekAPI(deepSeekPromptModel.getBudgetprompt(TravelPlan,requirement,budget,days,departure))
                );


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


                    aiResponse = ShardResponseData.responseModel.getFoodResponse();
                    VBox root = DisplayUIModel.CategorizedFoodUI(aiResponse);
                    FoodBox.getChildren().clear();
                    FoodBox.getChildren().add(root);


                    aiResponse = ShardResponseData.responseModel.getHotelResponse();
                    VBox root2 = DisplayUIModel.CategorizedHotelUI(aiResponse);
                    HotelBox.getChildren().clear();
                    HotelBox.getChildren().add(root2);


                    aiResponse = ShardResponseData.responseModel.getCitiesResponse();
                    CitiesBox.getChildren().clear();
                    DisplayUIModel.CategorizedCityLabels(aiResponse, CitiesBox);


                    aiResponse = ShardResponseData.responseModel.getBudgetResponse();
                    bindBudgetLabelWithExplanation(aiResponse, FoodLabel, LiveLabel, TransportLabel, TotalLabel);


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