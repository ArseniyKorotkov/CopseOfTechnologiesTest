package by.arsy.techtest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class FloatingCirclesApplication extends Application {

    private static final String APP_NAME = "Floating Circles";
    private static final double SCREEN_HEIGHT = 700;
    private static final double SCREEN_WIDTH = 700;


    @Override
    public void start(Stage stage) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/by.arsy.techtest/floating-circles-view.fxml"));

        try {
            BorderPane pane = loader.load();

            Pane circleField = (Pane) pane.getChildren().get(0);
            HBox userPanel = (HBox) pane.getChildren().get(1);

            circleField.setPrefSize(SCREEN_WIDTH, SCREEN_HEIGHT - userPanel.getHeight());

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
}