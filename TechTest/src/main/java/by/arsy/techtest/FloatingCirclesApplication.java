package by.arsy.techtest;

import by.arsy.techtest.entity.SmartCircle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FloatingCirclesApplication extends Application {

    private static final String APP_NAME = "Floating Circles";
    private static final double SCREEN_HEIGHT = 700;
    private static final double SCREEN_WIDTH = 700;
    private static final double USER_PANEL_WIDTH = 30;
    private Pane circleField;
    private BorderPane userPanel;


    @Override
    public void start(Stage stage) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/by.arsy.techtest/floating-circles-view.fxml"));

        try {
            BorderPane pane = loader.load();
            circleField = (Pane) pane.getChildren().get(0);
            userPanel = (BorderPane) pane.getChildren().get(1);

            userPanel.setMinHeight(USER_PANEL_WIDTH);
            circleField.setMaxSize(SCREEN_WIDTH, SCREEN_HEIGHT - userPanel.getHeight());

            addScreenSizeListener(stage);

            Scene scene = new Scene(pane, SCREEN_WIDTH, SCREEN_HEIGHT);
            stage.setTitle(APP_NAME);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch();
    }


    private void addScreenSizeListener(Stage stage) {
        addScreenHeightListener(stage);
        addScreenWidthListener(stage);
    }


    private void addScreenHeightListener(Stage stage) {
        stage.heightProperty().addListener((observable, oldValue, newValue) ->
           updateSmartCirclesAnimationPlaceHeight(newValue)
        );
    }

    private void addScreenWidthListener(Stage stage) {
        stage.widthProperty().addListener((observable, oldValue, newValue) ->
           updateSmartCirclesAnimationPlaceWidth(newValue)
        );
    }


    private void updateSmartCirclesAnimationPlaceHeight(Number newValue) {
        circleField.setMaxHeight((double) newValue - userPanel.getHeight());
        for (Node circle : circleField.getChildren()) {
            ((SmartCircle) circle).setAnimationPlaceHeight(circleField.getHeight());
        }
    }


    private void updateSmartCirclesAnimationPlaceWidth(Number newValue) {
        circleField.setMaxWidth((double) newValue);
        for (Node circle : circleField.getChildren()) {
            ((SmartCircle) circle).setAnimationPlaceWidth(circleField.getWidth());
        }
    }



}