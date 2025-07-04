package org.example.CulturaMYTrip.Model;
import java.awt.Desktop;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.example.CulturaMYTrip.api.GeocodingService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class DisplayUIModel {



    public static FlowPane updateAttractionTagsOnlyUI(String aiReplyText, FlowPane attractionPane) {
        attractionPane.getChildren().clear();

        String[] lines = aiReplyText.split("\n");
        boolean collectingAttractions = false;

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith("Famous Attractions:")) {
                collectingAttractions = true;
            } else if (collectingAttractions && line.startsWith("-")) {
                String attractionLine = line.substring(1).trim(); // Remove dash
                String[] parts = attractionLine.split(":", 2);     // Split name: description
                String name = parts[0].trim();

                Label tag = new Label(name);
                tag.setStyle(
                        "-fx-background-color: #F0F8FF;" +
                                "-fx-padding: 8 12;" +
                                "-fx-background-radius: 10;" +
                                "-fx-border-color: #B0C4DE;" +
                                "-fx-font-size: 13px;"
                );

                handleOpenStreetView(name, "Street View" ,tag);
                attractionPane.getChildren().add(tag);
            }
        }

        attractionPane.setHgap(8);
        attractionPane.setVgap(8);
        attractionPane.setPrefWrapLength(300);
        return attractionPane;
    }



    private static void handleOpenStreetView(String placeName, String WindowTitle, Label label) {
        label.setCursor(Cursor.HAND);

        label.setOnMouseClicked(event -> {
            try {
                double[] coords = GeocodingService.getCoordinates(placeName);
                StreetViewOpener.openStreetView(coords[0], coords[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
    }

    public class StreetViewOpener {
    public static void openStreetView(double lat, double lng) {
        String url = String.format("https://www.google.com/maps/@?api=1&map_action=pano&viewpoint=%f,%f", lat, lng);
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI(url));
            } else {
                System.err.println("Desktop browsing is not supported.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


    public static VBox SouvenirUI(String aiReplyText, FlowPane pane) {
        pane.getChildren().clear();

        String[] lines = aiReplyText.split("\n");
        boolean collectingAttractions = false;

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith("Souvenir:")) {
                collectingAttractions = true;
            } else if (collectingAttractions && line.startsWith("-")) {
                String attractionLine = line.substring(1).trim(); // Remove dash
                String[] parts = attractionLine.split(":", 2);     // Split name: description
                String name = parts[0].trim();
                String desc = parts.length > 1 ? parts[1].trim() : "No description available.";

                Label tag = new Label(name);
                tag.setStyle(
                        "-fx-background-color: #F0F8FF;" +
                                "-fx-padding: 8 12;" +
                                "-fx-background-radius: 10;" +
                                "-fx-border-color: #B0C4DE;" +
                                "-fx-font-size: 13px;"
                );

                ClickDisplayFullcontent(name, desc, "Souvenir", tag);
                pane.getChildren().add(tag);
            }
        }

        pane.setHgap(8);
        pane.setVgap(8);
        pane.setPrefWrapLength(300);
        return null;
    }






    public static VBox CategorizedTravelUI(String aiOutput) {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        Map<String, VBox> dayMap = new LinkedHashMap<>();
        boolean hasValidEntry = false;

        String[] blocks = aiOutput.split("Day ");
        for (String block : blocks) {
            if (block.trim().isEmpty()) continue;

            String[] lines = block.trim().split("\n");
            if (lines.length < 2) continue;

            String dayLine = lines[0].trim(); // e.g., "1: Explore Kuala Lumpur Heritage"
            String dayTitle = "Day " + dayLine;

            VBox dayBox = new VBox(8);
            Label title = new Label("📅 " + dayTitle);
            title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
            dayBox.getChildren().add(title);
            dayBox.setPadding(new Insets(5));
            dayBox.setPrefWidth(480);
            dayBox.setMaxWidth(480);

            // Parse locations under this day
            for (int i = 1; i < lines.length - 1; i++) {
                if (!lines[i].contains("→ Name:")) continue;
                String name = lines[i].replace("→ Name:", "").trim();
                String descLine = lines[i + 1].trim();
                if (!descLine.contains("→ Description:")) continue;
                String desc = descLine.replace("→ Description:", "").trim();

                Label placeLabel = new Label("🏛️ " + name);
                placeLabel.setPrefWidth(460);
                placeLabel.setWrapText(true);
                placeLabel.setStyle("-fx-background-color: #FAFAFA; -fx-padding: 8px; -fx-background-radius: 8px; -fx-font-size: 13px;");

                // Attach click event to show full description
                ClickDisplayFullcontent(name, desc, "Cultural Site Details", placeLabel);
                dayBox.getChildren().add(placeLabel);

                i++; // skip next line (already used as description)
                hasValidEntry = true;
            }

            if (hasValidEntry) {
                dayMap.put(dayTitle, dayBox);
            }
        }

        if (hasValidEntry) {
            root.getChildren().addAll(dayMap.values());
        } else {
            Label noData = new Label("Insufficient data");
            noData.setStyle("-fx-text-fill: #999; -fx-font-style: italic; -fx-font-size: 13px;");
            root.getChildren().add(noData);
        }

        return root;
    }






    public static VBox CategorizedFoodUI(String aiOutput) {
        VBox root = new VBox(0);
        root.setAlignment(Pos.CENTER);

        Map<String, VBox> groupedMap = new LinkedHashMap<>();

        String[] blocks = aiOutput.split("Category:");
        for (String block : blocks) {
            if (block.trim().isEmpty()) continue;

            String[] lines = block.trim().split("\n");
            if (lines.length < 4) continue;

            String category = lines[0].trim(); // e.g., Penang
            String food = lines[1].replace("→ Food:", "").trim();
            String popularity = lines[2].replace("→ Popularity:", "").trim();
            String desc = lines[3].replace("→ Description:", "").trim();

            VBox groupBox = groupedMap.computeIfAbsent(category, k -> {
                VBox box = new VBox(5);
                Label header = new Label("🍽️ Region: " + k);
                header.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
                box.getChildren().add(header);
                VBox.setMargin(box, new Insets(6, 10, 6, 10));
                return box;
            });

            String previewText = "(" + food + ") --- Popularity: [ " + popularity + " ]";

            Label item = new Label(previewText);
            item.setPrefWidth(600);
            item.setMaxWidth(600);
            item.setWrapText(true);
            item.setStyle("-fx-background-color: #FFF8E1; -fx-padding: 6px; -fx-background-radius: 20px; -fx-font-size: 13px;");
            ClickDisplayFullcontent(previewText, desc, "Food Details", item);

            groupBox.getChildren().add(item);
        }

        root.getChildren().addAll(groupedMap.values());
        return root;
    }


    public static VBox CategorizedHotelUI(String aiOutput) {
        VBox root = new VBox(0);
        root.setAlignment(Pos.CENTER);

        Map<String, VBox> groupedMap = new LinkedHashMap<>();

        String[] blocks = aiOutput.split("Category:");
        for (String block : blocks) {
            if (block.trim().isEmpty()) continue;

            String[] lines = block.trim().split("\n");
            if (lines.length < 4) continue;

            String category = lines[0].trim(); // e.g., George Town
            String hotel = lines[1].replace("→ Hotel:", "").trim();
            String rating = lines[2].replace("→ Rating:", "").trim();
            String desc = lines[3].replace("→ Description:", "").trim();

            VBox groupBox = groupedMap.computeIfAbsent(category, k -> {
                VBox box = new VBox(5);
                Label header = new Label("🏨 Area: " + k);
                header.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
                box.getChildren().add(header);
                VBox.setMargin(box, new Insets(6, 10, 6, 10));
                return box;
            });

            String previewText = "(" + hotel + ") --- Rating: [ " + rating + " ]";

            Label item = new Label(previewText);
            item.setPrefWidth(600);
            item.setMaxWidth(600);
            item.setWrapText(true);
            item.setStyle("-fx-background-color: #E3F2FD; -fx-padding: 6px; -fx-background-radius: 20px; -fx-font-size: 13px;");
            ClickDisplayFullcontent(previewText, desc, "Hotel Info", item);

            groupBox.getChildren().add(item);
        }

        root.getChildren().addAll(groupedMap.values());
        return root;
    }


//    public static VBox CategorizedCityLabels(String aiOutput) {
//        VBox root = new VBox(5);
//        root.setAlignment(Pos.CENTER);
//        root.setPadding(new Insets(5));
//
//        String[] blocks = aiOutput.split("Category:");
//
//        for (String block : blocks) {
//            if (block.trim().isEmpty()) continue;
//
//            String[] lines = block.trim().split("\n");
//            if (lines.length < 2) continue;
//
//            String city = lines[0].replace("→ category:", "").trim();
//            if (city.isEmpty()) continue;
//
//            String descLine = lines[1].trim();
//            if (!descLine.startsWith("→ Description:")) continue;
//
//            String desc = descLine.replace("→ Description:", "").trim();
//
//
//            Label cityLabel = new Label(city);
//            cityLabel.setWrapText(true);
//            cityLabel.setPrefWidth(260);
//            cityLabel.setAlignment(Pos.CENTER);
//            cityLabel.setStyle("-fx-background-color: #E0F7FA; -fx-padding: 10px; -fx-background-radius: 15px; -fx-font-size: 14px;");
//
//
//            ClickDisplayFullcontent(city, desc, "City Info", cityLabel);
//
//            root.getChildren().add(cityLabel);
//        }
//
//        if (root.getChildren().isEmpty()) {
//            Label noData = new Label("No valid cities found.");
//            noData.setStyle("-fx-text-fill: #999999; -fx-font-style: italic;");
//            root.getChildren().add(noData);
//        }
//
//        return root;
//    }

    public static void  CategorizedCityLabels(String aiOutput, FlowPane pane) {
        pane.getChildren().clear();

        String[] blocks = aiOutput.split("Category:");

        for (String block : blocks) {
            if (block.trim().isEmpty()) continue;

            String[] lines = block.trim().split("\n");
            if (lines.length < 2) continue;

            String city = lines[0].trim();
            String descLine = lines[1].trim();

            if (!descLine.startsWith("→ Description:")) continue;

            String desc = descLine.replace("→ Description:", "").trim();

            Label cityTag = new Label(city);
            cityTag.setStyle(
                    "-fx-background-color: #F0F8FF;" +
                            "-fx-padding: 8 12;" +
                            "-fx-background-radius: 10;" +
                            "-fx-border-color: #B0C4DE;" +
                            "-fx-font-size: 13px;"
            );

            ClickDisplayFullcontent(city, desc, "City Info", cityTag);
            pane.getChildren().add(cityTag);
        }

        pane.setHgap(8);
        pane.setVgap(8);
        pane.setPrefWrapLength(300); // Adjust based on container size
    }












    public static void ClickDisplayFullcontent(String title, String explanation, String WindowTitle, Label label) {

        label.setCursor(Cursor.HAND);

        label.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle(WindowTitle);
            alert.setHeaderText(title);


            TextArea content = new TextArea(explanation);
            content.setWrapText(true);
            content.setEditable(false);
            content.setStyle("-fx-font-size: 20px; -fx-font-family: 'Arial';");
            content.setPrefWidth(600);
            content.setPrefHeight(300);
            alert.getDialogPane().setContent(content);


            alert.getButtonTypes().clear();
            alert.getButtonTypes().add(ButtonType.OK);


            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().clear();

            alert.showAndWait();
        });

    }



    public static void bindBudgetLabelWithExplanation(String aiResponse, Label foodLabel, Label liveLabel, Label transportLabel, Label totalLabel) {
        String[] lines = aiResponse.split("\n");

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith("Food:")) {
                applyLabelData(line, "Food", foodLabel, "🍱 Food Info");
            } else if (line.startsWith("Live:")) {
                applyLabelData(line, "Live", liveLabel, "🏨 Accommodation Info");
            } else if (line.startsWith("Transport:")) {
                applyLabelData(line, "Transport", transportLabel, "🚗 Transport Info");
            } else if (line.startsWith("Total:")) {
                applyLabelData(line, "Total", totalLabel, "💰 Total Budget Info");
            }
        }
    }

    private static void applyLabelData(String line, String key, Label label, String title) {
        try {
            String[] mainParts = line.split("→");
            String amountPart = mainParts[0].split("USD")[1].trim();
            String explanation = mainParts.length > 1 ? mainParts[1].trim() : "No explanation provided.";

            label.setText("USD " + amountPart);
            ClickDisplayFullcontent(key, explanation, title, label);
        } catch (Exception e) {
            label.setText("N/A");
            ClickDisplayFullcontent(key, "Unable to extract explanation.", title, label);
        }
    }







}
